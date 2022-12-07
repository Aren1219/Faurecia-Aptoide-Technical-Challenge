package com.example.faurecia_aptoidetechnicalchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.faurecia_aptoidetechnicalchallenge.ui.MainViewModel
import com.example.faurecia_aptoidetechnicalchallenge.ui.NavGraphs
import com.example.faurecia_aptoidetechnicalchallenge.ui.theme.FaureciaAptoideTechnicalChallengeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaureciaAptoideTechnicalChallengeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainPage(viewModel = viewModel)
//                }
            }
        }
    }
}
