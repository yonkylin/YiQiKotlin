package yonky.yiqikotlin.v

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupWindow
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.base.BaseFragment
import android.widget.TextView
import com.google.android.flexbox.FlexDirection.ROW
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayoutManager
import com.squareup.haha.perflib.Main
import kotlinx.android.synthetic.main.tab_item.*
import kotlinx.android.synthetic.main.tab_item.view.*
import kotlinx.android.synthetic.main.topbar.*
import kotlinx.android.synthetic.main.window_region.*
import kotlinx.android.synthetic.main.window_region.view.*
import yonky.yiqikotlin.MyListener
import yonky.yiqikotlin.showToast
import yonky.yiqikotlin.utils.MyUtil
import yonky.yiqikotlin.utils.StatusBarUtil
import yonky.yiqikotlin.v.adapter.RegionAdapter
import yonky.yiqikotlin.v.fragment.*


/**
 * Created by Administrator on 2018/7/6.
 */
class MainActivity:BaseActivity(),MyListener{


    val fragments=ArrayList<BaseFragment>()
    val mainFragment:BaseFragment=MainFragment()
    val marketFragment:BaseFragment=MarketFragment()
    val styleFragment:BaseFragment=StyleFragment()
    val loginFragment:BaseFragment=LoginFragment()
    val myFragment:BaseFragment=MyFragment()



    var mPopupWindow:PopupWindow?=null
    lateinit var preference : SharedPreferences
    lateinit var regionSelected: String
    val titles = arrayOf("首页","逛市场","搜款式","采购单","我的")
    val drawables =arrayOf(R.drawable.tab_home_selector,R.drawable.tab_market_selector,R.drawable.tab_style_selector,
            R.drawable.tab_list_selector,R.drawable.tab_me_selector)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {
        preference=getSharedPreferences("data",0)
        regionSelected=preference.getString("region","广州")

       fragments.add(mainFragment)
       fragments.add(marketFragment)
       fragments.add(styleFragment)
       fragments.add(loginFragment)
       fragments.add(myFragment)


    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)
        viewpager.offscreenPageLimit=4;

        bt_region.setText(regionSelected);
        setTabs(tab_layout,layoutInflater,drawables,titles)

        val pagerAdaper =ViewPagerAdaper(supportFragmentManager,fragments)
        viewpager.adapter = pagerAdaper
        tab_layout.addOnTabSelectedListener(object:TabLayout.ViewPagerOnTabSelectedListener(viewpager){
            override fun onTabSelected(tab: TabLayout.Tab?) {
                super.onTabSelected(tab)
                val mCurrentPosition=tab_layout.selectedTabPosition
                setToolBar(mCurrentPosition)
            }
        })

        bt_region.setOnClickListener(){
            showRegion()
        }

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

    private fun showRegion(){
        if(mPopupWindow==null){
            val contentView=LayoutInflater.from(this).inflate(R.layout.window_region,null)
            mPopupWindow =object:PopupWindow(contentView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT,true){
                override fun dismiss() {
                    super.dismiss()
                updateButton()
                }
            }
            val regionManager = FlexboxLayoutManager()
            regionManager.flexDirection = ROW
            regionManager.flexWrap = WRAP

            contentView.rv_region1.layoutManager = regionManager
            val adapter=RegionAdapter(this,regionSelected)
            contentView.rv_region1.adapter = adapter
            adapter.listener=this
            contentView.halfTransparentView.setOnClickListener(){
                mPopupWindow?.dismiss()
            }


        }
        mPopupWindow!!.showAsDropDown(bt_region,0,-MyUtil.dp2px(this,5))



    }


    private fun updateButton(){
        val s =preference.getString("region","广州")
        if(s!=regionSelected){
            regionSelected =s
            bt_region.setText(s)
            for(i in fragments.indices){
                fragments[i].hasLoadData =false
                fragments[i].lazyLoadDataIfPrepared()
            }
        }
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

    private fun setToolBar(position: Int) {
        if (position >= 3) {
            toolbar.setVisibility(View.GONE)
        } else {
            toolbar.setVisibility(View.VISIBLE)
        }

    }

    override fun onClick() {
        mPopupWindow!!.dismiss()
        updateButton()
    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}