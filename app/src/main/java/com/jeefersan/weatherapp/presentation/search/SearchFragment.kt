package com.jeefersan.weatherapp.presentation.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.algolia.instantsearch.helper.android.list.autoScrollToStart
import com.jeefersan.domain.Location
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentSearchBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.search.viewmodels.SearchViewModelImpl
import org.koin.android.ext.android.inject

/**
 * Created by JeeferSan on 5-5-20.
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>(), LocationsAdapter.LocationItemListener {
    private val viewModel: SearchViewModelImpl by inject()
    private lateinit var locationsAdapter: LocationsAdapter

    override val layoutId: Int = R.layout.fragment_search

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@SearchFragment
        }
    }

    override fun onItemClick(item: Location) {
        viewModel.onLocationSelect(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationsAdapter = LocationsAdapter(this)
        getBinding().rvSuggestions.let {
            it.adapter = locationsAdapter
            it.itemAnimator = null
            it.autoScrollToStart(locationsAdapter)
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.locationSuggestions.observe(viewLifecycleOwner, Observer { locations ->
            locationsAdapter.submitList(locations)
        })
    }


}
