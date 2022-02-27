package com.example.listinflator.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listinflator.Application
import com.example.listinflator.data.model.Setting
import com.example.listinflator.databinding.ViewControllerSettingsBinding
import com.example.listinflator.mvp.presenter.SettingsPresenter
import com.example.listinflator.mvp.view.ISettingsView
import com.example.listinflator.ui.main.MvpController
import com.example.listinflator.ui.settings.adapters.SettingAdapter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class SettingsController : MvpController(), ISettingsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    // Span count for settings list
    private val spanCount = 2

    override fun inject() {
        super.inject()
        Application.component.inject(this)
    }

    private var _binding: ViewControllerSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        _binding = ViewControllerSettingsBinding.inflate(inflater, container, false)
        presenter.initUI()

        return binding.root
    }

    override fun showSettings(settingsList: List<Setting>) {
        binding.settingsList.apply {
            adapter = SettingAdapter(
                settingsList,
                object : SettingAdapter.OnClickSettingListener {
                    override fun onClick(setting: Setting) {
                    }
                }
            )

        layoutManager = GridLayoutManager(activity?.applicationContext,
            spanCount,
            RecyclerView.VERTICAL,
            false)
        }
    }
}