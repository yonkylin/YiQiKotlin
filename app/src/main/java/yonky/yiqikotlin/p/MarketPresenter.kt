package yonky.yiqikotlin.p

import android.content.Context
import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.contract.MarketContract
import yonky.yiqikotlin.bean.ShopFilterBean
import yonky.yiqikotlin.m.DataManager
import yonky.yiqikotlin.net.exception.ExceptionHandle

/**
 * Created by Administrator on 2018/8/14.
 */
class MarketPresenter(mContext:Context) :BasePresenter<MarketContract.View>(),MarketContract.Presenter{
    val mDataManager by lazy{
        DataManager()
    }

    override fun loadData(filter: ShopFilterBean, loadingMore: Boolean) {
        val disposable = mDataManager.getSearchData(filter.psize,filter.orderby,filter.keyword,
                filter.bq,filter.service,filter.pindex,filter.from,filter.zdid)
                .map { marketBean ->
                    marketBean.shop_items_list_get_response?.items
                }
                .subscribe({shopBean ->
                    mRootView?.showResult(shopBean!!,loadingMore)
                },{ throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

}