package yonky.yiqikotlin

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import yonky.yiqikotlin.base.App

/**
 * Created by Administrator on 2018/7/6.
 */
fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(App.instance, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity?.applicationContext, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

