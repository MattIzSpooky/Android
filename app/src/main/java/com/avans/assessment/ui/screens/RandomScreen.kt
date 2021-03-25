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

class RandomScreen() : Fragment() {
    private lateinit var beerViewModel: BeerViewModel

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
                    if (beerViewModel.beer == null) {
                        CenteredProgressIndicator()
                    } else {
                        BeerDetail(beerViewModel.beer!!)
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        beerViewModel = BeerViewModel(context, "")
    }

    override fun onStart() {
        super.onStart()

        beerViewModel.loadRandomBeer()
    }
}