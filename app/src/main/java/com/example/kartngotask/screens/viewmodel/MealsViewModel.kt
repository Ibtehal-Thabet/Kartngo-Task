package com.example.kartngotask.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kartngotask.data.database.MealsDatabase
import com.example.kartngotask.data.repository.MealRepository
import com.example.kartngotask.models.Meal
import kotlinx.coroutines.launch

class MealsViewModel(application: Application) : AndroidViewModel(application) {
    private val mealsRepository: MealRepository
    val allMeals: LiveData<List<Meal>>

    private val _totalQuantity = MutableLiveData<Int>()
    val totalQuantity: LiveData<Int> get() = _totalQuantity

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    init {
        val mealDao = MealsDatabase.getInstance(application).mealsDao()
        mealsRepository = MealRepository(mealDao)
        allMeals = mealsRepository.allMeals
        insertMeals()

        allMeals.observeForever { mealList ->
            val total = mealList.sumOf { it.mealQuantity }
            var sumPrice = 0.0
            mealList.forEach {
                sumPrice += it.mealPrice*it.mealQuantity
            }
            _totalPrice.value = sumPrice
            _totalQuantity.value = total
        }
    }

    private fun insertMeals(){
        viewModelScope.launch {
            val mealList = listOf(
                Meal(mealTitle = "Classic Burger", mealImage = "classic_burger", mealPrice = 20.5),
                Meal(mealTitle = "Chess Burger", mealImage = "chess_burger", mealPrice = 25.5),
                Meal(mealTitle = "Burger Double", mealImage = "burger_double", mealPrice = 28.5),
                Meal(mealTitle = "Veggie Burger", mealImage = "veggie_burger", mealPrice = 30.5),
                Meal(mealTitle = "Smash Burger", mealImage = "smash_burger", mealPrice = 18.0),
                Meal(
                    mealTitle = "Burger and Fries",
                    mealImage = "burger_and_fries",
                    mealPrice = 24.0
                ),
                Meal(
                    mealTitle = "Combo Burger",
                    mealImage = "double_burger_combo",
                    mealPrice = 27.0
                ),
                Meal(mealTitle = "Chicken Burger", mealImage = "chicken_burger", mealPrice = 17.5),
                Meal(
                    mealTitle = "Chicken Burger Double",
                    mealImage = "double_chicken_burger",
                    mealPrice = 19.5
                ),
                Meal(mealTitle = "HotDog", mealImage = "hot_dog", mealPrice = 16.5),
                Meal(mealTitle = "Fries Bucket", mealImage = "fries", mealPrice = 8.5)
            )
            mealList.forEach {
                mealsRepository.insertMeals(it)
            }
        }
    }

    fun increaseMealQuantity(meal: Meal) {
        val newQuantity = meal.mealQuantity + 1
        updateMealQuantity(meal.id, newQuantity)
    }

    fun decreaseMealQuantity(meal: Meal) {
        val newQuantity = (meal.mealQuantity - 1).coerceAtLeast(0)
        updateMealQuantity(meal.id, newQuantity)
    }

    private fun updateMealQuantity(id: Int, quantity: Int) {
        viewModelScope.launch {
            mealsRepository.updateMealQuantity(id, quantity)

        }
    }
}