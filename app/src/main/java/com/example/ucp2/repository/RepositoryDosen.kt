package com.example.ucp2.repository

import com.example.ucp2.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    suspend fun deleteDosen(dosen: Dosen)

    suspend fun updateDosen(dosen: Dosen)
}