package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.jinwook.have_a_seat.databinding.ActivitySearchByLocationBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class SearchByLocation : AppCompatActivity() {
    val binding by lazy{ ActivitySearchByLocationBinding.inflate(layoutInflater)}
    lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView = MapView(this)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.49471488207207, 127.06363198238245), true)
        binding.mapView.addView(mapView)

    
       
    }

    fun makeMarkerByGeoCord(markerName:String,latitude:Double,longitude:Double){
        //마커이름,좌표이용해서 마커생성
        val marker: MapPOIItem = MapPOIItem()
        marker.setItemName(markerName)
        marker.setTag(0)
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude))
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin) // 기본으로 제공하는 BluePin 마커 모양.

        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin) // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.


        mapView.addPOIItem(marker)
    }
}