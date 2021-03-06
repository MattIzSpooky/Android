package com.avans.assessment.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.ui.Routes
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.ContactListItem
import com.avans.assessment.ui.components.general.Centered
import com.avans.assessment.viewmodels.ContactsViewModel

@Composable
fun ContactsScreen(context: Context, navController: NavHostController) {
    val contactsViewModel = ContactsViewModel(context)

    val requestPermissionLauncher = createRequestPermissionLauncher(context, navController)

    Scaffold(
        bottomBar = { BottomNavBar(context, navController) },
        topBar = {
            TopAppBar(title = { Text("Contacts") })
        }
    ) {
        val error = contactsViewModel.error

        if (error != null) {
            ErrorScreen(error)
            return@Scaffold
        }

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
                contactsViewModel.loadContacts()

                if (contactsViewModel.contacts.isEmpty()) {
                    Centered {
                        Text("No contacts")
                    }
                    return@Scaffold
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        items(contactsViewModel.contacts) {
                            ContactListItem(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun createRequestPermissionLauncher(
    context: Context,
    navController: NavHostController
): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            Toast
                .makeText(context, "Contact page will work.", Toast.LENGTH_SHORT)
                .show()

            // Reroute to current page to force checking the permission again and showing the data if changed.
            navController.popBackStack()
            navController.navigate(Routes.CONTACTS)
        } else {
            Toast
                .makeText(
                    context, "Contacts will not work until it has been accepted.",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }
}
