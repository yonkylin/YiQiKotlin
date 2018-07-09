package yonky.yiqikotlin.widget

import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent



/**
 * Created by Administrator on 2018/7/9.
 */

class MyViewPager : ViewPager {
    var isCanSwipe = false

    constructor(@NonNull context: Context) : super(context) {}

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isCanSwipe
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isCanSwipe
    }


    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }


}
