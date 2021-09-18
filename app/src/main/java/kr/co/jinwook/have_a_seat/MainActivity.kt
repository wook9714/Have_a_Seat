package kr.co.jinwook.have_a_seat

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kr.co.jinwook.have_a_seat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        UIFunction.makeStatusbarTransparent(window)

        binding.guideline.setGuidelineBegin(UIFunction.getStatusBarHeight(resources))


        Log.d("tag",UIFunction.getStatusBarHeight(resources).toString())

    }




}