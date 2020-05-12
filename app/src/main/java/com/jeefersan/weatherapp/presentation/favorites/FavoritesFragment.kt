package com.jeefersan.weatherapp.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jeefersan.weatherapp.databinding.FragmentFavoritesBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.favorites.adapters.FavoriteCurrentWeatherAdapter
import com.jeefersan.weatherapp.presentation.favorites.viewmodels.FavoritesViewModelImpl
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by JeeferSan on 25-4-20.
 */
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(),
    FavoriteCurrentWeatherAdapter.FavoriteListener {
    private val viewModel: FavoritesViewModelImpl by viewModel()

    private val args: FavoritesFragmentArgs by navArgs()

    override val layoutId: Int = com.jeefersan.weatherapp.R.layout.fragment_favorites

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@FavoritesFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        args.location?.let { viewModel.onNewLocationSelected(it) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.favoriteCurrentWeatherModels.observe(viewLifecycleOwner, Observer { favorites ->
            getBinding().rvFavorites.apply {
                setHasFixedSize(true)
                adapter = FavoriteCurrentWeatherAdapter(this@FavoritesFragment, favorites)
            }
        })
    }


    override fun onShowDetailsClick(id: Int) = viewModel.onShowDetailsClick(id)

    override fun onRemoveClick(id: Int) = viewModel.onRemoveClick(id)
}