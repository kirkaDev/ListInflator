package com.example.listinflator.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.listinflator.R
import com.example.listinflator.mvp.view.ISettingsView
import com.example.listinflator.ui.main.MvpController

class SettingsController: MvpController(), ISettingsView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.view_controller_settings, container, false
        ).apply {

        }
    }

    override fun showSettings() {
        TODO("Not yet implemented")
    }
}