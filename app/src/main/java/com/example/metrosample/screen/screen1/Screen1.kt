package com.example.metrosample.screen.screen1

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.metrosample.bottomNavigation.BottomNavKey
import com.example.metrosample.screen.component.ScaffoldTopAppBar
import dev.zacsweers.metrox.viewmodel.metroViewModel

fun EntryProviderScope<NavKey>.screen1(
    modifier: Modifier = Modifier,
    navigateToScreen3: (title: String) -> Unit,
) {
    entry(BottomNavKey.Screen1NavKey) {
        val viewModel: Screen1ViewModel = metroViewModel()

        Screen1(
            modifier = modifier,
            viewModel = viewModel,
            navigateToScreen3 = navigateToScreen3,
        )
    }
}

@Composable
private fun Screen1(
    viewModel: Screen1ViewModel,
    navigateToScreen3: (title: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScaffoldTopAppBar(title = uiState.title) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(uiState.data) { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(width = 1.dp, color = Color.Black)
                        .clickable { navigateToScreen3(title) },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = title)
                }
            }
        }
    }
}