package com.example.listinflator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.listinflator.ui.settings.SettingsController

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router =
            Conductor.attachRouter(this, findViewById(R.id.activityContainer), savedInstanceState)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(SettingsController()))
        }
    }
}