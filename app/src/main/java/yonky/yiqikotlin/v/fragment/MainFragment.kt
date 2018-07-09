package yonky.yiqikotlin.v.fragment

import android.content.Context
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseFragment
import yonky.yiqikotlin.base.contract.MainContract
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.AreaEBean
import yonky.yiqikotlin.v.adapter.MainAdapter

/**
 * Created by Administrator on 2018/7/9.
 */
class MainFragment : BaseFragment(), MainContract.View{

    val mainAdapter by lazy{
        MainAdapter(mContext)
    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun lazyLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResult(areaABeanList: List<AreaBean>, tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showE(eList: List<AreaEBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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