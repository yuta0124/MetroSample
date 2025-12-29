package com.example.metrosample.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

// TODO: api28 以前だと機能しないかも 
@DependencyGraph(AppScope::class)
interface AppGraph: MetroAppComponentProviders, ViewModelGraph