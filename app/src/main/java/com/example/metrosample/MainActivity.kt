package com.example.metrosample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.metrosample.bottomNavigation.BottomNavKey
import com.example.metrosample.bottomNavigation.BottomNavigation
import com.example.metrosample.screen.Screen3
import com.example.metrosample.screen.screen1
import com.example.metrosample.screen.screen2
import com.example.metrosample.screen.screen3
import com.example.metrosample.ui.theme.MetroSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tab1BackStack = rememberNavBackStack(BottomNavKey.Screen1)
            val tab2BackStack = rememberNavBackStack(BottomNavKey.Screen2)
            var currentKey by retain {
                mutableStateOf<BottomNavKey>(BottomNavKey.Screen1)
            }
            val currentBackStack = when (currentKey) {
                BottomNavKey.Screen1 -> tab1BackStack
                BottomNavKey.Screen2 -> tab2BackStack
            }

            val resetBackStack: (BottomNavKey) -> Unit = {
                when (it) {
                    BottomNavKey.Screen1 -> {
                        if (tab1BackStack.size > 1) tab1BackStack.removeAll { key ->
                            key != tab1BackStack.first()
                        }
                    }

                    BottomNavKey.Screen2 -> {
                        if (tab2BackStack.size > 1) tab2BackStack.removeAll { key ->
                            key != tab2BackStack.first()
                        }
                    }
                }
            }

            val addToBackStack: (NavKey) -> Unit = {
                when (currentKey) {
                    BottomNavKey.Screen1 -> tab1BackStack.add(it)
                    BottomNavKey.Screen2 -> tab2BackStack.add(it)
                }
            }

            val onBackPreseed: () -> Unit = {
                onHandleBackPressed(
                    currentBottomKey = currentKey,
                    tab2BackStackSize = tab2BackStack.size,
                    onSetTab1Key = {
                        currentKey = BottomNavKey.Screen1
                    },
                    onPopTab1BackStack = {
                        tab1BackStack.removeLastOrNull()
                    },
                    onPopTab2BackStack = {
                        tab2BackStack.removeLastOrNull()
                    },
                )
            }

            MetroSampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(
                            currentKey = currentKey,
                            updateCurrentKey = { key ->
                                currentKey = key
                            },
                            resetBackStack = resetBackStack,
                        )
                    }
                ) { innerPadding ->
                    App(
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                        startBackStack = currentBackStack,
                        addToBackStack = addToBackStack,
                        onBackPreseed = onBackPreseed,
                    )
                }
            }

            BackHandler(enabled = true) {
                onHandleBackPressed(
                    currentBottomKey = currentKey,
                    tab2BackStackSize = tab2BackStack.size,
                    onSetTab1Key = {
                        currentKey = BottomNavKey.Screen1
                    },
                    onPopTab1BackStack = {
                        tab1BackStack.removeLastOrNull()
                    },
                    onPopTab2BackStack = {
                        tab2BackStack.removeLastOrNull()
                    }
                )
            }
        }
    }

    private fun onHandleBackPressed(
        currentBottomKey: BottomNavKey,
        tab2BackStackSize: Int,
        onSetTab1Key: () -> Unit,
        onPopTab1BackStack: () -> Unit,
        onPopTab2BackStack: () -> Unit,
    ) {
        when (currentBottomKey) {
            BottomNavKey.Screen1 -> onPopTab1BackStack()
            BottomNavKey.Screen2 -> {
                if (tab2BackStackSize > 1) {
                    onPopTab2BackStack()
                } else {
                    onSetTab1Key()
                }
            }
        }
    }
}

@Composable
private fun App(
    startBackStack: NavBackStack<NavKey>,
    addToBackStack: (NavKey) -> Unit,
    onBackPreseed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = startBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            screen1(
                navigationToScreen3 = {
                    addToBackStack(Screen3)
                }
            )
            screen2(
                navigateToScreen3 = {
                    addToBackStack(Screen3)
                }
            )
            screen3(
                onBackPreseed = onBackPreseed,
            )
        }
    )
}