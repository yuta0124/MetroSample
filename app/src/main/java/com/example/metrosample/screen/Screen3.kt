package com.example.metrosample.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.metrosample.screen.component.ScaffoldTopAppBar
import kotlinx.serialization.Serializable

@Serializable
data object Screen3 : NavKey

fun EntryProviderScope<NavKey>.screen3(
    modifier: Modifier = Modifier,
    onBackPreseed: () -> Unit,
) {
    entry(Screen3) {
        Screen3(
            modifier = modifier,
            onBackPreseed = onBackPreseed,
        )
    }
}

@Composable
private fun Screen3(
    onBackPreseed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScaffoldTopAppBar(
        title = "Screen3",
        onBackPreseed = onBackPreseed,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Screen3",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
