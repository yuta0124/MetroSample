package com.example.metrosample

import android.app.Application
import com.example.metrosample.di.AppGraph
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication

class App : Application(), MetroApplication {
    override val appComponentProviders: MetroAppComponentProviders by lazy { createGraph<AppGraph>() }
}