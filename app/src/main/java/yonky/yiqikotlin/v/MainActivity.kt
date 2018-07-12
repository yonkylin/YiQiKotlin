package yonky.yiqikotlin.v

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.v.fragment.TestFragment
import yonky.yiqikotlin.base.BaseFragment
import android.widget.TextView
import kotlinx.android.synthetic.main.tab_item.*
import kotlinx.android.synthetic.main.tab_item.view.*
import kotlinx.android.synthetic.main.topbar.*
import yonky.yiqikotlin.utils.StatusBarUtil
import yonky.yiqikotlin.v.fragment.MainFragment


/**
 * Created by Administrator on 2018/7/6.
 */
class MainActivity:BaseActivity(){


    val fragments=ArrayList<BaseFragment>()

//    val preference : SharedPreferences=getSharedPreferences("region",0)

    val titles = arrayOf("首页","逛市场","搜款式","采购单","我的")
    val drawables =arrayOf(R.drawable.tab_home_selector,R.drawable.tab_market_selector,R.drawable.tab_style_selector,
            R.drawable.tab_list_selector,R.drawable.tab_me_selector)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {

       fragments.add(MainFragment())
       fragments.add(TestFragment())
       fragments.add(TestFragment())
       fragments.add(TestFragment())
       fragments.add(TestFragment())


    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)
        viewpager.offscreenPageLimit=4;


        setTabs(tab_layout,layoutInflater,drawables,titles)

        val pagerAdaper =ViewPagerAdaper(supportFragmentManager,fragments)
        viewpager.adapter = pagerAdaper
        tab_layout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))


//        初始化底部导航栏
//        bottomNavigationBar
//                .setActiveColor(R.color.colorPrimary)
//                .setInActiveColor(R.color.gray)
//                .addItem(BottomNavigationItem(R.drawable.tab_homered,"首页").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.tab_homegray)))
//                .addItem(BottomNavigationItem(R.drawable.tab_marketred,"逛市场").setInactiveIconResource(R.drawable.tab_marketgray))
//                 .addItem(BottomNavigationItem(R.drawable.tab_stylered,"搜款式").setInactiveIconResource(R.drawable.tab_stylegray))
//                .addItem(BottomNavigationItem(R.drawable.tab_listred,"采购单").setInactiveIconResource(R.drawable.tab_listgray))
//                .addItem(BottomNavigationItem(R.drawable.tab_mered,"我的").setInactiveIconResource(R.drawable.tab_megray))
//                .setFirstSelectedPosition(0)
//                .initialise()
//
//        bottomNavigationBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
//            override fun onTabReselected(position: Int) {
//
//            }
//
//            override fun onTabUnselected(position: Int) {
//            }
//
//            override fun onTabSelected(position: Int) {
//
//            }
//        })





    }

    override fun start() {
    }


    //设置底部Tab文字及图标
    private fun setTabs(tabLayout: TabLayout, inflater: LayoutInflater, icons: Array<Int>, titles: Array<String>) {
        for (i in titles.indices) {
            val view = inflater.inflate(R.layout.tab_item, null)
            val tab = tabLayout.newTab()
            tab.customView =view

           view.tv_tab.text =titles[i]

           view.iv_tab.setImageResource(icons[i])
            tabLayout.addTab(tab)

        }
    }


}