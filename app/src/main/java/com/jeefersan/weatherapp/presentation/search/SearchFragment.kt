package com.jeefersan.weatherapp.presentation.search

import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentSearchBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import org.koin.android.ext.android.inject

/**
 * Created by JeeferSan on 5-5-20.
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModelImpl by inject()

    override val layoutId: Int = R.layout.fragment_search

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@SearchFragment
        }
    }



}
