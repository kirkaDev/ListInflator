package com.example.listinflator.data.model

import androidx.annotation.DrawableRes

data class Setting(
    @DrawableRes val imgResource: Int,
    val settingName: String,
    val state: String
)