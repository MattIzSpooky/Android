package com.avans.assessment.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.avans.assessment.exceptions.NoPermissionException

class ContactService(ctx: Context) : BaseService(ctx) {
    @Throws(NullPointerException::class, NoPermissionException::class)
    fun getContacts(): List<String> {
        val ctx = retrieveContextOrThrow()

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

        throw NoPermissionException()
    }
}