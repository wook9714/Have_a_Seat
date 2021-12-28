package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_food_order_process.view.*
import kotlinx.android.synthetic.main.container_in_foodorderprocess.view.*
import kotlinx.android.synthetic.main.foodorderprocess_friendbtn.view.*
import kotlinx.android.synthetic.main.menu_detail_select.view.*
import kr.co.jinwook.have_a_seat.databinding.ActivityFoodOrderProcessBinding
import kr.co.jinwook.have_a_seat.databinding.ContainerInFoodorderprocessBinding
import kr.co.jinwook.have_a_seat.databinding.FoodorderprocessFriendbtnBinding
import kr.co.jinwook.have_a_seat.databinding.MenuDetailSelectBinding

class FoodOrderProcess : AppCompatActivity() {
    val binding by lazy{ActivityFoodOrderProcessBinding.inflate(layoutInflater)}
    val foodDataList:ArrayList<FoodContainerData> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        for(i in foodDataList){
            addContainerInFoodOrderProcessing(i)
        }
        binding.button.setOnClickListener {
            val tmp = FoodorderprocessFriendbtnBinding.inflate(LayoutInflater.from(this),binding.friendList,false).root

            binding.friendList.addView(tmp)

        }

    }
    fun loadData(){
        for(i in 1..10){
            foodDataList.add(FoodContainerData("foodName${i}","foodInfo${i}",i*1000))

        }
    }

    fun addContainerInFoodOrderProcessing(data:FoodContainerData){
        val container = ContainerInFoodorderprocessBinding.inflate(LayoutInflater.from(this),binding.testLayout,false).root
        container.text_foodName.text = data.foodName
        container.text_foodInfo.text = data.foodInfo
        container.text_price.text = data.price.toString()

        binding.testLayout.addView(container)
    }

}

data class FoodContainerData(var foodName:String,var foodInfo:String, var price:Int)

