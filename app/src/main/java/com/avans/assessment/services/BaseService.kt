package com.avans.assessment.services

import android.content.Context
import java.lang.ref.WeakReference

abstract class BaseService(ctx: Context) {
    private val context = WeakReference(ctx)

    @Throws(NullPointerException::class)
    fun retrieveContextOrThrow(): Context {
        return context.get() ?: throw NullPointerException("Context is null")
    }
}
