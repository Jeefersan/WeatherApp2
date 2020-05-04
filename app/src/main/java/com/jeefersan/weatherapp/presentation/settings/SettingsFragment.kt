package com.jeefersan.weatherapp.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeefersan.weatherapp.databinding.FragmentSettingsBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by JeeferSan on 25-4-20.
 */
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    private val viewModel: SettingsViewModelImpl by viewModel()

    override val layoutId: Int = com.jeefersan.weatherapp.R.layout.fragment_settings

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@SettingsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}