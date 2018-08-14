package yonky.yiqikotlin.v.fragment

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.my_recyclerview.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseFragment
import yonky.yiqikotlin.base.contract.MainContract
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.AreaEBean
import yonky.yiqikotlin.net.exception.ErrorStatus
import yonky.yiqikotlin.p.MainPresenter
import yonky.yiqikotlin.showToast
import yonky.yiqikotlin.v.adapter.MainAdapter
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_THREE
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_TWO

/**
 * Created by Administrator on 2018/7/9.
 */
class MainFragment : BaseFragment(), MainContract.View{

         val mainAdapter by lazy{        MainAdapter(mContext)    }

    lateinit var mPreferences:SharedPreferences
        val mPresenter by lazy {       MainPresenter(mContext)   }
        var isRefresh= false

       override fun getLayoutId(): Int = R.layout.fragment_main

       override fun lazyLoad() {
          val zdid =mPreferences.getString("regionId","42");

           mPresenter.loadDatas("A",zdid);
           mPresenter.loadDatas("B",zdid);
           mPresenter.loadDatas("C",zdid);
           mPresenter.loadDatas("D",zdid);
           mPresenter.loadDatas("E",zdid);
        }

       override fun initView() {
           mPreferences = mContext.getSharedPreferences("data", 0)
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
           mLayoutStatusView = status_view
           rv_main.layoutManager=layoutManager
           rv_main.adapter =mainAdapter

           swipe_refresh.setOnRefreshListener {
               isRefresh =true
               lazyLoad() }
       }
       override fun showResult(areaBeanList: List<AreaBean>, tag: String) {
            mLayoutStatusView?.showContent()
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

    /**
     * 显示错误信息
     */
       override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }


    /**
     * 显示 Loading （下拉刷新的时候不需要显示 Loading）
     */
       override fun showLoading() {
//           if(!isRefresh)
               mLayoutStatusView?.showLoading()

       }

       override fun dismissLoading() {

       }

    override fun onDestroy() {

        super.onDestroy()
        mPresenter.detachView()
    }
}
