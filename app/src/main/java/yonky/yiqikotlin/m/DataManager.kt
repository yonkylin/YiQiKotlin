package yonky.yiqikotlin.m

import io.reactivex.Observable
import yonky.yiqikotlin.bean.*
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

    fun getStyleData(shop_id: String?,
                     size: String?,
                     seller_cid: String?,
                     pindex: String,
                     from: String,
                     price2: String,
                     dtype: String,
                     zdid: String?,
                     price1: String,
                     psize: String,
                     orderby: String,
                     color: String?,
                     spm: String?,
                     keyword: String?,
                     mid: String?,
                     fid: String?): Observable<StyleBean> {
        return RetrofitManager.service.getStyleData("search", shop_id, size, seller_cid, pindex, from, price2, dtype, zdid,
                price1, psize, orderby, color, spm, keyword, mid, fid, "")
                .compose(SchedulerUtils.ioToMain())
    }

    //获取店铺详情
    //    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D
    fun getShopData(shop_id: String, from: String, user_id: String, zdid: String, spm: String): Observable<ShopPage> {
        return RetrofitManager.service.getShop(shop_id, from, user_id, zdid, spm)
                .compose(SchedulerUtils.ioToMain())
    }

    //    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48  逛市场

    fun getSearchData(psize: String, orderby: String, keyword: String?, bq: String?, service: String?, pindex: String, from: String, zdid: String): Observable<MarketBean> {
        return  RetrofitManager.service.getSearchData(psize, orderby, keyword, bq, service, pindex, from, zdid)
                .compose(SchedulerUtils.ioToMain())
    }


//    http://api2.17zwd.com/rest/goods/get_item?goods_id=106373882&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

    fun getGoodDetail(type: String, goods_id: String, from: String, user_id: String, zdid: String, spm: String): Observable<GoodDetailBean> {
        return RetrofitManager.service.getGoodDetail(type, goods_id, from, user_id, zdid, spm)
                .compose(SchedulerUtils.ioToMain())
    }

}