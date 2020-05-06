//package com.jeefersan.weatherapp.presentation.search
//
//
//import android.os.Bundle
//import android.view.View
//import com.algolia.instantsearch.core.connection.ConnectionHandler
//import com.algolia.instantsearch.core.hits.connectHitsView
//import com.algolia.instantsearch.helper.android.list.autoScrollToStart
//import com.algolia.instantsearch.helper.android.searchbox.SearchBoxViewAppCompat
//import com.algolia.instantsearch.helper.searchbox.SearchBoxConnector
//import com.algolia.instantsearch.helper.searchbox.connectView
//import com.algolia.instantsearch.helper.searcher.SearcherPlaces
//import com.algolia.search.model.places.PlaceType
//import com.algolia.search.model.places.PlacesQuery
//import com.algolia.search.model.search.Language
//import com.jeefersan.weatherapp.R
//import com.jeefersan.weatherapp.databinding.FragmentSearchBinding
//import com.jeefersan.weatherapp.presentation.base.BaseFragment
//import com.jeefersan.weatherapp.presentation.base.BaseViewModel
//import org.koin.android.ext.android.inject
//
///**
// * Created by JeeferSan on 5-5-20.
// */
//class SearchFragment : BaseFragment<FragmentSearchBinding>() {
//    private val viewModel: SearchViewModelImpl by inject()
//
//    override val layoutId: Int = R.layout.fragment_search
//
//    override fun getViewModel(): BaseViewModel = viewModel
//
//    override fun setupBinding() {
//        getBinding().apply {
//            vm = viewModel
//            lifecycleOwner = this@SearchFragment
//        }
//    }
//
//    val query = PlacesQuery(
//        type = PlaceType.City,
//        hitsPerPage = 15,
//        aroundLatLngViaIP = false
//    )
//    val searcher = SearcherPlaces(query = query, language = Language.English)
//    val searchBox = SearchBoxConnector(searcher)
//    val adapter = PlacesAdapter()
//    val connection = ConnectionHandler(searchBox)
//
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        connection += searchBox.connectView(SearchBoxViewAppCompat(getBinding().searchView))
//        connection += searcher.connectHitsView(adapter) { hits -> hits.hits }
//        getBinding().rvPlacesList.let {
//            it.itemAnimator = null
//            it.adapter = adapter
//            it.autoScrollToStart(adapter)
//        }
//        searcher.searchAsync()
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        connection.disconnect()
//        searcher.cancel()
//    }
//}
