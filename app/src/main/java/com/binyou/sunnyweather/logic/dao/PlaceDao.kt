package com.binyou.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.binyou.sunnyweather.SunnyWeatherApplication
import com.binyou.sunnyweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit() {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun clearSavedPlace() {
        sharedPreferences().edit() {
            clear()
            commit()
        }
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = SunnyWeatherApplication.context.
        getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}