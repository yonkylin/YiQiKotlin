package yonky.yiqikotlin.v

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.v.fragment.TestFragment

/**
 * Created by Administrator on 2018/7/6.
 */
class MainActivity:BaseActivity(){

    var mHomeFragment: TestFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
//        初始化底部导航栏
        bottomNavigationBar
                .setActiveColor(R.color.colorPrimary)
                .setInActiveColor(R.color.gray)
                .addItem(BottomNavigationItem(R.drawable.tab_homered,"首页").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.tab_homegray)))
                .addItem(BottomNavigationItem(R.drawable.tab_marketred,"逛市场").setInactiveIconResource(R.drawable.tab_marketgray))
                 .addItem(BottomNavigationItem(R.drawable.tab_stylered,"搜款式").setInactiveIconResource(R.drawable.tab_stylegray))
                .addItem(BottomNavigationItem(R.drawable.tab_listred,"采购单").setInactiveIconResource(R.drawable.tab_listgray))
                .addItem(BottomNavigationItem(R.drawable.tab_mered,"我的").setInactiveIconResource(R.drawable.tab_megray))
                .setFirstSelectedPosition(0)
                .initialise()

        bottomNavigationBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
            }
        })


    }

    override fun start() {
    }
}