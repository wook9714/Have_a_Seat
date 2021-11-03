package kr.co.jinwook.have_a_seat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kr.co.jinwook.have_a_seat.databinding.ActivityMainBinding
import net.daum.mf.map.api.MapReverseGeoCoder
import android.content.pm.PackageManager

import android.content.pm.PackageInfo
import android.util.Base64
import java.lang.Exception
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        getAppKeyHash()
        setSupportActionBar(toolbarMain)
        binding.toolbarMain.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation Menu Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnMyOrderBills.setOnClickListener {
            val intentGoOrderSheet = Intent(this,OrderSheet::class.java)
            startActivity(intentGoOrderSheet)


        }
        binding.btnGpsMapping.setOnClickListener {
            val intentGoOrderSheet = Intent(this,SearchByLocation::class.java)
            startActivity(intentGoOrderSheet)

        }
        LocationFuncion.getLocationNamebyGps(this,37.496556, 127.070630)





/*
        UIFunction.makeStatusbarTransparent(window)

        UIFunction.makeGuideLineDown(binding.statusbarGuideline)


        Log.d("tag",UIFunction.getStatusBarHeight(resources).toString())
*/
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


    private fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something: String = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString())
        }
    }



}