package com.example.kartngotask.data.repository

import androidx.lifecycle.LiveData
import com.example.kartngotask.data.dao.MealsDao
import com.example.kartngotask.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealRepository(private val mealsDao: MealsDao) {

    suspend fun insertMeals(meal: Meal){
        withContext(Dispatchers.IO) {
            mealsDao.insertMeal(meal)
        }
    }

    suspend fun updateMealQuantity(id: Int, quantity: Int) {
        withContext(Dispatchers.IO) {
            mealsDao.updateMealQuantity(id, quantity)
        }
    }

    val allMeals: LiveData<List<Meal>> = mealsDao.getAllMeals()

}