package yonky.yiqikotlin.v.main

import android.os.Bundle
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity

/**
 * Created by Administrator on 2018/7/6.
 */
class MainActivity:BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getLayout(): Int = R.layout.test

    override fun initData() {
    }

    override fun initView() {
    }

    override fun start() {
    }
}