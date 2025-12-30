package com.example.metrosample.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

/**
 * NOTE: MetroAppComponentProviders は minSDK=28 以下では機能しない (ビルドエラーになる)
 */
@DependencyGraph(AppScope::class)
interface AppGraph: MetroAppComponentProviders, ViewModelGraph