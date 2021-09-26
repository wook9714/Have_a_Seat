package kr.co.jinwook.have_a_seat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kr.co.jinwook.have_a_seat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu,menu)
        return true
    }

    init {
        instance = this
    }

    companion object {
        public var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.toolbarMain.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation Menu Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnMyOrderBills.setOnClickListener {
            val intentGoOrderSheet = Intent(this,OrderSheet::class.java)
            startActivity(intentGoOrderSheet)

        }


/*

        UIFunction.makeStatusbarTransparent(window)

        UIFunction.makeGuideLineDown(binding.statusbarGuideline)


        Log.d("tag",UIFunction.getStatusBarHeight(resources).toString())


*/


    }





}