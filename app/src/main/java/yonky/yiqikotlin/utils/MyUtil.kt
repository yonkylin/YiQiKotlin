package yonky.yiqikotlin.utils

import android.content.Context
import android.widget.Toast
import java.lang.reflect.AccessibleObject.setAccessible
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import java.io.UnsupportedEncodingException
import kotlin.experimental.and
import java.lang.reflect.AccessibleObject.setAccessible




/**
 * Created by Administrator on 2018/7/9.
 */
object MyUtil {

    fun dp2px(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun px2dp(context: Context, px: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    //    获取屏幕宽高
    fun getDisplayWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getDisplayHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    //字符串转16进制
//    fun encode(str: String): String {
//        val hexString = "0123456789ABCDEF"
//        //根据默认编码获取字节数组
//        val bytes = str.toByteArray()
//        val sb = StringBuilder(bytes.size * 2)
//        //将字节数组中每个字节拆解成2位16进制整数
//        for (i in bytes.indices) {
//            sb.append(hexString[bytes[i]and 0xf0 shr 4])
//            sb.append(hexString[bytes[i] and 0x0f shr 0])
//        }
//        return sb.toString()
//    }

    //url编码
    fun getURLEncoderString(str: String?): String {
        var result = ""
        if (null == str) {
            return ""
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return result
    }


    //判断是否有安装qq
    fun isQQAvailable(context: Context): Boolean {

        val mPackageManager = context.packageManager

        val pinfo = mPackageManager.getInstalledPackages(0)

        if (pinfo != null) {
            for (i in pinfo.indices) {
                val pn = (pinfo[i] as PackageInfo).packageName
                if (pn == "com.tencent.mobileqq") {
                    return true
                }
            }
        }
        return false
    }




    fun toast(mContext: Context) {
        Toast.makeText(mContext, "该功能还在开发中！", Toast.LENGTH_SHORT).show()
    }
}

