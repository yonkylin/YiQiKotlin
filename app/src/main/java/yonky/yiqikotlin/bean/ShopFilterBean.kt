package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/7/17.
 */

class ShopFilterBean {
    // 逛市场
    //    http://api2.17zwd.com/rest/shop/search_shops/?psize=10&orderby=mr&keyword=&bq=&service=&pindex=1&from=android&zdid=48
    //获取店铺详情
    //    http://api2.17zwd.com/rest/shop/get_shop?shop_id=26974&from=android&user_id=-1&zdid=48&spm=c5jEjVMzAhEqMknXPYkPU9EOVa4gg6EKJId8KFy3%2BVE%3D

    var psize = "10"
    var orderby = "mr"
    var keyword:String? = ""
    var bq:String? = ""
    var service:String? = ""
    var pindex = "1"
    var from = "android"
    var zdid = "48"
    var shop_id:String?= ""
    var spm:String? = ""
    var user_id = "-1"
}
