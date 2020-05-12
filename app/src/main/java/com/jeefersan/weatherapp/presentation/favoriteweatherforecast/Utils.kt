package com.jeefersan.weatherapp.presentation.favoriteweatherforecast

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.misc.toAbbreviatedWeekDay
import com.jeefersan.weatherapp.misc.toHourlyDate
import com.jeefersan.weatherapp.misc.toWeekDay
import com.jeefersan.weatherapp.models.DailyWeatherModel
import com.jeefersan.weatherapp.models.HourlyWeatherModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json.Default.context
import java.util.*

/**
 * Created by JeeferSan on 11-5-20.
 */


fun setDataHourlyTemperature(
    hourlyForecast: List<HourlyWeatherModel>,
    lineChart: LineChart,
    cityName: String
) {
    val entries = mutableListOf<Entry>()
    var maxTemp: Int = Int.MIN_VALUE
    var minTemp: Int = Int.MAX_VALUE

    for (i in hourlyForecast.indices) {
        val temp = hourlyForecast[i].temperature
        entries.add(
            Entry(hourlyForecast[i].timeStamp.toFloat(), hourlyForecast[i].temperature.toFloat())
        )
        if (temp > maxTemp) maxTemp = temp
        if (temp < minTemp) minTemp = temp
    }

    val dataSet = LineDataSet(entries, "Temperature")
        .apply {
            lineWidth = 4f
            circleRadius = 7f
            isHighlightEnabled = false
            setCircleColor(R.color.colorAccent)
            valueTextSize = 12f
            valueTextColor = Color.GRAY
            mode = LineDataSet.Mode.LINEAR
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return String.format(Locale.getDefault(), "%.0f °C", value)
                }
            }
        }

    val lineData = LineData(dataSet)

    lineChart.apply {
        description.text = "Hourly temperatures for $cityName"
        description.textSize = 14f
        description.textColor = context.getColor(R.color.colorPrimary)
        description.isEnabled = true
        axisLeft.setDrawLabels(false)
        axisRight.setDrawLabels(false)
        legend.isEnabled = true
        legend.textSize = 14f
        setScaleEnabled(false)
        data = lineData
        animateY(900)
    }

    lineChart.xAxis
        .apply {
            setDrawLabels(true)
            textSize = 12f
            setDrawGridLines(false)
            labelCount = entries.size
            axisMaximum = hourlyForecast.last().timeStamp + 1500.toFloat()
            axisMinimum = hourlyForecast.first().timeStamp - 1500.toFloat()
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toLong().toHourlyDate()
                }
            }
            setDrawAxisLine(true)
        }

    lineChart.axisLeft
        .apply {
            axisMaximum = maxTemp + 3.toFloat()
            axisMinimum = minTemp - 3.toFloat()
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                    return hourlyForecast[value.toInt()].temperature.toString()
                }
            }
            setDrawAxisLine(false)
            setDrawLabels(true)
            setDrawGridLines(false)

        }
}

fun setDataHumidity(
    hourlyForecast: List<HourlyWeatherModel>,
    lineChart: LineChart,
    cityName: String
) {
    val entries = mutableListOf<Entry>()

    for (i in hourlyForecast.indices) {
        entries.add(
            Entry(hourlyForecast[i].timeStamp.toFloat(), hourlyForecast[i].humidity.toFloat())
        )
    }

    val dataSet = LineDataSet(entries, "Humidity")
        .apply {
            lineWidth = 4f
            circleRadius = 7f
            isHighlightEnabled = false
            color = R.color.redColor
            setCircleColor(R.color.redColor)
            valueTextSize = 12f
            valueTextColor = Color.DKGRAY
            mode = LineDataSet.Mode.LINEAR
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "${value.toInt()} %"
                }
            }
        }


    val lineData = LineData(dataSet)

    lineChart.apply {
        description.text = "Humidity per hour for $cityName"
        description.textSize = 14f
        description.textColor = context.getColor(R.color.colorPrimary)
        description.isEnabled = true
        axisLeft.setDrawLabels(false)
        axisRight.setDrawLabels(false)
        legend.isEnabled = true
        legend.textSize = 14f
        setScaleEnabled(false)
        data = lineData
        animateX(900)
    }

    lineChart.xAxis
        .apply {
            setDrawLabels(true)
            setDrawGridLines(false)
            textSize = 12f
            labelCount = entries.size
            axisMaximum = hourlyForecast.last().timeStamp + 1500.toFloat()
            axisMinimum = hourlyForecast.first().timeStamp - 1500.toFloat()
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toLong().toHourlyDate()
                }
            }
            setDrawAxisLine(true)
            setDrawAxisLine(false)
            setDrawLabels(true)
            setDrawGridLines(false)
        }

    lineChart.axisLeft
        .apply {
            axisMaximum = 100f
            axisMinimum = 0f
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                    return hourlyForecast[value.toInt()].temperature.toString()
                }
            }
            setDrawAxisLine(false)
            setDrawLabels(true)
            setDrawGridLines(false)
        }
}


