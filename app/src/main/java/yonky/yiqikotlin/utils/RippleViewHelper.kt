package yonky.yiqikotlin.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import android.animation.ObjectAnimator
import yonky.yiqikotlin.widget.RippleView


/**
 * Created by Administrator on 2018/8/16.
 */

class RippleViewHelper(private val mRippleView: RippleView) {
    private var animatorSet: AnimatorSet? = null

    init {
        initAnimation()
    }

    private fun initAnimation() {
        val animators = ArrayList<Animator>()
        val shiftAnimator = ObjectAnimator.ofFloat(mRippleView, "wave_shift_ritio", 0f, 1f)
        shiftAnimator.repeatCount = ValueAnimator.INFINITE
        shiftAnimator.duration = 1000
        shiftAnimator.interpolator = LinearInterpolator()
        animators.add(shiftAnimator)

        val amplitudeAnimtor = ObjectAnimator.ofFloat(mRippleView, "amplitude_ritio", 0.1f, 0.2f)
        amplitudeAnimtor.interpolator = LinearInterpolator()
        amplitudeAnimtor.duration = 5000
        amplitudeAnimtor.repeatCount = ValueAnimator.INFINITE
        amplitudeAnimtor.repeatMode = ValueAnimator.REVERSE
        animators.add(amplitudeAnimtor)

        animatorSet = AnimatorSet()
        animatorSet!!.playTogether(animators)
    }

    fun start() {
        if (animatorSet == null) {
            initAnimation()
        }
        animatorSet!!.start()
    }

    fun cancel() {
        if (animatorSet != null) {
            animatorSet!!.cancel()
            animatorSet!!.end()
        }
    }
}
