package com.avans.assessment.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.avans.assessment.R
import com.avans.assessment.models.Beer
import com.avans.assessment.utils.GsonRequest
import java.lang.ref.WeakReference

class BeerService(ctx: Context) {
    private val context = WeakReference(ctx)

    fun fetchBeers(page: Int, perPage: Int, callback: (result: List<Beer>) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers?page=$page&per_page=$perPage", Array<Beer>::class.java,null,
            { response ->
                callback(response.toList())
            },
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }

    fun fetchBeer(id: String, callback: (result: Beer) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers/$id", Array<Beer>::class.java,null,
            { response ->
                callback(response[0])
            },
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }

    fun fetchRandom(callback: (result: Beer) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers/random", Array<Beer>::class.java,null,
            { response ->
                callback(response[0])
            },
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }

    fun search(beerName: String, callback: (result: List<Beer>) -> Unit) {
        val stringRequest = GsonRequest(
            "https://api.punkapi.com/v2/beers?beer_name=${beerName.replace(' ', '_')}", Array<Beer>::class.java,null,
            { response ->
                callback(response.toList())
            },
            {
                print("Something went wrong")
            })

        this.context.get()?.let { ApiClient.getInstance(it).addToRequestQueue(stringRequest) }
    }

    fun sendNotification(beer: Beer) {
        val ctx = context.get() ?: return  // TODO: Show error message.

        this.createNotificationChannel(ctx)

        val builder = NotificationCompat.Builder(ctx, "test_notification_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(beer.name)
            .setContentText(beer.description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(ctx)) {
            notify(beer.id, builder.build())
        }
    }

    private fun createNotificationChannel(ctx: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test notifications"
            val descriptionText = "Test description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("test_notification_channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}