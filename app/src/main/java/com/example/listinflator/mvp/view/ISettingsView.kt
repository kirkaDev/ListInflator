package com.example.listinflator.mvp.view

import com.example.listinflator.data.model.BluetoothDevice
import com.example.listinflator.data.model.Setting
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface ISettingsView: MvpView {
    fun showShortSettingsList(settingsList: List<Setting>)
    fun showFullSettingsList(settingsList: List<Setting>)
    fun showBluetoothScreen(devicesList: List<BluetoothDevice>)
}