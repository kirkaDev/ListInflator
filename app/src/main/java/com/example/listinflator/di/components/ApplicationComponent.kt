package com.example.listinflator.di.components

import com.example.listinflator.ui.settings.SettingsController
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface ApplicationComponent {
    fun inject(settingsController: SettingsController)
}