package com.example.metrosample.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.metrosample.bottomNavigation.BottomNavKey
import com.example.metrosample.screen.component.ScaffoldTopAppBar

fun EntryProviderScope<NavKey>.screen1(
    modifier: Modifier = Modifier,
    navigationToScreen3: () -> Unit,
) {
    entry(BottomNavKey.Screen1) {
        Screen1(
            modifier = modifier,
            navigationToScreen3 = navigationToScreen3,
        )
    }
}

@Composable
private fun Screen1(
    modifier: Modifier = Modifier,
    navigationToScreen3: () -> Unit,
) {
    ScaffoldTopAppBar(title = "Screen1") {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Screen1",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = navigationToScreen3) {
                Text(text = "to Screen3")
            }
        }
    }
}