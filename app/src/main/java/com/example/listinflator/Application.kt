package com.example.listinflator

import android.app.Application
import com.example.listinflator.di.components.ApplicationComponent
import com.example.listinflator.di.components.DaggerApplicationComponent

class Application : Application() {

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.create()
    }
}