package com.example.metrosample.screen.screen4

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
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel
import kotlinx.serialization.Serializable

@Serializable
data class Screen4NavKey(val title: String) : NavKey

fun EntryProviderScope<NavKey>.screen4(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    entry<Screen4NavKey> { args ->
        val viewModel: Screen4ViewModel =
            assistedMetroViewModel<Screen4ViewModel, Screen4ViewModel.Factory> { create(args.title) }

        Screen4(
            modifier = modifier,
            viewModel = viewModel,
            onBackPressed = onBackPressed,

        )


    }
}

@Composable
fun Screen4(
    viewModel: Screen4ViewModel,
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