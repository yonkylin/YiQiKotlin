package yonky.yiqikotlin.net

import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query
import yonky.yiqikotlin.bean.MainPageBean


/**
 * Created by Administrator on 2018/7/10.
 */
interface ApiService {
    companion object {
    //  http://api2.17zwd.com/rest/popularize/get_home_data?from=android&zdid=48&tag=A&debug=false  首页
    //    http://stu.17zwd.com/api/searchByUrl?pic_url=http://img.alicdn.com/bao/uploaded/i2/1656591777/TB2jD5bXmyEJuJjSspiXXX4IFXa_!!1656591777.jpg&shadow$_klass_=class+com.hanyun.onlineproject.entity.GetSearchImgBean&zdid=42
    val HOST = "https://api2.17zwd.com/"
    val SEARCH = "https://stu.17zwd.com/api/"
}

    @GET("rest/popularize/get_home_data")
    fun getMainPageData(@Query("from") platform: String,
                        @Query("zdid") zdid: String,
                        @Query("tag") tag: String,
                        @Query("debug") isDebug: String): Observable<MainPageBean>

    //    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48
    //逛市场

    /*@GET("rest/shop/search_shops/")
    fun getSearchData(@Query("psize") psize: String,
                      @Query("orderby") orderby: String,
                      @Query("keyword") keyword: String,
                      @Query("bq") bq: String,
                      @Query("service") service: String,
                      @Query("pindex") pindex: String,
                      @Query("from") from: String,
                      @Query("zdid") zdid: String): Observable<MarketBean>*/

    // 搜款式
    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48


/*    @GET("/rest/goods/{search}")
    fun getStyleData(@Path("search") type: String,
                     @Query("shop_id") shop_id: String,
                     @Query("size") size: String,
                     @Query("seller_cid") seller_cid: String,
                     @Query("pindex") pindex: String,
                     @Query("from") from: String,
                     @Query("price2") price2: String,
                     @Query("dtype") dtype: String,
                     @Query("zdid") zdid: String,
                     @Query("price1") price1: String,
                     @Query("psize") psize: String,
                     @Query("orderby") orderby: String,
                     @Query("color") color: String,
                     @Query("spm") spm: String,
                     @Query("keyword") keyword: String,
                     @Query("mid") mid: String,
                     @Query("fid") fid: String,
                     @Query("shadow$_klass_") shadow: String): Observable<StyleBean>*/


    // http://api2.17zwd.com/rest/goods/search?shop_id=26974&size=&seller_cid=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48&price1=0.0&psize=10&orderby=mr&color=&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D&keyword=

    /*    @GET("/rest/goods/search")
        Observable<StyleBean> getGoods(@Query("shop_id")String shop_id,
                                       @Query("size")String size,
                                       @Query("seller_cid") String seller_cid,
                                       @Query("pindex") String pindex,
                                       @Query("from") String from,
                                       @Query("price2") String price2,
                                       @Query("dtype") String dtype,
                                       @Query("zdid") String zdid,
                                       @Query("price1") String price1,
                                           @Query("psize") String psize,
                                           @Query("orderby") String orderby,
                                           @Query("color")  String color ,
                                           @Query("spm")String spm,
                                           @Query("keyword") String keyword,
                                       @Query("shadow$_klass_")String shadow
        );*/
    //获取店铺详情
    //    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D
   /* @GET("/rest/shop/get_shop")
    fun getShop(@Query("shop_id") shop_id: String,
                @Query("from") from: String,
                @Query("user_id") user_id: String,
                @Query("zdid") zdid: String,
                @Query("spm") spm: String
    ): Observable<ShopPage>
*/
    //    http://api2.17zwd.com/rest/goods/get_item?goods_id=106373882&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

   /* @GET("/rest/goods/{item}")
    fun getGoodDetail(@Path("item") item: String,
                      @Query("goods_id") goods_id: String,
                      @Query("from") from: String,
                      @Query("user_id") user_id: String,
                      @Query("zdid") zdid: String,
                      @Query("spm") spm: String
    ): Observable<GoodDetailBean>*/


    //    http://api2.17zwd.com/rest/goods/get_colors?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest
    //    http://api2.17zwd.com/rest/goods/get_sizes?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest

   /* @GET("/rest/goods/{type}?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest")
    fun getGoodAttribute(@Path("type") type: String): Observable<GoodAttributeBean>*/

    //    http://api2.17zwd.com/rest/shop/get_services?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest

    //    http://api2.17zwd.com/rest/region/get_list?from=android&shadow$_klass_=class+com.hanyun.onlineproject.entity.NetRequest&zdid=52&fid=899
   /* @GET("rest/region/get_list")
    fun getRegionData(@Query("from") from: String,
                      @Query("shadow$_klass_") shadow: String,
                      @Query("zdid") zdid: String,
                      @Query("fid") fid: String): Observable<RegionBean>*/

    //    http://stu.17zwd.com/api/searchByUrl?pic_url=http://img.alicdn.com/bao/uploaded/i2/1656591777/TB2jD5bXmyEJuJjSspiXXX4IFXa_!!1656591777.jpg
    // &shadow$_klass_=class+com.hanyun.onlineproject.entity.GetSearchImgBean&zdid=42

  /*  @POST("searchByUrl")
    fun getSearchGood(@Query("pic_url") url: String,
                      @Query("shadow$_klass_") shadow: String,
                      @Query("zdid") zdid: String): Observable<StyleBean>*/




}







