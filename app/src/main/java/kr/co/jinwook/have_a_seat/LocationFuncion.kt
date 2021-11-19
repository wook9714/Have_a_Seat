package kr.co.jinwook.have_a_seat

import android.app.Activity
import android.content.Context
import android.util.Log
import net.daum.android.map.coord.MapCoord
import net.daum.android.map.geocoding.ReverseGeoCodingWebService
import net.daum.mf.map.api.MapPoint

import net.daum.mf.map.api.MapReverseGeoCoder




class LocationFuncion {

    var resultListner:MapReverseGeoCoder.ReverseGeoCodingResultListener? = null

    fun addResultListner(resultListner:MapReverseGeoCoder.ReverseGeoCodingResultListener){
        this.resultListner = resultListner
    }

    fun getLocationNamebyGps(activity:Activity,latitude:Double,longitude:Double){
        val mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)

        val reverseGeoCoder = MapReverseGeoCoder(
            activity.getString(R.string.kakao_app_key),
            mapPoint,
            resultListner,
            activity
        )
        reverseGeoCoder.startFindingAddress()
    }

}
