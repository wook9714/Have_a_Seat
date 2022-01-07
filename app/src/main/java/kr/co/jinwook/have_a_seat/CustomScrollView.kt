package kr.co.jinwook.have_a_seat

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView

class CustomScrollView  : NestedScrollView, ViewTreeObserver.OnGlobalLayoutListener {


    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyleAttr : Int) : super(
        context,
        attr,
        defStyleAttr
    ) {

        overScrollMode = OVER_SCROLL_NEVER
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    var header : View?= null

        set(value) {
            field = value
            field?.let {
                it.translationZ = 1f
                it.setOnClickListener{ _ ->
                    this.smoothScrollTo(scrollX,it.top - 130)
                    //TODO 여기 131.25
                    callStickListener()

                }
            }
        }

    var freeListener : (View) -> Unit = {}
    var stickListener : (View) -> Unit = {}

    private var mIsHeaderSticky = false
    private var mHeaderInitPosition = 0f

    override fun onGlobalLayout() {
        mHeaderInitPosition = header?.top?.toFloat() ?: 0f
        //일단 0이라고 하고, 기준점에서 변한정도를 mHeaderInitPosition 이 됨
        // scrolly, mHeaderInitPosition 아래로 스크롤 했을 때 + ㅡㅡf
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        val scrolly = t

        if(scrolly + 130.0f > mHeaderInitPosition) {
            stickHeader(scrolly - mHeaderInitPosition + 130.0f)
            // 스크롤이 더 많이 된만큼 stickHeader 실행
        } else{
            // 아닐경우 이동시키지 않음
            freeHeader()
        }


    }



    private fun freeHeader(){
        header?.translationY = 0f
        //이동시키지 않음
        callFreeListener()
    }

    private fun stickHeader(position : Float){
        // 스크롤이 더 많이 된만큼 해더를 이동시켜줌
        header?.translationY = position
        callStickListener()
    }

// 여기서부터는 플래그 mIsHeaderSticky 관리
    private fun callFreeListener() {
        if(mIsHeaderSticky) {
            freeListener(header ?: return)
            mIsHeaderSticky = false
        }
    }
    private fun callStickListener() {
        if (!mIsHeaderSticky) {
            stickListener(header ?: return)
            mIsHeaderSticky = true
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeOnGlobalLayoutListener(this)
    }



}