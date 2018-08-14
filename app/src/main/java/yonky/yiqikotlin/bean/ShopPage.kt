package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/8/14.
 */

class ShopPage {

    /**
     * shop_item_get_response : {"item":{"site_id":48,"site_tag":"cs","shop_id":26974,"shop_name":"知名度","is_reg":1,"vip":2,"tb_nick":"淘亿林","tb_url":"shop137276001.taobao.com","tb_url_2":"http://shop137276001.taobao.com","qq":"2646142581","phone":"13144082450","wechat":"","major":"女装","address":"广东省普宁市池尾大圆往揭阳方向300米左右，池尾大道路旁","market_id":274,"market":"池尾商圈","floor_id":275,"floor":"1F","dangkou":"N档","s_service":"一件代发","seller_head":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg_50x50.jpg","serller_head_original":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg","discount":"减20","spm":"5cKZa.48.66938.26974.0.10070","wap_url":"http://cs.m.17zwd.com/shop/26974.htm"},"is_favor_shop":0}
     * status_code : 200
     * result : success
     */

    var shop_item_get_response: ShopItemGetResponseBean? = null
    var status_code: Int = 0
    var result: String? = null

    class ShopItemGetResponseBean {
        /**
         * item : {"site_id":48,"site_tag":"cs","shop_id":26974,"shop_name":"知名度","is_reg":1,"vip":2,"tb_nick":"淘亿林","tb_url":"shop137276001.taobao.com","tb_url_2":"http://shop137276001.taobao.com","qq":"2646142581","phone":"13144082450","wechat":"","major":"女装","address":"广东省普宁市池尾大圆往揭阳方向300米左右，池尾大道路旁","market_id":274,"market":"池尾商圈","floor_id":275,"floor":"1F","dangkou":"N档","s_service":"一件代发","seller_head":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg_50x50.jpg","serller_head_original":"http://logo.taobao.com/shop-logo/99/da/TB1HNEfHFXXXXXSapXXSutbFXXX.jpg","discount":"减20","spm":"5cKZa.48.66938.26974.0.10070","wap_url":"http://cs.m.17zwd.com/shop/26974.htm"}
         * is_favor_shop : 0
         */

        var item: ShopBean? = null
        var is_favor_shop: Int = 0


    }
}
