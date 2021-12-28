package kr.co.jinwook.have_a_seat

import android.app.Application
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kakao.sdk.common.KakaoSdk
import kotlinx.android.synthetic.main.activity_restaurant_info.*
import kr.co.jinwook.have_a_seat.databinding.ActivityRestaurantInfoBinding
import android.R
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RestaurantInfo : AppCompatActivity() {
    val TAG : String = "로그"



    val binding by lazy{ActivityRestaurantInfoBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {

        UIFunction.makeStatusBarTransparent(window)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getRepresentImageNumber()


        val fragmentAdapter = RestaurantInfoFrgmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(FragmentOrderSheetAll(),"정보")
        fragmentAdapter.addFragment(FragmentOrderSheetNeedPay(),"메뉴")
        fragmentAdapter.addFragment(FragmentOrderSheetPaid(),"리뷰")

        binding.viewPager00.adapter = fragmentAdapter
        binding.tabLayout00.setupWithViewPager(view_pager00)

        fragmentAdapter.fragmenttitle

        scrollview00.run {
            header = head_layout00

            stickListener = { _ ->
                Log.d(TAG, "Stick Listener")

            }
            freeListener = { _ ->
                Log.d(TAG, "Free Listener")

            }
        }






        // tablayout의 탭 선택 시 tablayout을 담고있는 linearlayout(head_layout00)이 클릭되었을 때의 효과를 실행
        // headlayout 클릭효과와 동시에 setexpanded(Boolean)을 작동시켜 collapsinglayout이 collapse되도록 설정
        binding.tabLayout00.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                head_layout00.callOnClick()
                Log.d(TAG,"About_Fragment_activity - onTabSelected() called")
                appbarLayout.setExpanded(false)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
                head_layout00.callOnClick()
                Log.d(TAG,"About_Fragment_activity - onTabReselected() called")
                appbarLayout.setExpanded(false)
            }
        })











    }

    lateinit var restaurantName : String
    var representImageNumber : String =""

    fun getRepresentImageNumber(){
        Log.d(TAG, "RestaurantInfo - getRepresentImageNumber() called")
        val db2 = Firebase.firestore
        val restaurant = db2.collection("restaurant")
        restaurant.whereEqualTo("restaurantName", "부산아지매국밥").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "representImage 의 개수는 ${document.getString("representImageNumber")}")
                    representImageNumber = document.getString("representImageNumber").toString()


                }
                makeImageNumberList(representImageNumber)

                restaurantName = "부산아지매국밥"
                var representImagePagerAdapter = RepresentImagePagerAdapter()
                representImagePagerAdapter.activity = this@RestaurantInfo
                representImagePagerAdapter.restaurantName = restaurantName
                representImagePagerAdapter.imageNumberList = makeImageNumberList(representImageNumber)

                binding.viewPagerRepresentImage.adapter = representImagePagerAdapter

            }.addOnFailureListener {

                Toast.makeText(this@RestaurantInfo, "Failed", Toast.LENGTH_LONG).show()
            }

    }



    private fun makeImageNumberList(representImageNumber : String): MutableList<String> {
        Log.d(TAG, "RestaurantInfo - makeImageNumberList() called")
        var imageNumberList = mutableListOf<String>()
        var representImageNumberToInt  = representImageNumber.toInt()
        Log.d(TAG, "makeImageNumberList: $representImageNumberToInt")

        for (i in 1..representImageNumberToInt){
            var listMember = i.toString()
            imageNumberList.add(listMember)

        }
        Log.d(TAG, "makeImageNumberList: $imageNumberList")
        return imageNumberList
    }



}

