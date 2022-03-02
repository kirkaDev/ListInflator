package com.example.listinflator.data.model

import androidx.annotation.DrawableRes

data class Setting(
    @DrawableRes val imgResource: Int,
    val name: String,
    val state: String,
    val settingType: SettingTypes
){
    enum class SettingTypes{
        AIRPLANE_MODE, MOBILE_DATA, WI_FI, BLUETOOTH, AIR_DROP, HOTSPOT
    }
}