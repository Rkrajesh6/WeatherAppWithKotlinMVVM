package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("lon")
                 val lon: Double = 0.0,
                 @SerializedName("lat")
                 val lat: Double = 0.0)


data class Wind(@SerializedName("deg")
                val deg: Int = 0,
                @SerializedName("speed")
                val speed: Double = 0.0)


data class WeatherItem(@SerializedName("icon")
                       val icon: String = "",
                       @SerializedName("description")
                       val description: String = "",
                       @SerializedName("main")
                       val main: String = "",
                       @SerializedName("id")
                       val id: Int = 0)


data class Clouds(@SerializedName("all")
                  val all: Int = 0)


data class WeatherResponse(@SerializedName("city")
                           val city: City,
                           @SerializedName("cnt")
                           val cnt: Int = 0,
                           @SerializedName("cod")
                           val cod: String = "",
                           @SerializedName("message")
                           val message: Int = 0,
                           @SerializedName("list")
                           val list: List<ListItem>?)


data class City(@SerializedName("country")
                val country: String = "",
                @SerializedName("coord")
                val coord: Coord,
                @SerializedName("sunrise")
                val sunrise: Int = 0,
                @SerializedName("timezone")
                val timezone: Int = 0,
                @SerializedName("sunset")
                val sunset: Int = 0,
                @SerializedName("name")
                val name: String = "")


data class ListItem(@SerializedName("dt")
                    val dt: Int = 0,
                    @SerializedName("dt_txt")
                    val dtTxt: String = "",
                    @SerializedName("weather")
                    val weather: List<WeatherItem>?,
                    @SerializedName("main")
                    val main: Main,
                    @SerializedName("clouds")
                    val clouds: Clouds,
                    @SerializedName("sys")
                    val sys: Sys,
                    @SerializedName("wind")
                    val wind: Wind)


data class Sys(@SerializedName("pod")
               val pod: String = "")


data class Main(@SerializedName("temp")
                val temp: Double = 0.0,
                @SerializedName("temp_min")
                val tempMin: Double = 0.0,
                @SerializedName("grnd_level")
                val grndLevel: Int = 0,
                @SerializedName("temp_kf")
                val tempKf: Double = 0.0,
                @SerializedName("humidity")
                val humidity: Int = 0,
                @SerializedName("pressure")
                val pressure: Int = 0,
                @SerializedName("sea_level")
                val seaLevel: Int = 0,
                @SerializedName("feels_like")
                val feelsLike: Double = 0.0,
                @SerializedName("temp_max")
                val tempMax: Double = 0.0)


