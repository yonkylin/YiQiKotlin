package yonky.yiqikotlin.base.contract

import yonky.yiqikotlin.bean.ShopFilterBean
import yonky.yiqikotlin.bean.ShopBean
import yonky.yiqikotlin.base.IBaseView
import yonky.yiqikotlin.base.IPresenter


/**
 * Created by Administrator on 2018/8/14.
 */

interface MarketContract {
    interface View : IBaseView {
        fun showResult(listBeans: List<ShopBean>, loadingMore: Boolean)
        fun showError(msg:String,errorCode:Int)
    }

    interface Presenter : IPresenter<View> {
        fun loadData(filterBean: ShopFilterBean, loadingMore: Boolean)
    }
}
