package com.example.listinflator.utils

import com.example.listinflator.R
import com.example.listinflator.data.model.BluetoothDevice
import com.example.listinflator.data.model.Setting

object SettingGenerator
    {
        fun generateSettings(): List<Setting> {
            return listOf<Setting>(
                Setting(
                    R.drawable.ic_airplanemode_24,
                    "Airplane Mode",
                    "Off",
                    Setting.SettingTypes.AIRPLANE_MODE
                ),
                Setting(
                    R.drawable.ic_4g_plus_mobiledata_24,
                    "Mobile Data",
                    "On",
                    Setting.SettingTypes.MOBILE_DATA
                ),
                Setting(
                    R.drawable.ic_wifi_24,
                    "Wi-Fi",
                    "HUAWEI-9zBx-5G",
                    Setting.SettingTypes.WI_FI
                ),
                Setting(
                    R.drawable.ic_bluetooth_24,
                    "Bluetooth",
                    "2 Devices",
                    Setting.SettingTypes.BLUETOOTH
                ),
                Setting(
                    R.drawable.ic_airdrop_24,
                    "AirDrop",
                    "Receiving Off",
                    Setting.SettingTypes.AIR_DROP
                ),
                Setting(
                    R.drawable.ic_hotspot_24,
                    "Personal Hotspot",
                    "Not discoverable",
                    Setting.SettingTypes.HOTSPOT
                ),
            )
        }

        fun generateBluetoothDevices(): List<BluetoothDevice> {
            return listOf<BluetoothDevice>(
                BluetoothDevice(
                    "AirPods Pro (Кирилл)",
                    BluetoothDevice.ConnectionStates.Connected
                ),
                BluetoothDevice(
                    "Mazda CarPlay",
                    BluetoothDevice.ConnectionStates.Connected
                ),
              BluetoothDevice(
                    "ELM327",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
                BluetoothDevice(
                    "Apple Watch",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
                  BluetoothDevice(
                    "Xiaomi Mi Band 4",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),

                BluetoothDevice(
                    "AirPods Pro (Александр)",
                    BluetoothDevice.ConnectionStates.Connected
                ),
                BluetoothDevice(
                    "Mazda CarPlay",
                    BluetoothDevice.ConnectionStates.Connected
                ),
                BluetoothDevice(
                    "ELM327",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
                BluetoothDevice(
                    "Apple Watch",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
                BluetoothDevice(
                    "Xiaomi Mi Band 2",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),

                BluetoothDevice(
                    "AirPods Pro (Сергей)",
                    BluetoothDevice.ConnectionStates.Connected
                ),
                BluetoothDevice(
                    "Mazda CarPlay",
                    BluetoothDevice.ConnectionStates.Connected
                ),
                BluetoothDevice(
                    "ELM327",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
                BluetoothDevice(
                    "Apple Watch",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
                BluetoothDevice(
                    "Xiaomi Mi Band 3",
                    BluetoothDevice.ConnectionStates.Disconnected
                ),
            )
        }
}