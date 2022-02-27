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
    var settings: List<Setting> = emptyList()


    fun initUI(){
        presenterScope.launch{
            settings = SettingGenerator.generateSettings()
            viewState.showSettings(settings)
        }
    }
}