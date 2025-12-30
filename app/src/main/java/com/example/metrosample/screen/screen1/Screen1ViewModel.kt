package com.example.metrosample.screen.screen1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metrosample.repository.Repository
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Inject
@ViewModelKey(Screen1ViewModel::class)
@ContributesIntoMap(AppScope::class)
class Screen1ViewModel(private val repository: Repository) : ViewModel() {
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