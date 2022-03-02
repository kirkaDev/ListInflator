package com.example.listinflator.ui.settings

import android.os.Bundle
import android.transition.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listinflator.Application
import com.example.listinflator.R
import com.example.listinflator.data.model.BluetoothDevice
import com.example.listinflator.data.model.Setting
import com.example.listinflator.databinding.ViewControllerSettingsBinding
import com.example.listinflator.mvp.presenter.SettingsPresenter
import com.example.listinflator.mvp.view.ISettingsView
import com.example.listinflator.ui.main.MvpController
import com.example.listinflator.ui.settings.adapters.BluetoothAdapter
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

    private val animationDuration = 300L

    lateinit var sceneListIsNotExpanded: Scene
    lateinit var sceneListIsExpanded: Scene
    lateinit var sceneBluetoothDevices: Scene

    var sceneBackStack: ArrayList<Scene> = ArrayList()

    var settingsAdapter: SettingAdapter? = null
    var bluetoothDevicesAdapter: BluetoothAdapter? = null
    var transitionSet: TransitionSet? = null

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

        if (transitionSet == null) {
            transitionSet = TransitionSet().apply {
                ordering = TransitionSet.ORDERING_TOGETHER
                addTransition(Fade())
                addTransition(ChangeBounds())
                duration = animationDuration
                interpolator = AccelerateInterpolator()
            }
        }

        return binding.root
    }

    override fun showShortSettingsList(settingsList: List<Setting>) {
        sceneListIsNotExpanded = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene1,
            activity?.applicationContext!!
        )

        sceneListIsNotExpanded.setEnterAction {
            sceneListIsNotExpanded.sceneRoot.findViewById<RecyclerView>(R.id.settingsList).apply {
                settingsAdapter = SettingAdapter(
                    settingsList,
                    object : SettingAdapter.OnClickSettingListener {
                        override fun onClick(setting: Setting) {
                            sceneBackStack.add(sceneListIsNotExpanded)
                            presenter.showFullSettingsList()
                        }
                    },
                    isExpanded = false
                )

                adapter = settingsAdapter

                layoutManager = GridLayoutManager(
                    activity?.applicationContext,
                    spanCount,
                    RecyclerView.VERTICAL,
                    false
                )
            }
        }

        sceneListIsNotExpanded.sceneRoot.setOnClickListener {
            handleBack()
        }
        TransitionManager.go(sceneListIsNotExpanded, transitionSet)
    }

    override fun showFullSettingsList(settingsList: List<Setting>) {
        sceneListIsExpanded = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene2,
            activity?.applicationContext!!
        )

        sceneListIsExpanded.setEnterAction {
            sceneListIsExpanded.sceneRoot.findViewById<RecyclerView>(R.id.settingsList).apply {
                settingsAdapter?.let { it ->
                    it.updateList(settingsList)
                    it.setListIsExpanded(true)
                    it.setOnClickSettingListener(
                        object : SettingAdapter.OnClickSettingListener {
                            override fun onClick(setting: Setting) {
                                when (setting.settingType) {
                                    Setting.SettingTypes.BLUETOOTH -> {
                                        sceneBackStack.add(sceneListIsExpanded)
                                        presenter.showBluetoothScreen()
                                    }
                                }
                            }
                        }
                    )
                }

                adapter = settingsAdapter
                layoutManager = GridLayoutManager(
                    activity?.applicationContext,
                    spanCount,
                    RecyclerView.VERTICAL,
                    false
                )
            }
        }

        sceneListIsExpanded.sceneRoot.setOnClickListener {
            handleBack()
        }

        TransitionManager.go(sceneListIsExpanded, transitionSet)
    }

    override fun showBluetoothScreen(devicesList: List<BluetoothDevice>) {
        if (bluetoothDevicesAdapter == null) {
            bluetoothDevicesAdapter = BluetoothAdapter(devicesList)
        }

        sceneBluetoothDevices = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene_bluetooth,
            activity?.applicationContext!!
        )

        sceneBluetoothDevices.setEnterAction {
            sceneBluetoothDevices.sceneRoot.findViewById<RecyclerView>(R.id.devicesList).apply {
                adapter = bluetoothDevicesAdapter
                layoutManager = LinearLayoutManager(
                    activity?.applicationContext,
                    RecyclerView.VERTICAL,
                    false
                )
            }
        }
        TransitionManager.go(sceneBluetoothDevices, transitionSet)
    }

    override fun handleBack(): Boolean {
        try {
            if (sceneBackStack.isNotEmpty()) {
                when (sceneBackStack.last()) {
                    sceneListIsNotExpanded -> {
                        presenter.showShortList()
                    }
                    sceneListIsExpanded -> {
                        presenter.showFullSettingsList()
                    }
                    sceneBluetoothDevices -> {
                        presenter.showBluetoothScreen()
                    }
                }
                sceneBackStack.removeLastOrNull()
                return true
            } else {
                return super.handleBack()
            }
        } catch (e: Exception) {
            return super.handleBack()
        }
    }
}