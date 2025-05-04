package com.dharamstudios.tellajoke.presentation.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FlowRowExpander(
    modifier: Modifier = Modifier,
    text: String,
    state: Boolean,
    onClick: () -> Unit
) {
    Row(modifier) {
        Text(text = text)
        Icon(
            modifier = Modifier.clickable {
                onClick.invoke()
            },
            imageVector = if (state) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = ""
        )
    }
}