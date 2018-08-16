package yonky.yiqikotlin.v.fragment

import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseFragment
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.fragment_login.*
import yonky.yiqikotlin.utils.MyUtil
import yonky.yiqikotlin.utils.RippleViewHelper


/**
 * Created by Administrator on 2018/8/16.
 */
class LoginFragment: BaseFragment(){
    lateinit var  helper:RippleViewHelper
    val animatorSet=AnimatorSet()

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun lazyLoad() {
        iv_logo.setScaleX(0f);
        iv_logo.setScaleY(0f);
        helper.start();
        animatorSet.start();
    }

    override fun initView() {
        helper = RippleViewHelper(ripple_view)
        val csAnimation = ObjectAnimator.ofFloat(cs_login_info, "translationX", MyUtil.getDisplayWidth(mContext).toFloat(), 0f)
        csAnimation.setInterpolator(OvershootInterpolator())
        csAnimation.setDuration(1000)
        val ivAnimation1 = ObjectAnimator.ofFloat(iv_logo, "scaleX", 0f, 1f)
        val ivAnimation2 = ObjectAnimator.ofFloat(iv_logo, "scaleY", 0f, 1f)
        animatorSet.playTogether(ivAnimation1, ivAnimation2)
        animatorSet.play(ivAnimation1).after(csAnimation)
    }

    override fun lazyLoadDataIfPrepared() {
        if(userVisibleHint && isViewPrepare){
            lazyLoad()
        }
    }

    override fun onPause() {
        super.onPause()
        helper.cancel()
    }
}