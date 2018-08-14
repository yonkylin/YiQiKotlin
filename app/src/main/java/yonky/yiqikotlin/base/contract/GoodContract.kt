package yonky.yiqikotlin.base.contract

import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.IBaseView
import yonky.yiqikotlin.base.IPresenter
import yonky.yiqikotlin.bean.GoodBean
import yonky.yiqikotlin.bean.GoodFilterBean
import yonky.yiqikotlin.bean.ShopBean
import yonky.yiqikotlin.bean.ShopFilterBean

/**
 * Created by Administrator on 2018/7/16.
 */
interface GoodContract{
    interface View:IBaseView{
        fun showResult(list:List<GoodBean>, isLoadingMore:Boolean)
        fun showShop(shopBean: ShopBean)
        fun showEmpty()
        fun showError(msg:String,errorCode:Int)
    }
    interface Presenter: IPresenter<View> {
        fun loadGoods(filter: GoodFilterBean, isLoadingMore:Boolean)
        fun loadShop(filter: ShopFilterBean)
    }
}