package com.example.listinflator

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.listinflator.di.components.ApplicationComponent
import com.example.listinflator.di.components.DaggerApplicationComponent

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        getComponent(this)
    }

    companion object {
        private var mComponent: ApplicationComponent? = null

        val component: ApplicationComponent
            get() = mComponent!!

        fun getComponent(application: Application): ApplicationComponent {
            if (mComponent == null) {
                mComponent = DaggerApplicationComponent
                    .builder()
                    .build()
            }

            return mComponent!!
        }
    }
}