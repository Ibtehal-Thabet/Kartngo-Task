package com.example.kartngotask.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "mealsTable", indices = [Index(value = ["title"], unique = true)])
class Meal(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title") val mealTitle: String,
    @ColumnInfo(name = "price") val mealPrice: Double,
    @ColumnInfo(name = "image") val mealImage: String,
    @ColumnInfo(name = "quantity") val mealQuantity: Int = 0,

    )