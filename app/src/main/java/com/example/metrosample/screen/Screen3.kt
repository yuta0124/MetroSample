package com.example.metrosample.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    onBackPressed: () -> Unit,
) {
    entry(Screen3) {
        Screen3(
            modifier = modifier,
            onBackPressed = onBackPressed,
        )
    }
}

@Composable
private fun Screen3(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScaffoldTopAppBar(
        title = "Screen3",
        onBackPressed = onBackPressed,
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize().padding(innerPadding),
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
