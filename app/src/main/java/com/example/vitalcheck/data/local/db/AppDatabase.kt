package com.example.vitalcheck.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vitalcheck.data.local.dao.SintomaDao
import com.example.vitalcheck.data.local.entity.SintomasEntity

@Database(
    entities = [SintomasEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sintomaDao(): SintomaDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vitalcheck.db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}