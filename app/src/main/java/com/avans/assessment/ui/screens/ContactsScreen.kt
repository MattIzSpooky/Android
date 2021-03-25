package com.avans.assessment.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.viewmodels.ContactsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.avans.assessment.ui.components.Centered

@Composable
fun ContactsScreen(context: Context, navController: NavHostController) {
    val contactsViewModel = ContactsViewModel(context)

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast
                    .makeText(context,"Contact page will work.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast
                    .makeText(context,"Contacts will not work until it has been accepted.",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }

    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        topBar = {
            TopAppBar(title = { Text("Contacts") })
        }
    ) {


        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)

                Centered {
                    Text("No permission.")
                }
            }
            else -> {
                if (contactsViewModel.contacts.isEmpty()) {
                    Centered {
                        Text("No contacts")
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn() {
                            items(contactsViewModel.contacts) {
                                Text(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
