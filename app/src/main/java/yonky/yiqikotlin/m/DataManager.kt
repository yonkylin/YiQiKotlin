package yonky.yiqikotlin.m

import io.reactivex.Observable
import yonky.yiqikotlin.bean.MainPageBean
import yonky.yiqikotlin.net.RetrofitManager
import yonky.yiqikotlin.scheduler.SchedulerUtils

/**
 * Created by Administrator on 2018/7/10.
 */
class DataManager{
    fun getMainPage(platform:String, zdid:String , tag:String, isDebug:String): Observable<MainPageBean> {
        return RetrofitManager.service.getMainPageData(platform,zdid,tag,isDebug)
                .compose(SchedulerUtils.ioToMain())
    }
}