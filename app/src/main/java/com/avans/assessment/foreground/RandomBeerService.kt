package com.avans.assessment.foreground

import android.app.Service
import android.content.Intent
import android.os.*
import com.avans.assessment.services.BeerService

class RandomBeerService : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        private val beerService = BeerService(applicationContext)

        override fun handleMessage(msg: Message) {
            try {
                beerService.fetchRandom {
                    beerService.sendNotification(it)
                }
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }

        return START_STICKY
    }
}

