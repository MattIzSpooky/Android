package com.avans.assessment.ui.screens

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.general.Centered
import com.avans.assessment.ui.components.general.CenteredProgressIndicator
import com.avans.assessment.ui.components.general.NetworkImage
import com.avans.assessment.ui.theme.Typography
import com.avans.assessment.viewmodels.BeerViewModel

@Composable
fun DetailScreen(context: Context, navController: NavHostController, id: String?) {
    if (id == null) {
        CenteredProgressIndicator()
        return
    }
    val beerViewModel = BeerViewModel(context, id)

    Scaffold(
        bottomBar = { BottomNavBar(context, navController) },
        topBar = {
            TopAppBar(
                title = { Text("Beer") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back")
                    }
                },
            )
        }
    ) {
        val error = beerViewModel.error
        val beer = beerViewModel.beer

        if (error != null) {
            ErrorScreen(error)
            return@Scaffold
        }

        if (beer == null) {
            CenteredProgressIndicator()
            return@Scaffold
        }

        BeerDetail(context, beer)
    }
}


@Composable
fun DetailScreenWithoutBottom(context: Context, navController: NavHostController, id: String?) {
    if (id == null) {
        CenteredProgressIndicator()
        return
    }
    val beerViewModel = BeerViewModel(context, id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Beer") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back")
                    }
                },
            )
        }
    ) {
        if (beerViewModel.beer == null) {
            CenteredProgressIndicator()
        } else {
            BeerDetail(context, beerViewModel.beer!!)
        }
    }
}

@Composable
fun BeerDetail(context: Context, beer: Beer) {
    val configuration = LocalConfiguration.current

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(rememberScrollState())
        .padding(bottom = 55.dp)) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                BeerDetailLandScape(context, beer)
            }
            else -> {
                BeerDetailPortrait(context, beer)
            }
        }
    }
}

@Composable
private fun BeerDetailPortrait(context: Context, beer: Beer) {
    Column(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
    ) {
        Card(
            elevation = 2.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            backgroundColor = Color.LightGray
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(beer.name, style = Typography.h6)
                Text(beer.tagline, style = Typography.subtitle1)
                Text(beer.firstBrewed, style = Typography.caption)
            }
        }

        Card(
            elevation = 2.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            backgroundColor = Color.LightGray
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text("Description:", style = Typography.h6)
                Text(beer.description, style = Typography.body1)
            }
        }

        beer.imageUrl?.also {
            Centered {
                NetworkImage(context, url = beer.imageUrl, Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
private fun BeerDetailLandScape(context: Context, beer: Beer) {
    Row {
        beer.imageUrl?.also {
            Column {
                NetworkImage(context, url = beer.imageUrl, Modifier.padding(10.dp))
            }

        }

        Column {
            Card(
                elevation = 2.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.LightGray
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(beer.name, style = Typography.h6)
                    Text(beer.tagline, style = Typography.subtitle1)
                    Text(beer.firstBrewed, style = Typography.caption)
                }
            }

            Card(
                elevation = 2.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.LightGray
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text("Description:", style = Typography.h6)
                    Text(beer.description, style = Typography.body1)
                }
            }
        }
    }
}