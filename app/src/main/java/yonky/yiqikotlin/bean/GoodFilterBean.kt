package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/7/17.
 */

class GoodFilterBean {

    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48
    //搜款式
    var price1:String = "0.0"
    var psize:String = "10"
    var size:String? = ""
    var seller_cid:String? = ""
    var orderby:String = "mr"
    var color:String? = ""
    var keyword:String? = ""
    var pindex:String = "1"
    var from = "android"
    var price2:String = "9999.0"
    var dtype = "sks"
    var zdid:String? = "48"
    var spm:String? = ""
    var shop_id:String? = ""
    var goods_id:String? = ""
    var user_id:String = "-1"
    var mid:String? = ""
    var fid:String? = ""
    //    http://api2.17zwd.com/rest/goods/search?price1=0.0&psize=10&size=&seller_cid=&orderby=mr&color=&keyword=&pindex=1&from=android&price2=9999.0&dtype=sks&zdid=48

    var url:String? = ""
}
