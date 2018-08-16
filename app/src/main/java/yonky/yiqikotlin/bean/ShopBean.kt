package yonky.yiqikotlin.bean

import android.os.Parcel
import android.os.Parcelable.Creator
import android.os.Parcelable
import java.io.Serializable


/**
 * Create
d by Administrator on 2018/7/17.
 */

class ShopBean : Serializable{
    var site_id: String? = null
    var site_tag: String? = null
    var shop_id: String? = null
    var shop_name: String? = null
    var is_reg: Int = 0
    var vip: Int = 0
    var tb_nick: String? = null
    var tb_url: String? = null
    var tb_url_2: String? = null
    var qq: String? = null
    var phone: String? = null
    var wechat: String? = null
    var major: String? = null
    var address: String? = null
    var market_id: Int = 0
    var market: String? = null
    var floor_id: Int = 0
    var floor: String? = null
    var dangkou: String? = null
    var s_service: String? = null
    var seller_head: String? = null
    var serller_head_original: String? = null
    var discount: String? = null
    var spm: String? = null
    var wap_url: String? = null

}
