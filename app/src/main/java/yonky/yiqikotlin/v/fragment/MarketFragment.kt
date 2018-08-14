package yonky.yiqikotlin.v.fragment

import yonky.yiqikotlin.bean.ShopBean

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import yonky.yiqikotlin.bean.ShopFilterBean
import android.content.SharedPreferences
import kotlinx.android.synthetic.main.fragment_market.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.R.id.swipe_refresh
import yonky.yiqikotlin.base.contract.MarketContract
import yonky.yiqikotlin.base.BaseFragment
import yonky.yiqikotlin.p.MarketPresenter
import yonky.yiqikotlin.v.adapter.MarketAdapter


/**
 * Created by Administrator on 2018/8/14.
 */
class MarketFragment : BaseFragment(), MarketContract.View {

//    var fab: FloatingActionButton? = null
//
    val mMarketAdapter by lazy{ MarketAdapter(mContext)}
    var shopBean: List<ShopBean>?=null
    var shopFilter= ShopFilterBean()
//
//
     val mPresenter by lazy{ MarketPresenter(mContext) }
     var isLoadingMore: Boolean = false

     lateinit var mPreferences: SharedPreferences
//     var regionSelected: String? = null
     lateinit var zdid: String
//     var sort: String
//    private var i: Int = 0
//    private var j: Int = 0

    override fun getLayoutId(): Int = R.layout.fragment_market


//    //    设置点击事件
//    @OnClick(R.id.bt_default)
//    internal fun setBtDefault() {
//        setColor(btDefault, btDk, btNew, i)
//        sort = "mr"
//        shopFilter.orderby = sort
//        mPresenter!!.loadData(shopFilter, false)
//    }
//
//    @OnClick(R.id.bt_new)
//    internal fun setBtNew() {
//        setColor(btNew, btDefault, btDk, j)
//        if (j % 2 == 0) {
//            sort = "newOn_asc"
//        } else {
//            sort = "newOn_desc"
//        }
//        j++
//        i = 0
//        shopFilter.orderby = sort
//        mPresenter!!.loadData(shopFilter, false)
//    }
//
//    @OnClick(R.id.bt_dk)
//    internal fun setBtDk() {
//        setColor(btDk, btDefault, btNew, i)
//        if (i % 2 == 0) {
//            sort = "dkOn_asc"
//        } else {
//            sort = "dkOn_desc"
//        }
//        i++
//        j = 0
//        shopFilter.orderby = sort
//        mPresenter!!.loadData(shopFilter, false)
//    }
//
//    @OnClick(R.id.fab)
//    internal fun setFab() {
//        recyclerView!!.smoothScrollToPosition(0)
//    }
//
//    private fun setColor(bt: Button?, bt1: Button?, bt2: Button?, count: Int) {
//
//        bt!!.setTextColor(resources.getColor(R.color.colorPrimary))
//        bt1!!.setTextColor(resources.getColor(R.color.gray))
//        bt2!!.setTextColor(resources.getColor(R.color.gray))
//        bt1!!.setBackgroundResource(R.color.light_background)
//        bt2!!.setBackgroundResource(R.color.light_background)
//        if (bt!!.getId() !== R.id.bt_default) {
//            if (count % 2 == 0) {
//                bt!!.setBackgroundResource(R.drawable.background_sort_up)
//            } else {
//                bt!!.setBackgroundResource(R.drawable.background_sort_down)
//            }
//        }
//
//
//    }


    override fun initView() {
        mPreferences = mContext.getSharedPreferences("data", 0)

        //        regionSelected=mPreferences.getString("region","广州");
        //        zdid =mPreferences.getString("regionId","42");
        //        btRegion.setText(regionSelected);

        fab.hide()
        mPresenter.attachView(this)

        shopBean = ArrayList()
        shopFilter = ShopFilterBean()

        rv_market.layoutManager = LinearLayoutManager(mContext)
        rv_market.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))
        rv_market.adapter = mMarketAdapter

        swipe_refresh.setOnRefreshListener {
            shopFilter.pindex = "1"
            mPresenter.loadData(shopFilter, false)
        }

        rv_market.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItem = (recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val totalItemCount = recyclerView.layoutManager.itemCount
                //               第3个item出现时，显示 回到顶部按钮
                if (firstVisibleItem <= 2 || dy > 0) {
                    fab!!.hide()
                } else {
                    fab!!.show()
                }

                //                最后一个出现，并且向下滑动时，加载更多
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {

                    if (!isLoadingMore) {
                        isLoadingMore = true
                        val page = Integer.valueOf(shopFilter.pindex)!! + 1
                        shopFilter.pindex = page.toString()
                        mPresenter.loadData(shopFilter, true)
                    }
                }

            }
        }
        )    }


    override fun lazyLoad() {
        zdid = mPreferences.getString("regionId", "42")
        shopFilter.zdid = zdid
        mPresenter.loadData(shopFilter, false)
    }



    override fun showResult(listBeans: List<ShopBean>, loadingMore: Boolean) {
        swipe_refresh.isRefreshing = false
        if (loadingMore) {

            isLoadingMore = false
            mMarketAdapter.beanList.addAll(listBeans)
        } else {
            mMarketAdapter.beanList=listBeans as ArrayList<ShopBean>
        }

        mMarketAdapter.notifyDataSetChanged()
    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onDestroyView() {
        mPresenter.detachView()
        super.onDestroyView()
    }
}
