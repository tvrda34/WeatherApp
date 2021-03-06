package com.example.sofascoreacademy.project.model

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


@Entity
data class Locations(
        val title: String,
        val location_type: String,
        @PrimaryKey
        val woeid: Int,
        val latt_long: String
) : Serializable

@Entity
data class LocationDb(
        val title: String,
        val location_type: String,
        @PrimaryKey
        val woeid: Int,
        val latt_long: String,
        var position: Int
) : Serializable

data class LocLatt(
        val title: String,
        val location_type: String,
        val woeid: Int,
        val latt_long: String,
        val distance: Int
) : Serializable


data class SpecLoc(
        val parent: Locations,
        val consolidated_weather: List<City>,
        val timezone_name: String,
        val title: String,
        val time: String,
        val timezone: String
) : Serializable {


    @SuppressLint("SimpleDateFormat")
    private fun format(str: String): String {
        // expected input format "2020-05-24T08:19:40.807726-05:00"
        try {
            val slits = str.split(".")
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            inputFormat.timeZone = TimeZone.getTimeZone(timezone/*"Europe/London"*/)

            val outputFormat = SimpleDateFormat("h:mm a")
            outputFormat.timeZone = inputFormat.timeZone
            return outputFormat.format(inputFormat.parse(slits[0])!!)
        } catch (e: Exception) {

        }
        return "-"
    }

    fun formattedTime(): String {
        return format(time)
    }

}

@Entity
data class City(
        @PrimaryKey
        val id: Long,
        val weather_state_name: String,
        val weather_state_abr: String,
        val wind_direction_compass: String,
        val applicable_date: String,
        val min_temp: Double,      //celsius
        val max_temp: Double,
        val the_temp: Double,
        val wind_speed: Float,  //mph
        val wind_direction: Float,  //degrees
        val air_pressure: Float,    //mbar
        val humidity: Float,    //%
        val visibility: Float, //miles
        val predictability: Int,
        val created: String//%
) : Serializable {
    fun formattedApplicableDate(): String = formattedApplicableDate("EEE, MMM d")

    fun formattedDay(): Int {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(applicable_date)
        return date.day
    }

    fun formattedApplicableDate(pattern: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(applicable_date)
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return format.format(date!!)
    }

    fun formattedFullApplicableDate(): String =
            formattedApplicableDate("EEE, dd MMM yyyy")


}

data class favouriteCity(
        val woeid: Int,
        val time: String,
        val timezone_name: String,
        val timezone: String,
        val consolidated_weather: List<City>
) : Serializable {
    @SuppressLint("SimpleDateFormat")
    private fun format(str: String): String {
        // expected input format "2020-05-24T08:19:40.807726-05:00"
        try {
            val slits = str.split(".")
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            inputFormat.timeZone = TimeZone.getTimeZone(timezone/*"Europe/London"*/)

            val outputFormat = SimpleDateFormat("h:mm a")
            outputFormat.timeZone = inputFormat.timeZone
            return outputFormat.format(inputFormat.parse(slits[0])!!)
        } catch (e: Exception) {

        }
        return "-"
    }

    fun formattedTime(): String {
        return format(time)
    }
}

@Entity
data class Recent(
        val title: String,
        val location_type: String,
        @PrimaryKey
        val woeid: Int,
        val latt_long: String
) : Serializable

@Entity
data class BaseCity(
        val title: String,
        @PrimaryKey
        val latt_long: String
) : Serializable


