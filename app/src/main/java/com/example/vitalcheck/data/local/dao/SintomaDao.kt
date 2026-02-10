package com.example.vitalcheck.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vitalcheck.data.local.entity.SintomasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SintomaDao {

    @Insert
    suspend fun insert(sintoma: SintomasEntity)

    @Query("SELECT * FROM sintomas ORDER BY timestampMillis DESC")
    fun observeAll(): Flow<List<SintomasEntity>>
}