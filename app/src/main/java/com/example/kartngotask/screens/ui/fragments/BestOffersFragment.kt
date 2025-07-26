package com.example.kartngotask.screens.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kartngotask.databinding.FragmentBestOffersBinding
import com.example.kartngotask.screens.adapter.MealsAdapter
import com.example.kartngotask.screens.viewmodel.MealsViewModel

class BestOffersFragment: Fragment() {

    private var _viewBinding: FragmentBestOffersBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var viewModel: MealsViewModel
    private lateinit var adapter: MealsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel= ViewModelProvider(this)[MealsViewModel::class.java]

        _viewBinding = FragmentBestOffersBinding.inflate(inflater, container,false)
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MealsAdapter(viewModel)
        viewBinding.mealsRecyclerView.adapter = adapter

        viewModel.allMeals.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null // Prevent memory leaks
    }
}