package com.avans.assessment.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.avans.assessment.ui.components.general.CenteredProgressIndicator
import com.avans.assessment.viewmodels.RandomBeerViewModel

class RandomScreen() : Fragment() {
    private lateinit var randomBeerViewModel: RandomBeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Random beer") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    activity?.onBackPressed()
                                }) {
                                    Icon(Icons.Filled.ArrowBack, "back")
                                }
                            },
                        )
                    }
                ) {
                    val error = randomBeerViewModel.error
                    val beer = randomBeerViewModel.beer

                    if (error != null) {
                        ErrorScreen(error)
                        return@Scaffold
                    }

                    if (beer == null) {
                        CenteredProgressIndicator()
                        return@Scaffold
                    }

                    if (container == null) {
                        ErrorScreen("Container is empty")
                        return@Scaffold
                    }

                    BeerDetail(container.context, beer)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        randomBeerViewModel = RandomBeerViewModel(context)
    }
}