package yonky.yiqikotlin.v.fragment

import android.content.SharedPreferences
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_style.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseFragment
import yonky.yiqikotlin.base.contract.StyleContract
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.bean.GoodAttributeBean
import yonky.yiqikotlin.bean.GoodBean
import yonky.yiqikotlin.bean.Item
import yonky.yiqikotlin.p.StylePresenter
import yonky.yiqikotlin.v.adapter.StyleAdapter
import yonky.yiqikotlin.R.id.fab
import com.sun.javaws.ui.SplashScreen.hide
import android.support.v7.widget.RecyclerView
import android.support.v4.widget.SwipeRefreshLayout



/**
 * Created by Administrator on 2018/8/15.
 */
class StyleFragment: BaseFragment(),StyleContract.View{

   lateinit var filter: Filter
    val mPreferences by lazy{mContext.getSharedPreferences("data",0)}
    val mAdapter by lazy{ StyleAdapter(mContext)}
    val mPresenter by lazy{ StylePresenter()}

    override fun getLayoutId(): Int = R.layout.fragment_style

    override fun lazyLoad() {
        zdid=preferences.getString("regionId","42");
        filterBean.setZdid(zdid);
        mPresenter.loadDatas(filterBean,false);
        mPresenter.getGoodColor("get_colors");
        mPresenter.getGoodColor("get_sizes");
    }

    override fun initView() {
        fab.hide()
        mPresenter.attachView(this)
        rv_style.layoutManager =GridLayoutManager(mContext,2)
        rv_style.adapter =mAdapter

//        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            filterBean.setPindex("1")
//            mPresenter.loadDatas(filterBean, false)
//        })
//
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val firstItemPosition = (recyclerView!!.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
//                val lastItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
//                val totalCount = recyclerView.layoutManager.itemCount
//                if (firstItemPosition < 2 || dy > 0) {
//                    fab.hide()
//                } else {
//                    fab.show()
//                }
//
//                //                最后一个显示时，加载更多
//                if (lastItemPosition >= totalCount - 1 && dy > 0) {
//                    if (!isLoadingMore) {
//                        isLoadingMore = true
//                        val page = Integer.valueOf(filterBean.getPindex()) + 1
//                        filterBean.setPindex(page.toString())
//                        mPresenter.loadDatas(filterBean, true)
//                    }
//                }
//            }
//        })

    }

    override fun showResult(beanList: List<GoodBean>, isLoadingMore: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showGoodAttr(bean: GoodAttributeBean.GoodsItemGetResponseBean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRegion(regionList: List<Item>, type: Int) {
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