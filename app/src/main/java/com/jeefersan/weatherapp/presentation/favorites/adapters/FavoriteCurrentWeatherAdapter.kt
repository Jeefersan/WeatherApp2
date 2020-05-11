package com.jeefersan.weatherapp.presentation.favorites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.ListItemFavoriteCurrentWeatherBinding
import com.jeefersan.weatherapp.misc.collapse
import com.jeefersan.weatherapp.misc.expand
import com.jeefersan.weatherapp.misc.toggleArrow
import com.jeefersan.weatherapp.models.FavoriteCurrentWeatherModel
import com.jeefersan.weatherapp.presentation.base.BaseClickListener


/**
 * Created by JeeferSan on 6-5-20.
 */
class FavoriteCurrentWeatherAdapter(
    private val listener: FavoriteListener,
    private val list: List<FavoriteCurrentWeatherModel>
) :
    RecyclerView.Adapter<FavoriteCurrentWeatherAdapter.FavoriteWeatherViewHolder>() {

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: FavoriteWeatherViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class FavoriteWeatherViewHolder(private val binding: ListItemFavoriteCurrentWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FavoriteCurrentWeatherModel) {
            binding.apply {
                favorite = model
                btnExpand.setOnClickListener {
                    val show: Boolean = toggleLayout(
                        isExpanded = !model.isExpanded, v = it, layoutExpand = layoutExpander
                    )
                    model.isExpanded = show
                }
                btnShowDetails.setOnClickListener { listener.onShowDetailsClick(model.id) }
                btnRemove.setOnClickListener { listener.onRemoveClick(model.id) }
                executePendingBindings()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteWeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemFavoriteCurrentWeatherBinding.inflate(inflater, parent, false)
        return FavoriteWeatherViewHolder(binding)
    }

    interface FavoriteListener {
        fun onShowDetailsClick(id: Int)
        fun onRemoveClick(id: Int)
    }

    private fun toggleLayout(
        isExpanded: Boolean,
        v: View,
        layoutExpand: LinearLayout
    ): Boolean {
        toggleArrow(v, isExpanded)
        if (isExpanded) {
            expand(layoutExpand)
        } else {
            collapse(layoutExpand)
        }
        return isExpanded
    }

}