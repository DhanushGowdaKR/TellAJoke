package com.dharamstudios.tellajoke.presentation.homeScreen.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HelperFlowRows(
    originalList: List<String>,
    addedList: List<String>,
    onClick: (String) -> Unit
) {



    FlowRow {
        originalList.forEach {
            FilterChip(
                modifier = Modifier.padding(horizontal = 8.dp),
                label = {
                    Text(it)
                },
                selected = addedList.contains(it),
                onClick = {
                    onClick(it)
                }
            )
        }
    }

}