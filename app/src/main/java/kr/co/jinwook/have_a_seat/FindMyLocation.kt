package kr.co.jinwook.have_a_seat

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.jinwook.have_a_seat.databinding.ActivityFindMyLocationBinding
import java.util.*

class FindMyLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityFindMyLocationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var markerOptions:MarkerOptions

    private lateinit var geocoder:Geocoder





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        geocoder = Geocoder(this, Locale.getDefault())
        binding = ActivityFindMyLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.d("LocationLog","위치 탐색 시작")
        updateLocation()



        mMap.setOnCameraMoveListener(object:GoogleMap.OnCameraMoveListener{
            override fun onCameraMove() {
                Log.d("LocationLog","카메라 이동 중")




                fusedLocationClient.removeLocationUpdates(locationCallback)
                //markerOptions = MarkerOptions().position(mMap.cameraPosition.target).title("내위치")





            }
        })

        mMap.setOnCameraMoveCanceledListener(object:GoogleMap.OnCameraMoveCanceledListener{
            override fun onCameraMoveCanceled() {


           }
        })

        mMap.setOnCameraIdleListener(object:GoogleMap.OnCameraIdleListener{
            override fun onCameraIdle() {
                //지오코드_좌표 주소로 변환

                Log.d("LocationLog","카메라 이동 종료")
                val list = geocoder.getFromLocation(mMap.cameraPosition.target.latitude,mMap.cameraPosition.target.longitude,1)
                if(list.size!=0) {
                    binding.locationText.text = list.get(0).getAddressLine(0).toString()
                }
            }
        })

    }

    @SuppressLint("MissingPermission")
    fun updateLocation(){
        val locationRequest = LocationRequest.create()
        locationRequest.run{
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000

        }
        locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationRequest?.let{
                    for((i,location) in locationResult.locations.withIndex()){
                        Log.d("LocationLog","$i ${location.latitude}, ${location.longitude}")
                        val list = geocoder.getFromLocation(location.latitude,location.longitude,1)
                        Log.d("LocationLog","${list.size}")
                        binding.locationText.text = list.get(0).getAddressLine(0).toString()
                        Log.d("LocationLog","${list.get(0)}")

                        //mMap.clear()
                        //markerOptions.visible(false)







                        setLastLocation(location)
                    }

                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
        //fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun setLastLocation(lastLocation: Location){
        val LATLNG = LatLng(lastLocation.latitude,lastLocation.longitude)


        val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(18.0f).build()
        //mMap.clear()

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

}