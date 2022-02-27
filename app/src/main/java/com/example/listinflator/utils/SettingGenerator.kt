package com.example.listinflator.utils

import com.example.listinflator.R
import com.example.listinflator.data.model.Setting

object SettingGenerator
    {
        fun generateSettings(): List<Setting> {
            return listOf<Setting>(
                Setting(
                    R.drawable.ic_wifi_24,
                    "Personal Hotspot",
                    "Not discoverable"
                ),
                Setting(
                    R.drawable.ic_wifi_24,
                    "Personal Hotspot",
                    "Not discoverable"
                ),
                Setting(
                    R.drawable.ic_wifi_24,
                    "Personal Hotspot",
                    "Not discoverable"
                ),
                Setting(
                    R.drawable.ic_wifi_24,
                    "Personal Hotspot",
                    "Not discoverable"
                ),
                Setting(
                    R.drawable.ic_wifi_24,
                    "Personal Hotspot",
                    "Not discoverable"
                ),
            )
        }
}