package yonky.yiqikotlin.base.contract

import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.IBaseView
import yonky.yiqikotlin.base.IPresenter
import yonky.yiqikotlin.bean.*
import yonky.yiqikotlin.bean.GoodDetailBean.GoodsItemGetResponseBean


/**
 * Created by Administrator on 2018/8/15.
 */
interface StyleContract {
    interface View : IBaseView {
        fun showResult(beanList: List<GoodBean>, isLoadingMore: Boolean)
        fun showGoodAttr(bean: GoodAttributeBean.GoodsItemGetResponseBean)
        fun showRegion(regionList: List<Item>, type: Int)
        fun showError(msg:String,errorCode:Int)
    }

    interface Presenter : IPresenter<View> {
        fun loadDatas(filter: Filter, isLoadingMore: Boolean)
        fun getGoodColor(type: String)
    }

}