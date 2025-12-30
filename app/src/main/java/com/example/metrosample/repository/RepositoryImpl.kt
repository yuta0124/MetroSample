package com.example.metrosample.repository

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Inject
@ContributesBinding(AppScope::class)
class RepositoryImpl : Repository {
    private val data = listOf(
        "data1",
        "data2",
        "data3",
        "data4",
    )

    override suspend fun getData(): List<String> = withContext(Dispatchers.IO) {
        return@withContext data
    }
}