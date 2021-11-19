package kr.co.jinwook.have_a_seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.jinwook.have_a_seat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        FirebaseFunction.auth = Firebase.auth //파이어베이스 auth 초기화

        //임시로 익명로그인
        if(FirebaseFunction.auth.currentUser ==null){
            Log.d("myTag","로그인 안됨")
            FirebaseFunction.signInAnonymously(this)

        }
        else{
            Log.d("myTag",FirebaseFunction.auth.uid!!)
            FirebaseFunction.login()
        }
        //FirebaseFunction.test()



        setSupportActionBar(toolbarMain)
        binding.toolbarMain.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation Menu Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnMyOrderBills.setOnClickListener {
            val intentGoOrderSheet = Intent(this,OrderSheet::class.java)
            startActivity(intentGoOrderSheet)


        }
        binding.btnGpsMapping.setOnClickListener {
            val intentGoGpsMapping = Intent(this,SearchByLocation::class.java)
            startActivity(intentGoGpsMapping)

        }

        binding.testButton.setOnClickListener{
            val intentGoTestScene = Intent(this,Test::class.java)
            startActivity(intentGoTestScene)
        }

        //LocationFuncion.getLocationNamebyGps(this,37.496556, 127.070630)



        binding.btnRestaurantInfo.setOnClickListener {
            val intentGoRestaurantInfo = Intent(this,RestaurantInfo::class.java)
            startActivity(intentGoRestaurantInfo)
        }




        UIFunction.makeStatusBarTransparent(window)
/*
        UIFunction.makeGuideLineDown(binding.statusbarGuideline)


        Log.d("tag",UIFunction.getStatusBarHeight(resources).toString())
*/
    }

    private var backBtnTime : Long = 0

    override fun onBackPressed() {

        var currentTime : Long = System.currentTimeMillis()
        var gapTime : Long = currentTime - backBtnTime

        if (gapTime in 0..2000) {
            super.onBackPressed()
        }else{
            backBtnTime = currentTime
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show()
        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId
        when(itemView){
            R.id.myProfile -> Toast.makeText(applicationContext, "내 프로필 Clicked", Toast.LENGTH_SHORT).show()
            R.id.notify -> Toast.makeText(applicationContext, "내 알림 Clicked", Toast.LENGTH_SHORT).show()
        }
        return false
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







}