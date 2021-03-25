package com.avans.assessment.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

class ContactService(ctx: Context) {
    private val context = WeakReference(ctx)

    fun getContacts(): List<String> {
        val ctx = context.get() ?: return ArrayList() // TODO: Show error message.

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                ctx,
                Manifest.permission.READ_CONTACTS
            ) -> {
                val cursor = ctx.contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
                ) ?: return ArrayList()

                val names = ArrayList<String>()

                while (cursor.moveToNext()) {
                    val name: String =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                    names.add(name)
                }

                cursor.close()

                return names
            }
        }

        return ArrayList()
    }
}