package kr.co.jinwook.have_a_seat

import android.app.Activity
import android.content.Context
import android.util.Log
import net.daum.android.map.coord.MapCoord
import net.daum.android.map.geocoding.ReverseGeoCodingWebService
import net.daum.mf.map.api.MapPoint

import net.daum.mf.map.api.MapReverseGeoCoder




object LocationFuncion {

    fun getLocationNamebyGps(activity:Activity,latitude:Double,longitude:Double):String{
        val mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)

        val resultListner = ResultListner()

        val reverseGeoCoder = MapReverseGeoCoder(
            "fbe5938f443d060af0d220916f20e1dc",
            mapPoint,
            resultListner,
            activity
        )
        reverseGeoCoder.startFindingAddress()
        return ""
    }

}
class ResultListner:MapReverseGeoCoder.ReverseGeoCodingResultListener{

    override fun onReverseGeoCoderFoundAddress(p0: MapReverseGeoCoder?, p1: String?) {

        Log.d("myTag",p1!!)
    }

    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {
        Log.d("myTag","오류")
    }
}