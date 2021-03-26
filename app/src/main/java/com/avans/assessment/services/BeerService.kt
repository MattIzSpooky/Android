package com.avans.assessment.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.avans.assessment.R
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.models.Beer
import com.avans.assessment.network.ApiClient
import com.avans.assessment.network.NetworkStatus
import java.lang.NullPointerException
import kotlin.jvm.Throws

class BeerService(ctx: Context) : BaseService(ctx) {
    private val networkStatus = NetworkStatus(ctx)
    private val settingsService = SettingsService(ctx);
    private val Key: String = "minimum_alcohol"

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "test_notification_channel"
    }

    @Throws(NullPointerException::class, NoInternetException::class)
    fun fetchBeers(
        page: Int,
        perPage: Int,
        onResponse: (result: List<Beer>) -> Unit,
        onError: (result: String) -> Unit
    ) {
        if (!networkStatus.isConnected()) throw NoInternetException()

        val client = ApiClient.getInstance(retrieveContextOrThrow())

        val alcoholPreference = settingsService.getPreferenceByKey(Key)

        var alcoholPercentage = ""
        if(alcoholPreference != "")
            alcoholPercentage = "abv_gt=$alcoholPreference"

        val url = client.createUrl("?$alcoholPercentage&page=$page&per_page=$perPage")
        val request = client.createGsonRequest<Array<Beer>>(url, onResponse = { beerArr ->
            onResponse(beerArr.toList())
        }, onError)

        client.addToRequestQueue(request)
    }

    @Throws(NullPointerException::class, NoInternetException::class)
    fun fetchBeer(
        id: String,
        onResponse: (result: Beer) -> Unit,
        onError: (result: String) -> Unit
    ) {
        if (!networkStatus.isConnected()) throw NoInternetException()

        val client = ApiClient.getInstance(retrieveContextOrThrow())

        val url = client.createUrl("/$id")
        val request = client.createGsonRequest<Array<Beer>>(url, onResponse = { beerArr ->
            onResponse(beerArr[0])
        }, onError)

        client.addToRequestQueue(request)
    }

    @Throws(NullPointerException::class, NoInternetException::class)
    fun fetchRandom(onResponse: (result: Beer) -> Unit,  onError: (result: String) -> Unit) {
        if (!networkStatus.isConnected()) throw NoInternetException()

        val client = ApiClient.getInstance(retrieveContextOrThrow())

        val url = client.createUrl("/random")
        val request = client.createGsonRequest<Array<Beer>>(url, onResponse = { beerArr ->
            onResponse(beerArr[0])
        }, onError)

        client.addToRequestQueue(request)
    }

    @Throws(NullPointerException::class, NoInternetException::class)
    fun search(beerName: String, onResponse: (result: List<Beer>) -> Unit, onError: (result: String) -> Unit) {
        if (!networkStatus.isConnected()) throw NoInternetException()

        val client = ApiClient.getInstance(retrieveContextOrThrow())

        val url = client.createUrl("?beer_name=${beerName.replace(' ', '_')}")
        val request = client.createGsonRequest<Array<Beer>>(url, onResponse = { beerArr ->
            onResponse(beerArr.toList())
        }, onError)

        client.addToRequestQueue(request)
    }

    @Throws(NullPointerException::class)
    fun sendNotification(beer: Beer) {
        val ctx = retrieveContextOrThrow()

        this.createNotificationChannel(ctx)

        val builder = NotificationCompat.Builder(ctx, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(beer.name)
            .setContentText(beer.description)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(beer.description)
            )
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
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
}