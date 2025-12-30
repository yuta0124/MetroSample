package com.example.metrosample.screen.screen3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ViewModelAssistedFactoryKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@AssistedInject
class Screen3ViewModel(@Assisted val title: String): ViewModel() {
    val uiState: StateFlow<UiState>
        field = MutableStateFlow(UiState(title = title))

    data class UiState(
        val title: String,
    )

    @AssistedFactory
    @ViewModelAssistedFactoryKey(Screen3ViewModel::class)
    @ContributesIntoMap(AppScope::class)
    fun interface Factory: ViewModelAssistedFactory {
        override fun create(extras: CreationExtras): Screen3ViewModel {
            val title: String = extras[TitleIdKey] ?: error("title の取得に失敗")

            return create(title)
        }

        fun create(@Assisted title: String): Screen3ViewModel
    }

    companion object {
        val TitleIdKey = object : CreationExtras.Key<String> {}
    }
}
