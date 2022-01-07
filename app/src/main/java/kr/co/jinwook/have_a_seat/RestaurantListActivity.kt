package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.jinwook.have_a_seat.databinding.ActivityRestaurantListBinding

class RestaurantListActivity : AppCompatActivity() {
    val TAG : String = "로그"
    val binding by lazy { ActivityRestaurantListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }


}