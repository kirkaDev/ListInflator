package com.example.listinflator.data.model

data class BluetoothDevice(
    val deviceName: String,
    val connectionState: ConnectionStates
) {
    enum class ConnectionStates{
        CONNECTED, DISCONNECTED
    }
}