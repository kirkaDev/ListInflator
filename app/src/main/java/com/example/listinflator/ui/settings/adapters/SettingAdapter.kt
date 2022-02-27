package com.example.listinflator.ui.settings.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listinflator.R
import com.example.listinflator.data.model.Setting

class SettingAdapter(private val settingsList: List<Setting>,
                     private val onClickSettingListener: OnClickSettingListener,
): RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {

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

    class SettingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var settingImageButton: ImageButton? = null
        var settingNameTextView: AppCompatTextView? = null
        var settingStateTextView: AppCompatTextView? = null

        init {
            settingImageButton = itemView.findViewById(R.id.settingImage)
            settingNameTextView = itemView.findViewById(R.id.settingNameTextView)
            settingStateTextView = itemView.findViewById(R.id.settingStateTextView)
        }
    }
}