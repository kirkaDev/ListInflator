package com.example.listinflator.mvp.view

import moxy.viewstate.strategy.alias.OneExecution

@OneExecution
interface ISettingsView {
    fun showSettings()
}