package com.jeefersan.weatherapp.presentation.search

import android.text.SpannedString
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.algolia.instantsearch.core.highlighting.HighlightTokenizer
import com.algolia.instantsearch.core.hits.HitsView
import com.algolia.instantsearch.helper.android.highlighting.toSpannedString
import com.algolia.instantsearch.helper.android.inflate
import com.algolia.search.model.places.PlaceLanguage
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.serialize.toHighlights
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.ListItemPlaceBinding

/**
 * Created by JeeferSan on 5-5-20.
 */
class PlacesAdapter : ListAdapter<PlaceLanguage, PlacesAdapter.PlacesViewHolder>(PlacesAdapter),
    HitsView<PlaceLanguage> {


    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlaceBinding.inflate(inflater, parent, false)
        return PlacesViewHolder(binding)
    }

    override fun setHits(hits: List<PlaceLanguage>) = submitList(hits)

    inner class PlacesViewHolder(private val binding: ListItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: PlaceLanguage) {
            println(place.highlightResultOrNull)
            val name = place.highlightResultOrNull
                ?.toHighlights("locale_names")
                ?.firstOrNull()
                ?.tokenize() ?: place.localNames.first()
            val country = place.country

            binding.placeName.text = TextUtils.concat(name, ", ", country)
        }

        fun HighlightResult.tokenize(): SpannedString {
            return HighlightTokenizer()(value).toSpannedString()
        }
    }

    companion object : DiffUtil.ItemCallback<PlaceLanguage>() {

        override fun areItemsTheSame(oldItem: PlaceLanguage, newItem: PlaceLanguage): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(oldItem: PlaceLanguage, newItem: PlaceLanguage): Boolean {
            return oldItem == newItem
        }
    }


}

//class PlacesViewHolder(private val binding: ListItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(place: PlaceLanguage) {
//        println(place.highlightResultOrNull)
//        val name = place.highlightResultOrNull
//            ?.toHighlights("locale_names")
//            ?.firstOrNull()
//            ?.tokenize() ?: place.localNames.first()
//
//
//        binding.placeName.text = name
//    }
//
//    fun HighlightResult.tokenize(): SpannedString {
//        return HighlightTokenizer()(value).toSpannedString()
//    }
//}
//
//class PlacesAdapter : ListAdapter<PlaceLanguage, PlacesViewHolder>(PlacesAdapter), HitsView<PlaceLanguage> {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
//                val inflater = LayoutInflater.from(parent.context)
//        val binding = ListItemPlaceBinding.inflate(inflater, parent, false)
//        return PlacesViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
//        val item = getItem(position)
//
//        if (item != null) holder.bind(item)
//    }
//
//    override fun setHits(hits: List<PlaceLanguage>) {
//        submitList(hits)
//    }
//
//    companion object : DiffUtil.ItemCallback<PlaceLanguage>() {
//
//        override fun areItemsTheSame(oldItem: PlaceLanguage, newItem: PlaceLanguage): Boolean {
//            return oldItem::class == newItem::class
//        }
//
//        override fun areContentsTheSame(oldItem: PlaceLanguage, newItem: PlaceLanguage): Boolean {
//            return oldItem == newItem
//        }
//    }
//}