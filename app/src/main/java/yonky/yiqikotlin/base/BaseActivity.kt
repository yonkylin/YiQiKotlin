package yonky.yiqikotlin.base

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import yonky.yiqikotlin.utils.CleanLeakUtils

/**
 * Created by Administrator on 2018/7/6.
 */
abstract  class BaseActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(getLayout())
        App.instance?.addActivity(this)
        initData()
        initView()
        start()
    }

    override fun onDestroy() {
        CleanLeakUtils.fixInputMethodManagerLeak(this)
        super.onDestroy()
        App.instance?.removeActivity(this)

    }


    /*
    * 加载布局
    * */
    abstract fun getLayout():Int

    /**
     * 初始化数据
     */
    abstract fun initData()
    /**
     * 初始化 View
     */
    abstract fun initView()
    /**
     * 开始请求
     */
    abstract fun start()



}