package com.example.listinflator.data.model

import androidx.annotation.DrawableRes

data class Setting(
    @DrawableRes val imgResource: Int,
    val name: String,
    val state: String
)