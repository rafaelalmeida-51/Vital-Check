package com.example.vitalcheck.data.repository

import com.example.vitalcheck.data.local.dao.SintomaDao
import com.example.vitalcheck.data.local.entity.SintomasEntity
import kotlinx.coroutines.flow.Flow

class SintomasRepository(
    private val dao: SintomaDao
) {
    fun observeSintomas(): Flow<List<SintomasEntity>> {
        return dao.observeAll()
    }

    suspend fun addSintomas(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return

        dao.insert(
            SintomasEntity(
                timestampMillis = System.currentTimeMillis(),
                text = trimmed
            )
        )
    }
}