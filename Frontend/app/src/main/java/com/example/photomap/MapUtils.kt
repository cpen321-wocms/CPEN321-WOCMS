package com.example.photomap

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.Locale

object MapUtils {

    fun getHueFromColor(color: String): Float {
        return when (color.lowercase()) {
            "red" -> BitmapDescriptorFactory.HUE_RED
            "blue" -> BitmapDescriptorFactory.HUE_BLUE
            "green" -> BitmapDescriptorFactory.HUE_GREEN
            "yellow" -> BitmapDescriptorFactory.HUE_YELLOW
            "orange" -> BitmapDescriptorFactory.HUE_ORANGE
            "violet" -> BitmapDescriptorFactory.HUE_VIOLET
            "azure" -> BitmapDescriptorFactory.HUE_AZURE
            else -> BitmapDescriptorFactory.HUE_RED  // Default color if none match
        }
    }

    fun getCityName(context: Context, lat: Double, lng: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        return try {
            val addresses = geocoder.getFromLocation(lat, lng, 1)
            addresses?.firstOrNull()?.locality ?: "Unknown"
        } catch (e: IOException) {
            e.printStackTrace()
            "Unknown"
        }
    }

    fun centerMapOn(map: GoogleMap, latitude: Double, longitude: Double, zoom: Float = 15f) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), zoom))
    }
}
