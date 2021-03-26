package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.avans.assessment.exceptions.NoPermissionException
import com.avans.assessment.services.ContactService

class ContactsViewModel(ctx: Context) : ApplicationViewModel() {
    private val contactsService = ContactService(ctx)

    var contacts: List<String> by mutableStateOf(listOf())
        private set

    init {
        try {
            contacts = contactsService.getContacts()
        } catch (nullPointerException: NullPointerException) {
            error = nullPointerException.message
        } catch (noPermissionException: NoPermissionException) {
            error = noPermissionException.message
        }
    }
}