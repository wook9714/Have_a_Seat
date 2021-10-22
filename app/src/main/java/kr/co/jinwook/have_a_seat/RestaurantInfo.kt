package kr.co.jinwook.have_a_seat

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kakao.sdk.common.KakaoSdk
import kr.co.jinwook.have_a_seat.databinding.ActivityRestaurantInfoBinding


class RestaurantInfo : AppCompatActivity() {
    val binding by lazy{ActivityRestaurantInfoBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            KakaoMessage.sendMessage(this,KakaoMessage.defaultFeed)
        }




    }




}

