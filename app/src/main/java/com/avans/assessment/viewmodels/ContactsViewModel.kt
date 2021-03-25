package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.services.ContactService

class ContactsViewModel(ctx: Context) : ViewModel() {
    private val contactsService = ContactService(ctx)

    var contacts: List<String> by mutableStateOf(listOf())
        private set

    init {
        contacts = contactsService.getContacts()
    }
}