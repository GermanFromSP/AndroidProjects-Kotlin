package com.example.shoplist.presentation.adapters

import androidx.databinding.BindingAdapter
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, error:Boolean){
    val message = if (error) {
        textInputLayout.context.getString(R.string.error_name)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, error:Boolean){
    val message = if (error) {
        textInputLayout.context.getString(R.string.error_count)
    } else {
        null
    }
    textInputLayout.error = message
}