package com.example.metrosample.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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