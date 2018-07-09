package yonky.yiqikotlin.v

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager;
import yonky.yiqikotlin.base.BaseFragment



/**
 * Created by Administrator on 2018/7/9.
 */

class ViewPagerAdaper(fm: FragmentManager, private val fragments: List<BaseFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}