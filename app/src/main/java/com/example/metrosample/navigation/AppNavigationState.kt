package com.example.metrosample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.metrosample.bottomNavigation.BottomNavKey

class AppNavigationState(
    private val tab1BackStack: NavBackStack<NavKey>,
    private val tab2BackStack: NavBackStack<NavKey>,
    initialTab: BottomNavKey,
) {
    var currentTab: BottomNavKey by mutableStateOf(initialTab)
        private set

    val currentBackStack: NavBackStack<NavKey>
        get() = when (currentTab) {
            BottomNavKey.Screen1NavKey -> tab1BackStack
            BottomNavKey.Screen2NavKey -> tab2BackStack
        }

    val canHandleBackPress: Boolean
        get() = currentTab != BottomNavKey.Screen1NavKey || tab1BackStack.size > 1

    fun selectTab(tab: BottomNavKey) {
        currentTab = tab
    }

    fun resetBackStack(tab: BottomNavKey) {
        val backStack = when (tab) {
            BottomNavKey.Screen1NavKey -> tab1BackStack
            BottomNavKey.Screen2NavKey -> tab2BackStack
        }
        if (backStack.size > 1) {
            backStack.removeAll { it != backStack.first() }
        }
    }

    fun navigateTo(key: NavKey) {
        currentBackStack.add(key)
    }

    fun handleBackPress(): Boolean {
        return when (currentTab) {
            BottomNavKey.Screen1NavKey -> {
                if (tab1BackStack.size > 1) {
                    tab1BackStack.removeLastOrNull()
                    true
                } else {
                    false
                }
            }

            BottomNavKey.Screen2NavKey -> {
                if (tab2BackStack.size > 1) {
                    tab2BackStack.removeLastOrNull()
                    true
                } else {
                    currentTab = BottomNavKey.Screen1NavKey
                    true
                }
            }
        }
    }
}

@Composable
fun rememberAppNavigationState(
    initialTab: BottomNavKey = BottomNavKey.Screen1NavKey,
): AppNavigationState {
    val tab1BackStack = rememberNavBackStack(BottomNavKey.Screen1NavKey)
    val tab2BackStack = rememberNavBackStack(BottomNavKey.Screen2NavKey)

    return retain(tab1BackStack, tab2BackStack) {
        AppNavigationState(
            tab1BackStack = tab1BackStack,
            tab2BackStack = tab2BackStack,
            initialTab = initialTab,
        )
    }
}

