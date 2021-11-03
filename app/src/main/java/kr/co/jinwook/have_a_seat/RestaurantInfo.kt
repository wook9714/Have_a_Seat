package kr.co.jinwook.have_a_seat

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kakao.sdk.common.KakaoSdk
import kotlinx.android.synthetic.main.activity_restaurant_info.*
import kr.co.jinwook.have_a_seat.databinding.ActivityRestaurantInfoBinding


class RestaurantInfo : AppCompatActivity() {
    val binding by lazy{ActivityRestaurantInfoBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var viewPager = findViewById(R.id.view_pager00) as ViewPager
        var tabLayout = findViewById(R.id.tab_layout00) as TabLayout

        val fragmentAdapter = RestaurantInfoFrgmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(FragmentOrderSheetAll(),"정보")
        fragmentAdapter.addFragment(FragmentOrderSheetNeedPay(),"메뉴")
        fragmentAdapter.addFragment(FragmentOrderSheetPaid(),"리뷰")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

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
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                head_layout00.callOnClick()
                Log.d(TAG,"About_Fragment_activity - onTabSelected() called")
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
                head_layout00.callOnClick()
                Log.d(TAG,"About_Fragment_activity - onTabReselected() called")
            }
        })




    }



}

