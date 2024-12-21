package com.example.ucp2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.MatakuliahDao
import com.example.ucp2.entity.Matakuliah

@Database(entities = [Matakuliah::class], version = 1, exportSchema = false)
abstract class KrsDatabaseMatakuliah : RoomDatabase(){

    abstract fun matakuliahDao(): MatakuliahDao

    companion object {
        @Volatile
        private  var Instance: KrsDatabaseMatakuliah? = null

        fun getDatabase(context: Context): KrsDatabaseMatakuliah {
            return  Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabaseMatakuliah::class.java,
                    "KrsDatabaseMatakuliah"
                ).build().also { Instance = it }

            }
        }
    }

}