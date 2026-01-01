package com.example.metrosample

import android.app.Application
import android.content.Context
import com.example.metrosample.di.AppGraph
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory

class App : Application() {
    val appGraph: AppGraph by lazy { createGraph<AppGraph>() }
}

val Context.viewModelFactory: MetroViewModelFactory
    get() = (applicationContext as App).appGraph.viewModelFactory