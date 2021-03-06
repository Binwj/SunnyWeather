package com.binyou.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.binyou.sunnyweather.logic.Repository
import com.binyou.sunnyweather.logic.model.Location
import com.binyou.sunnyweather.ui.place.PlaceViewModel

class WeatherViewModel: ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    private val placeViewModel = PlaceViewModel()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

    fun clearSavedPlace() = placeViewModel.clearSavedPlace()
}