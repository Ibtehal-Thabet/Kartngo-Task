package com.example.kartngotask.screens

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.kartngotask.R

@BindingAdapter(value = ["app:imageFromName"])
fun bindImageFromDrawable(imageView: ImageView, image: String?) {
    image?.let{
        val context = imageView.context
        val resId = context.resources.getIdentifier(it, "drawable", context.packageName)
        if (resId != 0) {
            imageView.setImageResource(resId)
        } else {
            imageView.setImageResource(R.drawable.placeholder)
        }
    }
}
