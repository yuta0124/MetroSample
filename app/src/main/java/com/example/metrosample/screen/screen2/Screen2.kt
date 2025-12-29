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

fun EntryProviderScope<NavKey>.screen2(
    modifier: Modifier = Modifier,
    navigateToScreen3: () -> Unit,
) {
    entry(BottomNavKey.Screen2) {
        val viewModel: Screen2ViewModel = Screen2ViewModel()

        Screen2(
            viewModel = viewModel,
            modifier = modifier,
            navigateToScreen3 = navigateToScreen3,
        )
    }
}

@Composable
private fun Screen2(
    viewModel: Screen2ViewModel,
    navigateToScreen3: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScaffoldTopAppBar(
        title = uiState.title,
        onBackPressed = null,
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = uiState.title,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = navigateToScreen3) {
                Text(text = "to Screen3")
            }
        }
    }
}
