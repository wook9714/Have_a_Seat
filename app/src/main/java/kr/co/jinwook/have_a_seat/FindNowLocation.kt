package kr.co.jinwook.have_a_seat

import android.app.Activity
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.common.collect.MapMaker
import kr.co.jinwook.have_a_seat.databinding.ActivityFindNowLocationBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder
import net.daum.mf.map.api.MapView

class FindNowLocation : AppCompatActivity() {
    val binding by lazy{ActivityFindNowLocationBinding.inflate(layoutInflater)}
    val myActivity:Activity = this
    lateinit var mapView: MapView
    var mLocationManager: LocationManager? = null
    var mLocationListner:LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        mapView = MapView(this)
        mapView.setZoomLevel(1,false)

        val marker = MapPOIItem()


        marker.apply {
            itemName = "서울시청"   // 마커 이름
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5666805, 126.9784147)   // 좌표
            markerType = MapPOIItem.MarkerType.RedPin


            setCustomImageAnchor(0.5f, 1.0f)    // 마커 이미지 기준점
        }

        mapView.addPOIItem(marker)



        mapView.setCurrentLocationEventListener(object:MapView.CurrentLocationEventListener{
            override fun onCurrentLocationUpdate(p0: MapView?, p1: MapPoint?, p2: Float) {
                TODO("Not yet implemented")
            }

            override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
                TODO("Not yet implemented")
            }

            override fun onCurrentLocationUpdateFailed(p0: MapView?) {
                TODO("Not yet implemented")
            }

            override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
                TODO("Not yet implemented")
            }
        })

        mapView.setMapViewEventListener(object:MapView.MapViewEventListener{
            override fun onMapViewInitialized(p0: MapView?) {
                Toast.makeText(applicationContext,"지도를 움직여 위치를 설정하세요.",Toast.LENGTH_LONG).show()
            }

            override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {

            }

            override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {

            }

            override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
                val locationFunc = LocationFuncion()
                locationFunc.addResultListner(object:MapReverseGeoCoder.ReverseGeoCodingResultListener{
                    override fun onReverseGeoCoderFoundAddress(
                        p0: MapReverseGeoCoder?,
                        p1: String?
                    ) {

                        binding.locationInfoTextView.text = p1
                    }

                    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {
                        Toast.makeText(applicationContext,"위치를 찾을 수 없음",Toast.LENGTH_LONG).show()
                    }
                })



                locationFunc.getLocationNamebyGps(myActivity,mapView.mapCenterPoint.mapPointGeoCoord.latitude,mapView.mapCenterPoint.mapPointGeoCoord.longitude)
            }
        })
        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(36.49471488207207, 126.06363198238245), true)

        binding.mapView.addView(mapView)


    }


}