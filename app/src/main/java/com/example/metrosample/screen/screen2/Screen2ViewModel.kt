package com.example.metrosample.screen.screen2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Screen2ViewModel: ViewModel() {
    val uiState: StateFlow<UiState>
        field = MutableStateFlow(UiState())

    data class UiState(
        val title: String = "Screen2",
    )
}