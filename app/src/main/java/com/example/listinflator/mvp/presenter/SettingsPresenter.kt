package com.example.listinflator.mvp.presenter

import com.example.listinflator.data.model.Setting
import com.example.listinflator.mvp.view.ISettingsView
import com.example.listinflator.utils.SettingGenerator
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class SettingsPresenter
    @Inject constructor()
    : MvpPresenter<ISettingsView>() {
    private val shortListSize = 4

    var settings: List<Setting> = emptyList()

    fun initUI(){
        settings = SettingGenerator.generateSettings()
        showShortList()
    }

    fun showShortList(){
        presenterScope.launch {
            viewState.showShortSettingsList(settings.take(shortListSize))
        }
    }

    fun showFullSettingsList(){
        presenterScope.launch {
            viewState.showFullSettingsList(settings)
        }
    }

    fun showBluetoothScreen(){
        presenterScope.launch {
            viewState.showBluetoothScreen(
                SettingGenerator.generateBluetoothDevices()
            )
        }
    }
}