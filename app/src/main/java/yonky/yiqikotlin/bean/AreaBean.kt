package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/7/11.
 */


import java.io.Serializable
import android.os.Parcel
import android.os.Parcelable.Creator
import android.os.Parcelable



/**
 * Created by Administrator on 2018/7/9.
 */


class AreaBean : Serializable {

    /**
     * site_id : 48
     * ad_id : 11682
     * page_id : 125
     * pos_id : 218
     * data_type : PopularizeModule
     * media_type : Image
     * target : Shop
     * goods_id : 0
     * shop_id : 3158
     * title : 爱尚网批
     * price : 0
     * url : http://cs.17zwd.com/shop/3158.htm
     * img_url : https://aims.17zwd.com/osys/48/3beada5ca96c3966374f05ecd6d2bc8c.jpg
     * img_url2 : https://aims.17zwd.com/osys/48/3beada5ca96c3966374f05ecd6d2bc8c.jpg
     * spm : c5jEjVMzAhHFy5%2btBPM%2baMQqPYc7jeY%2f6NJ1FG6YAMQ%3d
     * seconds : 5
     */

    //    private static final long serialVersionUID = 1234567890;

    var site_id: String? = null
    var ad_id: Int = 0
    var page_id: Int = 0
    var pos_id: Int = 0
    var data_type: String? = null
    var media_type: String? = null
    var target: String? = null
    var goods_id: String? = null
    var shop_id: String? = null
    var title: String? = null
    var price: Float = 0.toFloat()
    var url: String? = null
    var img_url: String? = null
    var img_url2: String? = null
    var spm: String? = null
    var seconds: Int = 0
    var dtype = "sks"




}
