package yonky.yiqikotlin.widget

import android.content.Context
import android.graphics.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Shader.TileMode
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View


/**
 * Created by Administrator on 2018/8/16.
 */

class RippleView : View {


    private val water_level_ritio = 0.7f
    var amplitude_ritio = 0.05f
        set(amplitude_ritio) {
            field = amplitude_ritio
            invalidate()
        }
    private val wave_length_ritio = 1f
    var wave_shift_ritio = 0f
        set(wave_shift_ritio) {
            field = wave_shift_ritio
            invalidate()
        }


    private var viewPaint: Paint? = null
    private var mShaderMatrix: Matrix? = null

    var isShowView = true
        set(showView) {
            field = showView
            invalidate()
        }
    private var shader: BitmapShader? = null


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        viewPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        viewPaint!!.setColor(Color.WHITE)
        //        viewPaint.setAlpha(100);
        shader = null
        mShaderMatrix = Matrix()

    }

   override protected fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        createShader()
    }

    private fun createShader() {
        val bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val shaderPaint: Paint
        shaderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        shaderPaint.setColor(Color.WHITE)
        //        shaderPaint.setStrokeWidth(2);

        //        y = Asin(wx+t)+h
        val endX: Int
        val endY: Int
        endX = getWidth() + 1
        endY = getHeight() + 1
        val waveY = FloatArray(endX)
        for (beginX in 0 until endX) {
            val wx = beginX.toDouble() * 2.0 * Math.PI / getWidth()
            waveY[beginX] = DEFAULT_WATER_LEVEL_RATIO * getHeight() + DEFAULT_AMPLITUDE_RATIO * getHeight().toFloat() * Math.sin(wx).toFloat()
            canvas.drawLine(beginX.toFloat(), waveY[beginX], beginX.toFloat(), endY.toFloat(), shaderPaint)
        }
        val wave2Shift = getWidth() / 4
        for (beginX in 0 until endX) {
            canvas.drawLine(beginX.toFloat(), waveY[(beginX + wave2Shift) % endX], beginX.toFloat(), endY.toFloat(), shaderPaint)
        }

        shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP)
        viewPaint!!.setShader(shader)
    }

  override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isShowView && shader != null) {
            if (viewPaint!!.getShader() == null) {
                viewPaint!!.setShader(shader)
            }
            mShaderMatrix!!.setScale(wave_length_ritio, this.amplitude_ritio, 0f, (1 - water_level_ritio) * getHeight())

            mShaderMatrix!!.postTranslate(getWidth() * this.wave_shift_ritio, 0f)
            shader!!.setLocalMatrix(mShaderMatrix)

            val borderWidth = if (viewPaint == null) 0f else viewPaint!!.getStrokeWidth()
            //            canvas.drawRect(borderWidth,borderWidth,getWidth()-borderWidth,getHeight()-borderWidth,viewPaint);
            canvas.drawRect(0f, 0f, getWidth().toFloat(), getHeight().toFloat(), viewPaint)
        } else {
            viewPaint!!.setShader(null)
        }

    }

    companion object {
        /**
         * +------------------------+
         * |<--wave length->        |______
         * |   /\          |   /\   |  |
         * |  /  \         |  /  \  | amplitude
         * | /    \        | /    \ |  |
         * |/      \       |/      \|__|____
         * |        \      /        |  |
         * |         \    /         |  |
         * |          \  /          |  |
         * |           \/           | water level
         * +------------------------+__|____
         */

        private val DEFAULT_AMPLITUDE_RATIO = 0.05f
        private val DEFAULT_WATER_LEVEL_RATIO = 0.5f
        private val DEFAULT_WAVE_LENGTH_RATIO = 1.0f
        private val DEFAULT_WAVE_SHIFT_RATIO = 0.0f
    }
}
