package yonky.yiqikotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import yonky.yiqikotlin.utils.MyUtil
import yonky.yiqikotlin.R.color.light
import yonky.yiqikotlin.R.color.light_background
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Path
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.widget.LinearLayout
import yonky.yiqikotlin.R


class RegionLinearLayout : LinearLayout {
    private var mPaint1: Paint? = null
    private var mPaint2: Paint? = null
    private var path1: Path? = null
    private var path2: Path? = null
    lateinit var mContext: Context

   var x0 = 0f
   var h :Float =0f
   var x1 :Float =0f
   var x2:Float =0f
   var x3 :Float =0f
    constructor(context: Context) : super(context) {
        init(context)

    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }




    private fun init(mContext: Context) {
        setWillNotDraw(false)
        this.mContext = mContext

         h = MyUtil.dp2px(mContext, 5).toFloat()
         x1 = MyUtil.dp2px(mContext, 40).toFloat()
       x2 = MyUtil.dp2px(mContext, 45).toFloat()
        x3 = MyUtil.dp2px(mContext, 50).toFloat()


        mPaint1 = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint2!!.style=Paint.Style.STROKE
        mPaint2!!.setColor(resources.getColor(R.color.light))

        mPaint1!!.setColor(resources.getColor(R.color.light_background))
        path1 = Path()
        path2 = Path()

    }

    override  fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path1!!.moveTo(x0, h)
        path1!!.lineTo(x1, h)
        path1!!.lineTo(x2, 0f)
        path1!!.lineTo(x3, h)
        path1!!.lineTo(width.toFloat(), h)
        path1!!.lineTo(width.toFloat(), height.toFloat())
        path1!!.lineTo(0f, height.toFloat())
        path1!!.close()
        canvas.drawPath(path1, mPaint1)


        path2!!.moveTo(x0, h)
        path2!!.lineTo(x1, h)
        path2!!.lineTo(x2, 0f)
        path2!!.lineTo(x3, h)
        path2!!.lineTo(width.toFloat(), h)
//        path2!!.lineTo(width.toFloat(), 0f)
//        path2!!.close()
        canvas.drawPath(path2, mPaint2)
    }
}
