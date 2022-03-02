package com.example.listinflator.ui.settings.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.listinflator.R
import com.example.listinflator.data.model.Setting

class SettingAdapter(private var settingsList: List<Setting>,
                     private val onClickSettingListener: OnClickSettingListener,
                     private var isExpanded: Boolean
): RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {

    fun updateList(settingsList: List<Setting>){
        this.settingsList = settingsList
    }

    fun setListIsExpanded(isExpanded: Boolean){
        this.isExpanded = isExpanded
    }

    fun getListIsExpanded(): Boolean{
        return isExpanded
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_setting, parent, false)
        return SettingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.settingImageButton?.apply {
            setImageResource(settingsList[position].imgResource)
            setOnClickListener {
                onClickSettingListener.onClick(
                    settingsList[position]
                )
            }
        }

        holder.settingNameTextView?.text = settingsList[position].name
        holder.settingStateTextView?.text = settingsList[position].state
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    interface OnClickSettingListener {
        fun onClick(setting: Setting)
    }

    inner class SettingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var settingImageButton: ImageButton? = null
        var settingNameTextView: AppCompatTextView? = null
        var settingStateTextView: AppCompatTextView? = null

        init {
            settingImageButton = itemView.findViewById(R.id.settingImage)

            if (isExpanded){
                settingNameTextView = itemView.findViewById<AppCompatTextView?>(R.id.settingNameTextView).apply { isVisible = true }
                settingStateTextView = itemView.findViewById<AppCompatTextView?>(R.id.settingStateTextView).apply { isVisible = true }
            }
        }
    }
}