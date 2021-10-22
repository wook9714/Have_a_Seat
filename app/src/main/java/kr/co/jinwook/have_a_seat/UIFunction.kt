package kr.co.jinwook.have_a_seat

import android.graphics.Color
import android.view.View
import android.view.Window
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.StorageReference



val TAG : String = "로그"

object UIFunction {

    val storage = Firebase.storage
    val storageRef = storage.reference
    val ONE_MEGABYTE: Long = 1024 * 1024
    var a:Bitmap? = null



    fun downloadAndSetImageViewFromFB(imageView:ImageView,pathString:String){

        //이미지를 미리 리스트에 담아와서 다음에 로드할때 빠르게 할수있도록변경. 이때 이미지로드는 리싸이클러뷰에서 보이는것보다 더 많에 미리 로드.
        storageRef.child(pathString).getBytes(ONE_MEGABYTE).addOnSuccessListener {
            var image: Bitmap? = null
            image = BitmapFactory.decodeByteArray(it,0,it.size)
            imageView.setImageBitmap(image)
            Log.d("myTag","이미지 다운 성공")
            Log.d("myTag",it.size.toString())

        }.addOnFailureListener {

            Log.d("myTag",it.toString())
        }


    }

    fun getStatusBarHeight(resources:android.content.res.Resources): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
    fun makeGuideLineDown(guideLine:androidx.constraintlayout.widget.Guideline){
        guideLine.setGuidelineBegin(getStatusBarHeight(MainActivity.instance!!.resources))


    }

    fun makeGuideLineUp(guideLine:androidx.constraintlayout.widget.Guideline){

        guideLine.setGuidelineBegin(0)

    }

    fun makeStatusBarTransparent( window : Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window?.decorView?.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE  or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                } else {
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
                statusBarColor = Color.TRANSPARENT
            }
        }
        Log.d(TAG,"UIFunction - makeStatusBarTransparent() called")
    }

    fun View.setMarginTop(marginTop: Int) {
        val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        menuLayoutParams.setMargins(0, marginTop, 0, 0)
        this.layoutParams = menuLayoutParams
    }

}