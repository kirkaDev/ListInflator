package com.example.listinflator.utils

import com.example.listinflator.R
import com.example.listinflator.data.model.Setting

object SettingGenerator
    {
        fun generateSettings(): List<Setting> {
            return listOf<Setting>(
                Setting(
                    R.drawable.ic_airplanemode_24,
                    "Airplane Mode",
                    "Off"
                ),
                Setting(
                    R.drawable.ic_4g_plus_mobiledata_24,
                    "Mobile Data",
                    "On"
                ),
                Setting(
                    R.drawable.ic_wifi_24,
                    "Wi-Fi",
                    "HUAWEI-9zBx-5G"
                ),
                Setting(
                    R.drawable.ic_bluetooth_24,
                    "Bluetooth",
                    "2 Devices"
                ),
                Setting(
                    R.drawable.ic_airdrop_24,
                    "AirDrop",
                    "Receiving Off"
                ),
                Setting(
                    R.drawable.ic_hotspot_24,
                    "Personal Hotspot",
                    "Not discoverable"
                ),
            )
        }
}