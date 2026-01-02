package com.example.metrosample.repository

interface Repository {
    suspend fun getData(): List<String>
}