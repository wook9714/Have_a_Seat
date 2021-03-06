package kr.co.jinwook.have_a_seat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.fragment.app.ListFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_order_sheet.*
import kr.co.jinwook.have_a_seat.databinding.ActivityOrderSheetBinding





class OrderSheet : AppCompatActivity() {
    val binding by lazy{ActivityOrderSheetBinding.inflate(layoutInflater)}





    override fun onCreate(savedInstanceState: Bundle?) {

        UIFunction.makeStatusBarTransparent(window)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragmentList = listOf(FragmentOrderSheetAll(),FragmentOrderSheetPaid(),FragmentOrderSheetNeedPay(),FragmentOrderSheetUsed())
        val adapter = OrderSheetFragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.viewPager.adapter = adapter


        val tabTitles = listOf<String>(*resources.getStringArray(R.array.order_sheet_tab_layout_texts))
        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab,position->
            tab.text = tabTitles[position]
        }.attach()

        binding.testButton.setOnClickListener {
            val intent = Intent(this,RestaurantInfo::class.java)
            startActivity(intent)
        }





    }


}