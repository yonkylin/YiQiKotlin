package yonky.yiqikotlin.v.fragment

import android.content.SharedPreferences
import android.os.Bundle
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
import android.support.v7.widget.RecyclerView



/**
 * Created by Administrator on 2018/8/15.
 */
class StyleFragment: BaseFragment(),StyleContract.View{

    var filter= Filter()
    val mPreferences by lazy{mContext.getSharedPreferences("data",0)}
    val mAdapter by lazy{ StyleAdapter(mContext)}
    val mPresenter by lazy{ StylePresenter()}

    var isLoadingMore = false


    companion object {
        fun getInstance(mFilter: Filter): StyleFragment {
            val fragment = StyleFragment()
//            val bundle = Bundle()
//            fragment.arguments = bundle
            fragment.filter = mFilter
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_style

    override fun lazyLoad() {
        filter.zdid=mPreferences.getString("regionId","42")
        mPresenter.loadDatas(filter,false);
        mPresenter.getGoodColor("get_colors");
        mPresenter.getGoodColor("get_sizes");
    }

    override fun initView() {
        fab.hide()
        mPresenter.attachView(this)
        rv_style.layoutManager =GridLayoutManager(mContext,2)
        rv_style.adapter =mAdapter

        swipe_refresh.setOnRefreshListener {

            filter.pindex="1"
            mPresenter.loadDatas(filter, false)

        }

        rv_style.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemPosition = (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                val lastItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                val totalCount = recyclerView.layoutManager.itemCount
                if (firstItemPosition < 2 || dy > 0) {
                    fab.hide()
                } else {
                    fab.show()
                }

                //                最后一个显示时，加载更多
                if (lastItemPosition >= totalCount - 1 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true
                        val page = Integer.valueOf(filter.pindex) + 1
                        filter.pindex=page.toString()
                        mPresenter.loadDatas(filter, true)
                    }
                }
            }
        })

        fab.setOnClickListener{rv_style.smoothScrollToPosition(0)}

    }

    override fun showResult(beanList: List<GoodBean>, loadingMore: Boolean) {
        swipe_refresh.setRefreshing(false);
        if(loadingMore){
            isLoadingMore=false;
            mAdapter.beanList.addAll(beanList);
        }else{
            mAdapter.beanList=beanList as ArrayList<GoodBean>;
        }

        mAdapter.notifyDataSetChanged();
    }

    override fun showGoodAttr(bean: GoodAttributeBean.GoodsItemGetResponseBean) {

   }

    override fun showRegion(regionList: List<Item>, type: Int) {
    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}