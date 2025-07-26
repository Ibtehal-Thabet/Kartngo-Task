package com.example.kartngotask.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kartngotask.databinding.MealCardBinding
import com.example.kartngotask.models.Meal
import com.example.kartngotask.screens.viewmodel.MealsViewModel

class MealsAdapter(private val viewModel: MealsViewModel): ListAdapter<Meal, MealsAdapter.ViewHolder>(MealDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

//    override fun getItemCount(): Int {
//        return mealList.size
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal, viewModel)
    }

    class ViewHolder(private val mealBinding: MealCardBinding) :
        RecyclerView.ViewHolder(mealBinding.root) {

        fun bind(meal: Meal, viewModel: MealsViewModel) {
            mealBinding.meal = meal
            mealBinding.viewModel = viewModel
            mealBinding.executePendingBindings()
        }
    }

    class MealDiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(old: Meal, new: Meal) = old.id == new.id
        override fun areContentsTheSame(old: Meal, new: Meal) = old.equals(new)
    }
}