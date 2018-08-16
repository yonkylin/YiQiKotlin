package yonky.yiqikotlin.v

import kotlinx.android.synthetic.main.activity_fragment.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.base.BaseFragment
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.v.fragment.StyleFragment
import android.R.attr.fragment
import yonky.yiqikotlin.bean.ShopBean
import yonky.yiqikotlin.utils.StatusBarUtil


/**
 * Created by Administrator on 2018/8/16.
 */
class ActivityFragment:BaseActivity(){
    var filter=Filter()
    var shopBean: ShopBean?=null
    var fragment:BaseFragment?=null
    override fun getLayoutId(): Int = R.layout.activity_fragment

    override fun initData() {
       filter=intent.getSerializableExtra("filter")as Filter
//        shopBean=i
      if(filter.kind.isNullOrBlank()){



      }else {
          tv_title.text=filter.kind
          fragment = StyleFragment.getInstance(filter)
      }



    }

    override fun initView() {
        StatusBarUtil.Companion.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)
    }

    override fun start() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }
}