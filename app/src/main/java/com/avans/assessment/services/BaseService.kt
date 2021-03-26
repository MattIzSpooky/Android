package com.avans.assessment.services

import android.content.Context
import com.avans.assessment.network.NetworkStatus
import java.lang.NullPointerException
import java.lang.ref.WeakReference
import kotlin.jvm.Throws

abstract class BaseService(ctx: Context) {
    private val context = WeakReference(ctx)

    @Throws(NullPointerException::class)
    fun retrieveContextOrThrow(): Context {
        return context.get() ?: throw NullPointerException("Context is null")
    }
}
