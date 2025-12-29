package com.example.metrosample.screen.screen3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        val viewModel: Screen3ViewModel = Screen3ViewModel()
        Screen3(
            viewModel = viewModel,
            modifier = modifier,
            onBackPressed = onBackPressed,
        )
    }
}

@Composable
private fun Screen3(
    viewModel: Screen3ViewModel,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScaffoldTopAppBar(
        title = uiState.title,
        onBackPressed = onBackPressed,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = uiState.title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
