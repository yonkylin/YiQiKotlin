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
   mRootView?.showLoading()
   val disposable = mDataManager.getMainPage("android",zdid,tag,"false")
           .map({
              mainPageBean ->mainPageBean.popularize_items_list_get_response

           })
           .subscribe({ popularItem->
              Logger.d(popularItem)
              Logger.d(popularItem?.AreaA)
                mRootView?.dismissLoading()
               popularItem?.AreaA?.let{
                   mRootView?.showResult(popularItem.AreaA!!,"A")
               }
               popularItem?.AreaB1?.let{
                   mRootView?.showResult(popularItem.AreaB1!!,"B1")
               }
               popularItem?.AreaB2?.let{
                   mRootView?.showResult(popularItem.AreaB2!!,"B2")
               }
               popularItem?.AreaC1?.let{
                   mRootView?.showResult(popularItem.AreaC1!!,"C1")
               }
               popularItem?.AreaC2?.let{
                   mRootView?.showResult(popularItem.AreaC2!!,"C2")
               }
               popularItem?.AreaD?.let{
                   mRootView?.showResult(popularItem.AreaD!!,"D")
               }
               popularItem?.AreaE?.let{
                   mRootView?.showE(popularItem.AreaE!!)
               }


           }, { throwable ->
              mRootView?.apply {
                  dismissLoading()
                 //处理异常
                 showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
              }
           })
   addSubscription(disposable)
}


}