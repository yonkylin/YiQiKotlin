package yonky.yiqikotlin.base.contract

import yonky.yiqikotlin.base.IBaseView
import yonky.yiqikotlin.base.IPresenter
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.AreaEBean

/**
 * Created by Administrator on 2018/7/9.
 */
interface MainContract{
    interface View: IBaseView {
        fun showResult(areaABeanList: List<AreaBean>, tag: String)
        fun showE(eList: List<AreaEBean>)
        fun showError(msg:String,errorCode:Int)
    }
    interface Presenter:IPresenter<View>{
//        fun checkPermission()
        fun loadDatas(tag: String, zdid: String)
    }
}