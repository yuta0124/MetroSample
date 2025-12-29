package com.example.metrosample.screen.screen1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metrosample.repository.Repository
import com.example.metrosample.repository.RepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Screen1ViewModel : ViewModel() {
    // TODO: DI
    private val repository: Repository = RepositoryImpl()

    val uiState: StateFlow<UiState>
        field = MutableStateFlow(UiState())

    init {
        viewModelScope.launch {
            val data: List<String> = repository.getData()
            uiState.update {
                it.copy(data = data)
            }
        }
    }

    data class UiState(
        val title: String = "Screen1",
        val data: List<String> = emptyList(),
    )
}