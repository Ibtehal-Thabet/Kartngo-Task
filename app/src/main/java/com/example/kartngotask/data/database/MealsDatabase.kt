package com.example.kartngotask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kartngotask.data.dao.MealsDao
import com.example.kartngotask.models.Meal


@Database(
    entities = [Meal::class], version = 1,
    exportSchema = true
)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun mealsDao(): MealsDao

    companion object {

        @Volatile
        private var INSTANCE: MealsDatabase? = null

        fun getInstance(context: Context): MealsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDatabase::class.java,
                    "MEALS_DATABASE"
                ).build().also { INSTANCE = it }
                INSTANCE = instance
                instance
            }
        }
    }
}