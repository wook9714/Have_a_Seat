package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kr.co.jinwook.have_a_seat.databinding.ActivityFoodOrderProcessBinding
import kr.co.jinwook.have_a_seat.databinding.MenuDetailSelectBinding

class FoodOrderProcess : AppCompatActivity() {
    val binding by lazy{ActivityFoodOrderProcessBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var index = 0

        binding.button.setOnClickListener {
            index++




            binding.testLayout.addView(MenuDetailSelectBinding.inflate(layoutInflater).linearLayout)
        }




    }

}