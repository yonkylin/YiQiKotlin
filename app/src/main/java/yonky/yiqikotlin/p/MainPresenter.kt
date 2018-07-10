package yonky.yiqikotlin.p

import android.content.Context
import com.orhanobut.logger.Logger
import io.reactivex.functions.Function
import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.contract.MainContract
import yonky.yiqikotlin.bean.MainPageBean
import yonky.yiqikotlin.m.DataManager
import yonky.yiqikotlin.net.exception.ExceptionHandle
import yonky.yiqikotlin.v.fragment.MainFragment

/**
 * Created by Administrator on 2018/7/9.
 */
class MainPresenter(context:Context):BasePresenter<MainContract.View>(),MainContract.Presenter{
   val mDataManager by lazy {  DataManager()}

//   请求数据
override fun loadDatas(tag: String, zdid: String) {
   checkViewAttached()
//   mRootView?.showLoading()
   val disposable = mDataManager.getMainPage("android",zdid,tag,"false")
           .map({
              mainPageBean ->mainPageBean.popularize_items_list_get_response

           })
           .subscribe({ popularItem->
              Logger.d(popularItem)
              Logger.d(popularItem?.areaA)
              if(popularItem?.areaA!=null){

                 mRootView?.showResult(popularItem.areaA!!,"A")

              }else if(popularItem?.areaB1!=null){
                 mRootView?.showResult(popularItem.areaB1!!,"B1")
                 mRootView?.showResult(popularItem.areaB2!!,"B2")

              }else if(popularItem?.areaC1!=null){
                 mRootView?.showResult(popularItem.areaC1!!,"C1")
                 mRootView?.showResult(popularItem.areaC2!!,"C2")

              }else if(popularItem?.areaD!=null){
                 mRootView?.showResult(popularItem.areaD!!,"D")

              }else if(popularItem?.areaE!=null){
                 mRootView?.showE(popularItem.areaE!!)
              }

           }, { throwable ->
              mRootView?.apply {
                 //处理异常
                 showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
              }
           })
   addSubscription(disposable)
}


}