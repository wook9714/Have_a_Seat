package kr.co.jinwook.have_a_seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.jinwook.have_a_seat.databinding.ActivityTestBinding

class Test : AppCompatActivity() {
    val binding by lazy{ActivityTestBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener{
            FirebaseFunction.updateRoomForOrder("testRoom")
        }
        binding.btnJoinRoom.setOnClickListener{
            FirebaseFunction.listenRoomForOrderChange("testRoom",binding)
        }
        binding.btnMakeRoom.setOnClickListener{
            FirebaseFunction.makeRoomForOrder("testRoom")
        }

    }
}