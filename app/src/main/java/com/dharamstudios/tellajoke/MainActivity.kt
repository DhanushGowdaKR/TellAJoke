package com.dharamstudios.tellajoke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dharamstudios.tellajoke.presentation.homeScreen.HomeScreen
import com.dharamstudios.tellajoke.presentation.homeScreen.HomeViewModel
import com.dharamstudios.tellajoke.presentation.ui.theme.TellAJokeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TellAJokeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text(
                                text = "Tell A Joke",
                                fontSize = 32.sp
//                                modifier = Modifier.padding(top = 64.dp, bottom = 32.dp, start = 16.dp, end= 16.dp)
                            )
                        })
                    }
                ) { innerPadding ->

                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    HomeScreen(
                        innerPadding = innerPadding,
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }
}