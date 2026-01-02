package com.example.metrosample.screen.screen2

import androidx.lifecycle.ViewModel
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Inject
@ViewModelKey(Screen2ViewModel::class)
@ContributesIntoMap(AppScope::class)
class Screen2ViewModel : ViewModel() {
    val uiState: StateFlow<UiState>
        field = MutableStateFlow(UiState())

    data class UiState(
        val title: String = "Screen2",
    )
}