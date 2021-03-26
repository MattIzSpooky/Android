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
import com.avans.assessment.ui.components.CenteredProgressIndicator
import com.avans.assessment.viewmodels.BeerViewModel
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
                            title = { Text("Random beer")},
                            navigationIcon = {
                                IconButton(onClick = {
                                    activity?.onBackPressed()
                                }) {
                                    Icon(Icons.Filled.ArrowBack, "back")
                                }
                            },
                        )
                    }
                ){
                    if (randomBeerViewModel.beer == null) {
                        CenteredProgressIndicator()
                    } else {
                        // TODO fix non null asserted
                        BeerDetail(container!!.context, randomBeerViewModel.beer!!)
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        randomBeerViewModel = RandomBeerViewModel(context)
    }
}