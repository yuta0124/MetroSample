package com.example.metrosample.bottomNavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomNavigation(
    currentKey: BottomNavKey,
    updateCurrentKey: (BottomNavKey) -> Unit,
    resetBackStack: (BottomNavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        BottomNavKey.keys.forEach { key ->
            NavigationBarItem(
                selected = key == currentKey,
                onClick = {
                    if (key != currentKey) {
                        updateCurrentKey(key)
                    } else {
                        resetBackStack(key)
                    }
                },
                icon = {
                    Icon(
                        imageVector = key.icon,
                        contentDescription = null,
                    )
                },
                label = { Text(key.label) },
            )
        }
    }
}