package com.example.metrosample.repository

class RepositoryImpl : Repository {
    private val data = listOf(
        "data1",
        "data2",
        "data3",
        "data4",
    )

    override fun getData(): List<String> = data
}