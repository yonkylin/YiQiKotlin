package yonky.yiqikotlin.v

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_splash.*
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.showToast

/**
 * Created by Administrator on 2018/7/6.
 */
class SplashActivity:BaseActivity(){

    var alphaAnimation:AlphaAnimation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initData() {
    }

    override fun initView() {
        alphaAnimation= AlphaAnimation(0.3f,1.0f)
        alphaAnimation?.duration=2000
        alphaAnimation?.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationEnd(p0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(p0: Animation) {
            }

            override fun onAnimationStart(p0: Animation) {
            }
        })
        checkPermission()
    }

    fun redirectTo(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun start() {
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission(){
        val permissionItems =ArrayList<PermissionItem>()
//        permissionItems.add(PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone))
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,"存储空间",R.drawable.permission_ic_storage))
        HiPermission.create(this)
                .title("亲爱的上帝")
                .msg("为了能够正常使用，请开启这些权限吧！")
                .permissions(permissionItems)
//                .style(R.style.PermissionDefaultBlueStyle)
//                .animStyle(R.style.PermissionAnimScale)
                .checkMutiPermission(object : PermissionCallback {
                    override fun onClose() {
                        Logger.i( "permission_onClose")
                        showToast("用户关闭了权限")
                    }

                    override fun onFinish() {
//                        showToast("初始化完毕！")
                        layout_splash.startAnimation(alphaAnimation)
                    }

                    override fun onDeny(permission: String, position: Int) {
                        Logger.i("permission_onDeny")
                    }

                    override fun onGuarantee(permission: String, position: Int) {
                        Logger.i("permission_onGuarantee")
                    }
                })
    }
}