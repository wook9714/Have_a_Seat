package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.jinwook.have_a_seat.databinding.ActivityOrderSheetBinding

class OrderSheet : AppCompatActivity() {
    val binding by lazy{ActivityOrderSheetBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragmentList = listOf(FragmentOrderSheetAll(),FragmentOrderSheetPaid(),FragmentOrderSheetNeedPay(),FragmentOrderSheetUsed())
        val adapter = OrderSheetFragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.viewPager.adapter = adapter
        setContentView(R.layout.activity_order_sheet)
    }
}