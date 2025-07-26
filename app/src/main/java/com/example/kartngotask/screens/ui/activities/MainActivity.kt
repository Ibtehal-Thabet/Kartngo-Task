package com.example.kartngotask.screens.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kartngotask.R
import com.example.kartngotask.databinding.ActivityMainBinding
import com.example.kartngotask.screens.ui.fragments.BestOffersFragment
import com.example.kartngotask.screens.viewmodel.MealsViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[MealsViewModel::class.java]

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, BestOffersFragment())
            .addToBackStack(null)
            .commit()

        val categoriesList = listOf("أفضل العروض", "مستورد", "أجبان قابلة للدهن", "أجبان", "وجبات", "سندويتشات")
        bindTabs(categoriesList)
        updateSnackbar()

        viewBinding.toolbar.setNavigationOnClickListener {
            finishAffinity()
        }
    }

    private fun bindTabs(categories: List<String>) {
        categories.forEachIndexed { index, category ->
            val tab = viewBinding.tabLayout.newTab()
            val tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
            val tabText = tabView.findViewById<TextView>(R.id.tab_title)
            val checkIcon = tabView.findViewById<ImageView>(R.id.tab_icon)
            if (index == 0) {
                checkIcon.visibility = View.VISIBLE // show check for first tab
                tabView.setBackgroundResource(R.drawable.selected_tab_shape)
            } else {
                checkIcon.visibility = View.GONE
                tabView.setBackgroundResource(R.drawable.unselected_tab_shape)
            }
            tabText.text = category

            tab.text = category
            tab.tag = category
            tab.customView = tabView
            viewBinding.tabLayout.addTab(tab)
            val layoutParams = LinearLayout.LayoutParams(tab.view.layoutParams)
            layoutParams.marginStart = 12
            layoutParams.marginEnd = 12
            layoutParams.topMargin = 18
            layoutParams.bottomMargin = 12
            tab.view.layoutParams = layoutParams
        }
        viewBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val view = tab?.customView
                    view?.setBackgroundResource(R.drawable.selected_tab_shape)
                    view?.findViewById<ImageView>(R.id.tab_icon)?.visibility = View.VISIBLE

                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, BestOffersFragment())
                        .addToBackStack(null)
                        .commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val view = tab?.customView
                    view?.setBackgroundResource(R.drawable.unselected_tab_shape)
                    view?.findViewById<ImageView>(R.id.tab_icon)?.visibility = View.GONE
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
//
                }
            }
        )
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

    fun updateSnackbar(){
        viewModel.totalPrice.observe(this) { total ->
            val layoutParams = viewBinding.fragmentContainer.layoutParams as ViewGroup.MarginLayoutParams

            if (total > 0) {
                viewBinding.snackbarLayout.visibility = View.VISIBLE
                viewBinding.totalCart.text = "$total SAR"

                layoutParams.setMargins(0, 8, 0, 160)

            }else{
                viewBinding.snackbarLayout.visibility = View.GONE
                layoutParams.setMargins(0, 8, 0, 8)
            }
            viewBinding.fragmentContainer.layoutParams = layoutParams
        }
    }
}