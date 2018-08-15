package yonky.yiqikotlin.p

import android.content.Context
import com.orhanobut.logger.Logger
import io.reactivex.schedulers.Schedulers
import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.contract.GoodContract
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.bean.GoodFilterBean
import yonky.yiqikotlin.bean.ShopBean
import yonky.yiqikotlin.bean.ShopFilterBean
import yonky.yiqikotlin.m.DataManager
import yonky.yiqikotlin.net.exception.ExceptionHandle

/**
 * Created by Administrator on 2018/8/2.
 */
class GoodPresenter(context: Context):BasePresenter<GoodContract.View>(),GoodContract.Presenter{
    val mDataManager by lazy{
        DataManager()
    }
    override fun loadGoods(filter: Filter, isLoadingMore: Boolean) {
        val disposable = mDataManager.getStyleData(filter.shop_id,filter.size,filter.seller_cid,filter.pindex,filter.from,filter.price2,
                filter.dtype,filter.zdid, filter.price1,filter.psize,filter.orderby,filter.color ,filter.spm,filter.keyword,filter.mid,filter.fid)
                .map { styleBean -> styleBean.goods_items_list_get_response?.items }
                .subscribe({goodBeans->
                    if(goodBeans!=null)
                    mRootView?.showResult(goodBeans,isLoadingMore);
                },{ throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun loadShop(filter: Filter) {
        val disposable=mDataManager.getShopData(filter.shop_id!!,filter.from,filter.user_id,filter.zdid!!,filter.spm!!)
                .map { shopPage -> shopPage.shop_item_get_response?.item }
                .subscribe({shopBean ->
                    if(shopBean!=null)
                    mRootView?.showShop(shopBean)
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