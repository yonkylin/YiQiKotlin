package yonky.yiqikotlin.v.fragment

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_main.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseFragment
import yonky.yiqikotlin.base.contract.MainContract
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.AreaEBean
import yonky.yiqikotlin.p.MainPresenter
import yonky.yiqikotlin.v.adapter.MainAdapter
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_THREE
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_TWO

/**
 * Created by Administrator on 2018/7/9.
 */
class MainFragment : BaseFragment(), MainContract.View{

         val mainAdapter by lazy{        MainAdapter(mContext)    }

        val mPresenter by lazy {       MainPresenter(mContext)   }

       override fun getLayoutId(): Int = R.layout.fragment_main

       override fun lazyLoad() {
//           zdid =preferences.getString("regionId","42");

           val zdid ="42"
           mPresenter.loadDatas("A",zdid);
           mPresenter.loadDatas("B",zdid);
           mPresenter.loadDatas("C",zdid);
           mPresenter.loadDatas("D",zdid);
           mPresenter.loadDatas("E",zdid);
        }

       override fun initView() {
           mPresenter.attachView(this)
           val layoutManager = GridLayoutManager(mContext, 6, LinearLayoutManager.VERTICAL, false)
           layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
               override fun getSpanSize(position: Int): Int {
                   when (mainAdapter.getItemViewType(position)) {
                       TYPE_TWO -> return 3
                       TYPE_THREE -> return 2

                       else -> if (position > 15 && position != 20 && position != 25 && position != 30 && position != 35) {
                           return 3
                       } else return 6
                   }
               }
           }
           rv_main.layoutManager=layoutManager
           rv_main.adapter =mainAdapter

           swipe_refresh.setOnRefreshListener { lazyLoad() }
       }
       override fun showResult(areaBeanList: List<AreaBean>, tag: String) {
           Logger.d(areaBeanList)
           when(tag){
               "A"->mainAdapter.bannerList=areaBeanList
               "B1"->mainAdapter.b1List=areaBeanList
               "B2"->mainAdapter.b2List=areaBeanList
               "C1"->mainAdapter.c1List=areaBeanList
               "C2"->mainAdapter.c2List=areaBeanList
               "D"->mainAdapter.dList=areaBeanList
           }
           mainAdapter.notifyDataSetChanged()
           swipe_refresh.setRefreshing(false)
       }

       override fun showE(eList: List<AreaEBean>) {
           mainAdapter.eList=eList
       }

       override fun showError(msg: String, errorCode: Int) {
           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       }

       override fun showLoading() {
           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       }

       override fun dismissLoading() {
           TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       }
   }
