package com.jeefersan.weatherapp.presentation.favorites

import com.jeefersan.weatherapp.databinding.FragmentFavoritesBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.favorites.viewmodels.FavoritesViewModelImpl
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by JeeferSan on 25-4-20.
 */
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    private val viewModel: FavoritesViewModelImpl by viewModel()
    override val layoutId: Int = com.jeefersan.weatherapp.R.layout.fragment_favorites

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@FavoritesFragment
        }
    }


}