package yonky.yiqikotlin.p

import android.content.Context
import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.contract.GoodDetailContract
import yonky.yiqikotlin.bean.*
import yonky.yiqikotlin.m.DataManager
import yonky.yiqikotlin.net.exception.ExceptionHandle

/**
 * Created by Administrator on 2018/8/15.
 */
class GoodDetailPresenter(mContext:Context):BasePresenter<GoodDetailContract.View>(),GoodDetailContract.Presenter{
    val mDataManager by lazy{
        DataManager()
    }
    override fun loadGoodDetail(filter: Filter) {
        val disposable = mDataManager.getGoodDetail("get_item",filter.goods_id!!,filter.from,filter.user_id,filter.zdid!!,filter.spm!!)
                .filter(){goodDetailBean->
                    goodDetailBean.status_code==200
                }
                .map {goodDetailBean ->
                    goodDetailBean.goods_item_get_response!!.item
                }
                .subscribe({ goodBean ->
                    mRootView?.showResult(goodBean!!)
                },{ throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun loadImgs(filter: Filter) {
        val disposable = mDataManager.getGoodDetail("get_item_imgs",filter.goods_id!!,filter.from,filter.user_id,filter.zdid!!,filter.spm!!)
                .filter(){goodDetailBean->
                    goodDetailBean.status_code==200
                }
                .map {goodDetailBean ->
                    goodDetailBean.goods_item_get_response!!.imgs
                }
                .subscribe({ imgList ->
                    mRootView?.showImgs(imgList!!)
                },{ throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getShopDetail(filter: Filter) {
        val disposable = mDataManager.getShopData(filter.shop_id!!,filter.from,filter.user_id,filter.zdid!!,filter.spm!!)
                .filter{shopPage ->
                    shopPage.status_code==200
                }
                .map { t: ShopPage ->
                    t.shop_item_get_response!!.item
                }
                .subscribe({t: ShopBean? ->
                    mRootView?.showShop(t!!)
                },{throwable: Throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }
}