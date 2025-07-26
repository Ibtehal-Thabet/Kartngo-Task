package com.example.kartngotask.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kartngotask.models.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeal(vararg mealItems: Meal)

    @Query("UPDATE mealsTable SET quantity = :quantity WHERE id = :id")
    fun updateMealQuantity(id: Int, quantity: Int)

    @Query("select * from mealsTable")
    fun getAllMeals(): LiveData<List<Meal>>
}