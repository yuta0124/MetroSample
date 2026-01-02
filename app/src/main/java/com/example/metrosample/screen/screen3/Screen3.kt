package com.example.metrosample.screen.screen3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.metrosample.screen.component.ScaffoldTopAppBar
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel
import kotlinx.serialization.Serializable

@Serializable
data class Screen3NavKey(val title: String) : NavKey

fun EntryProviderScope<NavKey>.screen3(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    entry<Screen3NavKey> { args ->
        val viewModel: Screen3ViewModel = assistedMetroViewModel(
            extras = remember(args.title) {
                MutableCreationExtras().apply { set(Screen3ViewModel.TitleIdKey, args.title) }
            }
        )
        Screen3(
            modifier = modifier,
            viewModel = viewModel,
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
