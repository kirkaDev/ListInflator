package com.example.listinflator.ui.settings

import android.os.Bundle
import android.transition.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listinflator.Application
import com.example.listinflator.R
import com.example.listinflator.data.model.Setting
import com.example.listinflator.databinding.ViewControllerSettingsBinding
import com.example.listinflator.mvp.presenter.SettingsPresenter
import com.example.listinflator.mvp.view.ISettingsView
import com.example.listinflator.ui.main.MvpController
import com.example.listinflator.ui.settings.adapters.SettingAdapter
import kotlinx.android.synthetic.main.scene1.view.*
import kotlinx.android.synthetic.main.view_controller_settings.view.*
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
    private val shortListSize = 4

    lateinit var scene1: Scene
    lateinit var scene2: Scene

    var initialSettingsRV: RecyclerView? = null
    var initialSettingsAdapter: SettingAdapter? = null

    var expandedList: RecyclerView? = null
    var expandedListAdapter: SettingAdapter? = null

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

        scene1 = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene1,
            activity?.applicationContext!!
        )
        scene2 = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene2,
            activity?.applicationContext!!
        )

        scene2.setEnterAction {
            Log.d("Animation", "Animation, setEnterAction called")
            expandedList = scene2.sceneRoot.findViewById<RecyclerView>(R.id.settingsList).apply {
                expandedListAdapter = SettingAdapter(
                    presenter.settings,
                    object : SettingAdapter.OnClickSettingListener {
                        override fun onClick(setting: Setting) {
                        }
                    },
                    isExpanded = true
                )

                adapter = expandedListAdapter

                layoutManager = GridLayoutManager(
                    activity?.applicationContext,
                    spanCount,
                    RecyclerView.VERTICAL,
                    false
                )

            }
        }

        scene2.sceneRoot.setOnClickListener {
            scene2.exit()
        }

        return binding.root
    }

    override fun showSettings(settingsList: List<Setting>) {
        initialSettingsRV = binding.sceneRoot.scene1.settingsList.apply {
            initialSettingsAdapter = SettingAdapter(
                settingsList.take(shortListSize),
                object : SettingAdapter.OnClickSettingListener {
                    override fun onClick(setting: Setting) {
                        (adapter as SettingAdapter).apply {
                            setListIsExpanded(true)
                        }
                        val set = TransitionSet()
                        set.addTransition(Fade())
                        set.addTransition(ChangeBounds())
                        set.ordering = TransitionSet.ORDERING_TOGETHER
                        set.duration = 300
                        set.interpolator = AccelerateInterpolator()
                        Log.d("Animation", "Animation, transitioning to scene2")
                        TransitionManager.go(scene2, set)
                    }
                },
                isExpanded = false
            )
            adapter = initialSettingsAdapter

            layoutManager = GridLayoutManager(
                activity?.applicationContext,
                spanCount,
                RecyclerView.VERTICAL,
                false
            )
        }
    }
}