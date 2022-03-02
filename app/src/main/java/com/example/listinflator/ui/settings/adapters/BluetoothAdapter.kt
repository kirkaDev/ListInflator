package com.example.listinflator.ui.settings.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.listinflator.R
import com.example.listinflator.data.model.BluetoothDevice

class BluetoothAdapter(private var devicesList: List<BluetoothDevice>
): RecyclerView.Adapter<BluetoothAdapter.BluetoothViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BluetoothViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_bluetooth_device, parent, false)
        return BluetoothViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BluetoothViewHolder, position: Int) {
        holder.deviceNameTextView?.text = devicesList[position].deviceName
        holder.deviceStateTextView?.text = devicesList[position].connectionState.toString()
    }

    override fun getItemCount(): Int {
        return devicesList.size
    }

    inner class BluetoothViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var deviceNameTextView: AppCompatTextView? = null
        var deviceStateTextView: AppCompatTextView? = null

        init {
            deviceNameTextView = itemView.findViewById<AppCompatTextView?>(R.id.deviceNameTextView).apply { isVisible = true }
            deviceStateTextView = itemView.findViewById<AppCompatTextView?>(R.id.deviceStateTextView).apply { isVisible = true }
        }
    }
}