package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.ListFragment
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.jinwook.have_a_seat.databinding.ActivityOrderSheetBinding
import net.daum.mf.map.api.MapReverseGeoCoder




class OrderSheet : AppCompatActivity() {
    val binding by lazy{ActivityOrderSheetBinding.inflate(layoutInflater)}





    override fun onCreate(savedInstanceState: Bundle?) {
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







    }


}