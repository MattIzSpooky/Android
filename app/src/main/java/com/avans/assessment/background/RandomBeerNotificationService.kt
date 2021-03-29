package com.avans.assessment.background

import android.app.Service
import android.content.Intent
import android.os.*
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.services.BeerService
import java.lang.NullPointerException
import java.util.Timer
import java.util.TimerTask


class RandomBeerNotificationService : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private val timer = Timer()

    companion object {
        private const val NOTIFICATION_INTERVAL = 60000L
    }

    override fun onCreate() {
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        private val beerService = BeerService(applicationContext)

        override fun handleMessage(msg: Message) {
            try {
                beerService.fetchRandom(onResponse = {
                    beerService.sendNotification(it)
                }, onError = {
                    print("kaput")
                })
            } catch (e: NullPointerException) {
                print("Context was null")
            } catch (e: NoInternetException) {
                print("No internet")
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()  // Restore interrupt status.
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        timer.cancel()
        serviceLooper?.quit()

        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                serviceHandler?.obtainMessage()?.also { msg ->
                    msg.arg1 = startId
                    serviceHandler?.sendMessage(msg)
                }
            }
        }, 0, NOTIFICATION_INTERVAL)

        return START_STICKY
    }
}

