package com.example.listinflator.ui.settings

import android.os.Bundle
import android.transition.*
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
    private val animationDuration = 300L

    lateinit var sceneListIsNotExpanded: Scene
    lateinit var sceneListIsExpanded: Scene

    var settingsAdapter: SettingAdapter? = null
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

        if (transitionSet==null){
            transitionSet = TransitionSet().apply {
                ordering = TransitionSet.ORDERING_TOGETHER
                addTransition(Fade())
                addTransition(ChangeBounds())
                duration = animationDuration
                interpolator = AccelerateInterpolator()
            }
        }

        sceneListIsNotExpanded = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene1,
            activity?.applicationContext!!
        )

        sceneListIsNotExpanded.setEnterAction {
            sceneListIsNotExpanded.sceneRoot.findViewById<RecyclerView>(R.id.settingsList).apply {
                settingsAdapter?.updateList(presenter.settings.take(shortListSize))
                settingsAdapter?.setListIsExpanded(false)
                adapter = settingsAdapter

                layoutManager = GridLayoutManager(
                    activity?.applicationContext,
                    spanCount,
                    RecyclerView.VERTICAL,
                    false
                )
            }
        }

        sceneListIsExpanded = Scene.getSceneForLayout(
            binding.sceneRoot,
            R.layout.scene2,
            activity?.applicationContext!!
        )

        sceneListIsExpanded.setEnterAction {
            sceneListIsExpanded.sceneRoot.findViewById<RecyclerView>(R.id.settingsList).apply {
                settingsAdapter?.updateList(presenter.settings)
                settingsAdapter?.setListIsExpanded(true)
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
            TransitionManager.go(sceneListIsNotExpanded, transitionSet)
        }

        return binding.root
    }

    override fun showSettings(settingsList: List<Setting>) {
        binding.sceneRoot.scene1.settingsList.apply {
            settingsAdapter = SettingAdapter(
                settingsList.take(shortListSize),
                object : SettingAdapter.OnClickSettingListener {
                    override fun onClick(setting: Setting) {
                        (adapter as SettingAdapter).apply {
                            setListIsExpanded(true)
                        }
                        TransitionManager.go(sceneListIsExpanded, transitionSet)
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

    override fun handleBack(): Boolean {
        settingsAdapter?.let{
            return if (it.getListIsExpanded()){
                TransitionManager.go(sceneListIsNotExpanded, transitionSet)
                true
            } else{
                super.handleBack()
            }
        } ?: kotlin.run {
            return super.handleBack()
        }
    }
}