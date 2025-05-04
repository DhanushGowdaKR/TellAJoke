package com.dharamstudios.tellajoke.presentation.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dharamstudios.tellajoke.presentation.homeScreen.components.AnimatedText
import com.dharamstudios.tellajoke.presentation.homeScreen.components.FlowRowExpander
import com.dharamstudios.tellajoke.presentation.homeScreen.components.HelperFlowRows
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    innerPadding: PaddingValues
) {
    val homeState = homeViewModel.homeState.collectAsState().value

    var isCategoriesExpanded by rememberSaveable {
        mutableStateOf(true)
    }
    var isTypeExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var isBlackListFlagsExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
    ) {
        FlowRowExpander(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Categories",
            state = isCategoriesExpanded,
            onClick = {
                isCategoriesExpanded = !isCategoriesExpanded
            }
        )
        AnimatedVisibility(isCategoriesExpanded) {
            HelperFlowRows(
                originalList = listOf(
                    "Miscellaneous",
                    "Programming",
                    "Dark",
                    "Pun",
                    "Spooky",
                    "Christmas"
                ),
                addedList = homeState.category ?: emptyList(),
                onClick = { item ->
                    homeViewModel.event(HomeEvents.OnCategoryChange(item))
                }
            )
            Spacer(Modifier.height(16.dp))
        }
        FlowRowExpander(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Type",
            state = isTypeExpanded,
            onClick = {
                isTypeExpanded = !isTypeExpanded
            }
        )
        AnimatedVisibility(isTypeExpanded) {
            HelperFlowRows(
                originalList = listOf("Single", "Two Part"),
                addedList = homeState.type ?: emptyList(),
                onClick = { item ->
                    homeViewModel.event(HomeEvents.OnTypeChange(item))
                }
            )
            Spacer(Modifier.height(16.dp))
        }
        FlowRowExpander(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Black List Flags",
            state = isBlackListFlagsExpanded,
            onClick = {
                isBlackListFlagsExpanded = !isBlackListFlagsExpanded
            }
        )
        AnimatedVisibility(isBlackListFlagsExpanded) {
            HelperFlowRows(
                originalList = listOf("NSFW", "Religious", "Political", "Racist", "Explicit"),
                addedList = homeState.blacklistFlags ?: emptyList(),
                onClick = { item ->
                    homeViewModel.event(HomeEvents.OnBlacklistFlagsChange(item))
                }
            )
            Spacer(Modifier.height(16.dp))
        }
        Button(
            onClick = {
                homeViewModel.event(HomeEvents.OnGetJoke)
            }
        ) {
            Text("Get Joke")
        }
        Spacer(Modifier.height(32.dp))
//        Text("JOKE ")
        AnimatedText(text = homeState.joke ?: "")
    }
}

@Composable
fun JokeCardComposable() {

    val yRotation = remember { Animatable(0f) }
    val cs = rememberCoroutineScope()

    val isFlipped = remember { derivedStateOf { yRotation.value <= -90f } }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp)
            .graphicsLayer {
                rotationY = yRotation.value
                cameraDistance = 12 * density // Key for 3D perspective
            }
            .background(if (isFlipped.value) Color.Red else Color.Blue)
            .clickable {
                cs.launch {
                    yRotation.animateTo(
                        targetValue = if (isFlipped.value) 0f else -180f,
                        animationSpec = keyframes {
                            durationMillis = 1000
                            if (!isFlipped.value) {
                                -40f at 100
                                -10f at 450
                                -180f at 1000
                            } else {
                                -360f at 1000
                            }
                        }
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (isFlipped.value) {
            Text("Click to Flip", color = Color.White, fontWeight = FontWeight.Bold)
        }

    }
}
