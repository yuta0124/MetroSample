package com.example.metrosample

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.metrosample.bottomNavigation.BottomNavigation
import com.example.metrosample.navigation.AppNavigationState
import com.example.metrosample.navigation.rememberAppNavigationState
import com.example.metrosample.screen.screen1.screen1
import com.example.metrosample.screen.screen2.screen2
import com.example.metrosample.screen.screen3.Screen3
import com.example.metrosample.screen.screen3.screen3
import com.example.metrosample.ui.theme.MetroSampleTheme
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.android.ActivityKey
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory

@ContributesIntoMap(AppScope::class, binding<Activity>())
@ActivityKey(MainActivity::class)
@Inject
class MainActivity(private val metroVmf: MetroViewModelFactory) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appNavigationState: AppNavigationState = rememberAppNavigationState()

            BackHandler(enabled = appNavigationState.canHandleBackPress) {
                appNavigationState.handleBackPress()
            }

            MetroSampleTheme {
                App(
                    metroVmf = metroVmf,
                    navigationState = appNavigationState,
                )
            }
        }
    }
}

@Composable
private fun App(
    metroVmf: MetroViewModelFactory,
    navigationState: AppNavigationState,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalMetroViewModelFactory provides metroVmf) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigation(
                    currentKey = navigationState.currentTab,
                    updateCurrentKey = { key ->
                        navigationState.selectTab(key)
                    },
                    resetBackStack = { key ->
                        navigationState.resetBackStack(key)
                    },
                )
            }
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                backStack = navigationState.currentBackStack,
                navigateTo = navigationState::navigateTo,
                onBackPressed = navigationState::handleBackPress,
            )
        }
    }
}

@Composable
private fun NavHost(
    backStack: NavBackStack<NavKey>,
    navigateTo: (NavKey) -> Unit,
    onBackPressed: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            screen1(
                navigateToScreen3 = {
                    navigateTo(Screen3)
                }
            )
            screen2(
                navigateToScreen3 = {
                    navigateTo(Screen3)
                }
            )
            screen3(
                onBackPressed = {
                    onBackPressed()
                },
            )
        }
    )
}
