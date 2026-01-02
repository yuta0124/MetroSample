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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
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
import com.example.metrosample.screen.screen3.Screen3NavKey
import com.example.metrosample.screen.screen3.screen3
import com.example.metrosample.screen.screen4.Screen4NavKey
import com.example.metrosample.screen.screen4.screen4
import com.example.metrosample.ui.theme.MetroSampleTheme
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory

class MainActivity : ComponentActivity() {
    private val metroVmf: MetroViewModelFactory by lazy { viewModelFactory }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = metroVmf

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
                navigateToScreen3 = { title ->
                    navigateTo(Screen3NavKey(title))
                }
            )
            screen2(
                navigateToScreen4 = { title ->
                    navigateTo(Screen4NavKey(title))
                }
            )
            screen3(
                onBackPressed = {
                    onBackPressed()
                },
            )
            screen4(
                onBackPressed = {
                    onBackPressed()
                },
            )
        }
    )
}
