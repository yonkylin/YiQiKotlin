package yonky.yiqikotlin.base.contract

import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.IBaseView
import yonky.yiqikotlin.base.IPresenter
import yonky.yiqikotlin.bean.*


/**
 * Created by Administrator on 2018/8/15.
 */
interface GoodDetailContract {
    interface View : IBaseView {
        fun showResult(goodBean: GoodBean)
        fun showImgs(imgList: List<String>)
        fun showShop(shopBean: ShopBean)
        fun showError(msg:String,errorCode:Int)
    }

    interface Presenter : IPresenter<View> {
        fun loadGoodDetail(filter: Filter)
        fun loadImgs(filter: Filter)
        fun getShopDetail(filter: Filter)
//        fun loadGoodDetail(filter: GoodFilterBean)
//        fun loadImgs(filter: GoodFilterBean)
//        fun getShopDetail(filter: ShopFilterBean)
    }
}
