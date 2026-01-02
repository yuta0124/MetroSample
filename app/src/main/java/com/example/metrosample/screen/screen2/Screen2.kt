package com.example.metrosample.screen.screen2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.metrosample.bottomNavigation.BottomNavKey
import com.example.metrosample.screen.component.ScaffoldTopAppBar
import dev.zacsweers.metrox.viewmodel.metroViewModel

fun EntryProviderScope<NavKey>.screen2(
    navigateToScreen4: (title: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    entry(BottomNavKey.Screen2NavKey) {
        val viewModel: Screen2ViewModel = metroViewModel()

        Screen2(
            viewModel = viewModel,
            navigateToScreen4 = navigateToScreen4,
            modifier = modifier,
        )
    }
}

@Composable
private fun Screen2(
    viewModel: Screen2ViewModel,
    navigateToScreen4: (title: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScaffoldTopAppBar(
        title = uiState.title,
        onBackPressed = null,
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
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { navigateToScreen4("from screen2") }) {
                Text("navigate to screen4")
            }
        }
    }
}
