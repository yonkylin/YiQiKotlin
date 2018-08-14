package yonky.yiqikotlin.bean

import android.os.Parcel
import android.os.Parcelable.Creator
import android.os.Parcelable



/**
 * Create
d by Administrator on 2018/7/17.
 */

class ShopBean : Parcelable {
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


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.site_id)
        dest.writeString(this.site_tag)
        dest.writeString(this.shop_id)
        dest.writeString(this.shop_name)
        dest.writeInt(this.is_reg)
        dest.writeInt(this.vip)
        dest.writeString(this.tb_nick)
        dest.writeString(this.tb_url)
        dest.writeString(this.tb_url_2)
        dest.writeString(this.qq)
        dest.writeString(this.phone)
        dest.writeString(this.wechat)
        dest.writeString(this.major)
        dest.writeString(this.address)
        dest.writeInt(this.market_id)
        dest.writeString(this.market)
        dest.writeInt(this.floor_id)
        dest.writeString(this.floor)
        dest.writeString(this.dangkou)
        dest.writeString(this.s_service)
        dest.writeString(this.seller_head)
        dest.writeString(this.serller_head_original)
        dest.writeString(this.discount)
        dest.writeString(this.spm)
        dest.writeString(this.wap_url)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.site_id = `in`.readString()
        this.site_tag = `in`.readString()
        this.shop_id = `in`.readString()
        this.shop_name = `in`.readString()
        this.is_reg = `in`.readInt()
        this.vip = `in`.readInt()
        this.tb_nick = `in`.readString()
        this.tb_url = `in`.readString()
        this.tb_url_2 = `in`.readString()
        this.qq = `in`.readString()
        this.phone = `in`.readString()
        this.wechat = `in`.readString()
        this.major = `in`.readString()
        this.address = `in`.readString()
        this.market_id = `in`.readInt()
        this.market = `in`.readString()
        this.floor_id = `in`.readInt()
        this.floor = `in`.readString()
        this.dangkou = `in`.readString()
        this.s_service = `in`.readString()
        this.seller_head = `in`.readString()
        this.serller_head_original = `in`.readString()
        this.discount = `in`.readString()
        this.spm = `in`.readString()
        this.wap_url = `in`.readString()
    }

    companion object {

        /**
         * site_id : 48
         * site_tag : cs
         * shop_id : 505447
         * shop_name : 机器服饰
         * is_reg : 1
         * vip : 1
         * tb_nick : 机器服饰
         * tb_url : shop137831045.taobao.com
         * tb_url_2 : null
         * qq : 429225408
         * phone : 15819559299
         * wechat : null
         * major : 女装,男装
         * address : 军埔电子商务产业园
         * market_id : 243
         * market : 更多
         * floor_id : 702
         * floor : 军埔商圈
         * dangkou : N档
         * s_service : 一件代发,退现金,包换款
         * seller_head : http://logo.taobao.com/shop-logo/db/db/TB1GeSsis2vU1JjSZFwSut2cpXa.jpg_50x50.jpg
         * serller_head_original : http://logo.taobao.com/shop-logo/db/db/TB1GeSsis2vU1JjSZFwSut2cpXa.jpg
         * discount : 实价
         * spm : C67%2b6Q5%2b6im0MUn7W8yuK7N4%2b56FHZmG
         * wap_url : http://cs.m.17zwd.com/shop/505447.htm
         */
        private val serialVersionUID: Long = 1234567892

        val CREATOR: Creator<ShopBean> = object : Creator<ShopBean> {
            override fun createFromParcel(source: Parcel): ShopBean {
                return ShopBean(source)
            }

            override fun newArray(size: Int): Array<ShopBean?> {
                return arrayOfNulls(size)
            }
        }
    }
}
