package yonky.yiqikotlin.p

import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.contract.StyleContract
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.bean.GoodFilterBean
import yonky.yiqikotlin.bean.StyleBean
import yonky.yiqikotlin.m.DataManager
import yonky.yiqikotlin.net.exception.ExceptionHandle

/**
 * Created by Administrator on 2018/8/15.
 */
class StylePresenter:BasePresenter<StyleContract.View>(),StyleContract.Presenter{
    val mDataManager by lazy{
        DataManager()
    }

    override fun loadDatas(filter: Filter, isLoadingMore: Boolean) {
        val disposable = mDataManager.getStyleData(filter.shop_id,filter.size,filter.seller_cid,filter.pindex,
                filter.from,filter.price2,filter.dtype,filter.zdid,filter.price1,filter.psize,filter.orderby,
                filter.color,filter.spm,filter.keyword,filter.mid,filter.fid)
                .filter { styleBean: StyleBean ->
                    styleBean.status_code==200
                }
                .map { styleBean: StyleBean -> styleBean.goods_items_list_get_response!!.items }
                .subscribe({
                    goodBeanList->
                    mRootView?.showResult(goodBeanList!!,isLoadingMore)
                },{
                    throwable: Throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                }
        )
    }

    override fun getGoodColor(type: String) {
    }
}