fun setDataHourlyRainVolume(
    hourlyForecast: List<HourlyWeatherModel>,
    barChart: BarChart,
    cityName: String
) {
    val entries = mutableListOf<BarEntry>()
    val maxRainVolume = hourlyForecast.maxBy { it.rain ?: 0.0 }?.rain ?: 0.0

    for (i in hourlyForecast.indices) {
        entries.add(
            BarEntry(hourlyForecast[i].timeStamp.toFloat(), hourlyForecast[i].rain?.toFloat() ?: 0f)
        )
    }


    val barDataSet = BarDataSet(entries, "Rain volume")
        .apply {
            barBorderWidth = 0.4f
            isHighlightEnabled = false
            color = R.color.blue
            valueTextSize = 12f
            valueTextColor = Color.DKGRAY
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    if (value == 0f) return ""
                    return "${value.toInt()} mm"
                }
            }
        }


    val barData = BarData(barDataSet)
    barData.barWidth = 0.7f

    barChart.apply {
        axisLeft.setDrawLabels(false)
        axisRight.setDrawLabels(false)
        data = barData
        setFitBars(true)
        animateY(800)
        legend.isEnabled = true
        legend.textSize = 14f
        setScaleEnabled(false)
        description.text = "Rain volume per hour for $cityName"
        description.textSize = 14f
        description.textColor = context.getColor(R.color.colorPrimary)
        description.isEnabled = true
        invalidate()
    }

    barChart.xAxis.apply {
        setDrawLabels(true)
        setDrawGridLines(false)
        position = XAxis.XAxisPosition.BOTTOM
        textSize = 12f
        labelCount = entries.size
        axisMaximum = hourlyForecast.last().timeStamp + 1500.toFloat()
        axisMinimum = hourlyForecast.first().timeStamp - 1500.toFloat()
        position = XAxis.XAxisPosition.BOTTOM
        valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toLong().toHourlyDate()
            }

        }
        setDrawAxisLine(true)
    }



    barChart.axisLeft
        .apply {
            setDrawAxisLine(false)
            setDrawLabels(true)
            setDrawGridLines(false)
            axisMinimum = 0f
            axisMaximum = maxRainVolume.toFloat() + 4f
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {

                    return "${value.toInt()} mm"
                }
            }
        }


}


fun setDataDaily(
    weeklyForecast: List<DailyWeatherModel>,
    lineChart: LineChart
) {
    val minTempEntries = mutableListOf<Entry>()
    val maxTempEntries = mutableListOf<Entry>()

    var maxTmp: Int = Int.MIN_VALUE
    var minTmp: Int = Int.MAX_VALUE

    for (i in weeklyForecast.indices) {
        val minTemp = weeklyForecast[i].minTemp
        val maxTemp = weeklyForecast[i].maxTemp
        minTempEntries.add(
            Entry(weeklyForecast[i].date.toFloat(), minTemp.toFloat(), i)
        )

        maxTempEntries.add(
            Entry(weeklyForecast[i].date.toFloat(), maxTemp.toFloat(), i)
        )

        if (maxTemp > maxTmp) maxTmp = maxTemp
        if (minTemp < minTmp) minTmp = minTemp
    }

    val minTempDataSet = LineDataSet(minTempEntries, "Minimum temperature")
        .apply {
            lineWidth = 4f
            circleRadius = 7f
            valueTextSize = 12f
            setCircleColor(Color.GRAY)
            isHighlightEnabled = true
            color = Color.RED
            valueTextColor = Color.GRAY
            mode = LineDataSet.Mode.CUBIC_BEZIER
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return String.format(Locale.getDefault(), "%.0f °C", value)
                }
            }
        }

    val maxTempDataSet = LineDataSet(maxTempEntries, "Maximum temperature")
        .apply {
            lineWidth = 4f
            circleRadius = 7f
            valueTextSize = 12f
            isHighlightEnabled = true
            setCircleColor(Color.GRAY)
            valueTextColor = Color.GRAY
            mode = LineDataSet.Mode.CUBIC_BEZIER
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return String.format(Locale.getDefault(), "%.0f °C", value)
                }
            }
        }




    val lineData = mutableListOf<ILineDataSet>()
    lineData.add(minTempDataSet)
    lineData.add(maxTempDataSet)



    lineChart.apply {
        description.text = "Weekly Forecast"
        description.textSize = 14f
        description.textColor = context.getColor(R.color.colorPrimary)
        description.isEnabled = true
        maxHighlightDistance = 12f
        axisRight.setDrawLabels(false)
        legend.isEnabled = true
        setScaleEnabled(false)
        legend.textSize = 14f
        data = LineData(lineData)
        animateXY(700, 900)
    }


    lineChart.xAxis
        .apply {
            setDrawLabels(true)
            setDrawGridLines(false)
            textSize = 12f
            labelCount = maxTempEntries.size
            axisMaximum = weeklyForecast.last().date + 1500.toFloat()
            axisMinimum = weeklyForecast.first().date - 1500.toFloat()
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toLong().toAbbreviatedWeekDay()
                }
            }
            setDrawAxisLine(true)
            setDrawAxisLine(false)
            setDrawLabels(true)
            setDrawGridLines(false)
        }

    lineChart.axisLeft
        .apply {
            axisMaximum = maxTmp + 3.toFloat()
            axisMinimum = minTmp - 3.toFloat()
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                    return weeklyForecast[value.toInt()].maxTemp.toString()
                }
            }
            setDrawAxisLine(false)

            setDrawLabels(true)
            setDrawGridLines(false)
        }




}