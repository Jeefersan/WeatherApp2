package com.jeefersan.weatherapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeefersan.domain.Location
import com.jeefersan.weatherapp.databinding.ListItemSearchLocationBinding
import com.jeefersan.weatherapp.presentation.base.BaseItemListener

/**
 * Created by JeeferSan on 6-5-20.
 */

class LocationsAdapter(
    private val listener: LocationItemListener
) : ListAdapter<Location, LocationsAdapter.LocationViewHolder>(LocationItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSearchLocationBinding.inflate(inflater, parent, false)
        return LocationViewHolder(binding)
    }



    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(private val binding: ListItemSearchLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(locationItem: Location) {
            binding.apply {
                location = locationItem
                root.setOnClickListener { listener.onItemClick(locationItem) }
                executePendingBindings()
            }

        }
    }



    class LocationItemDiffCallback : DiffUtil.ItemCallback<Location>() {

        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    interface LocationItemListener : BaseItemListener<Location>


}