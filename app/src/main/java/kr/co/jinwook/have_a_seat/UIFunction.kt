package kr.co.jinwook.have_a_seat

import android.graphics.Color
import android.view.View
import android.view.Window
import android.app.Activity




object UIFunction {

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

    fun makeStatusbarTransparent(window: Window){


        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE  or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }
}