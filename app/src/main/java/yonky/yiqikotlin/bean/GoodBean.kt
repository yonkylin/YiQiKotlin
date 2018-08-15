package yonky.yiqikotlin.bean

import android.os.Parcel
import android.os.Parcelable.Creator
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Administrator on 2018/7/16.
 */

class GoodBean : Serializable {
    /**
     * site_id : 48
     * shop_market_id : 274
     * shop_floor_id : 275
     * shop_id : 39882
     * shop_name : 唯依客网批
     * shop_tb_nick : 唯依客
     * shop_qq : 738138422
     * shop_market : 池尾商圈
     * shop_floor : 1F
     * shop_dangkou : N档
     * shop_services :
     * shop_youhui : null
     * shop_is_flagship : 0
     * goods_id : 106778639
     * gid_old : 0
     * title : 150# (180克双磨牛奶丝)抖音小猪佩奇短袖t恤女【实拍现货】
     * price1 : 29.5
     * price2 : 9.5
     * gno :
     * cate_id : 50000671
     * cate_name : null
     * tb_url : http://item.taobao.com/item.htm?id=569617510344
     * tb_num_iid : 569617510344
     * status : 1
     * tb_img : https://img.alicdn.com/bao/uploaded/i4/2980477580/TB2ybObrntYBeNjy1XdXXXXyVXa_!!2980477580.jpg
     * tb_imgs : null
     * quantity : 0
     * attributes : null
     * sku_attributes : null
     * skus : null
     * spm : C67%2b6Q5%2b6inEQnSwEl%2fV8kmFdMP6NlK%2fKst0z4qes8w%3d
     * wap_url : http://cs.m.17zwd.com/item.htm?gid=106778639
     */

    //    private static final long serialVersionUID = 1234567891;

    var site_id: String = "48"
    var shop_market_id: Int = 0
    var shop_floor_id: Int = 0
    var shop_id: String? = null
    var shop_name: String? = null
    var shop_tb_nick: String? = null
    var shop_qq: String? = null
    var shop_market: String? = null
    var shop_floor: String? = null
    var shop_dangkou: String? = null
    var shop_services: String? = null
    var shop_youhui: String? = null
    var shop_is_flagship: Int = 0
    var goods_id: String? = null
    var gid_old: Int = 0
    var title: String? = null
    var price1: Double = 0.toDouble()
    var price2: Double = 0.toDouble()
    var gno: String? = null
    var cate_id: Int = 0
    var cate_name: String? = null
    var tb_url: String? = null
    var tb_num_iid: Long = 0
    var status: Int = 0
    var tb_img: String? = null
    var tb_imgs: String? = null
    var quantity: Int = 0
    var attributes: List<String>? = null
    var sku_attributes: SkuAttributesBean? = null
    var skus: List<SkusBean>? = null
    var spm: String? = null
    var wap_url: String? = null

    class SkuAttributesBean :Serializable{
        /**
         * colors : 乳白色,白色,灰色,姜黄色,军绿色
         * sizes : S,M,L,XL,2XL
         */

        var colors: String? = null
        var sizes: String? = null
    }



}
