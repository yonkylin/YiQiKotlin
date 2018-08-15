package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/8/15.
 */

class GoodAttributeBean {


    /**
     * goods_item_get_response : {"clothes":[{"text":"均码","value":"均"},{"text":"S","value":"S"},{"text":"M","value":"M"},{"text":"L","value":"L"},{"text":"XL","value":"XL"},{"text":"XXL","value":"XXL"},{"text":"XXXL","value":"XXXL"},{"text":"XXXXL","value":"XXXXL"}],"pants":[{"text":"26","value":"26"},{"text":"27","value":"27"},{"text":"28","value":"28"},{"text":"29","value":"29"},{"text":"30","value":"30"},{"text":"31","value":"31"},{"text":"32","value":"32"},{"text":"33","value":"33"},{"text":"34","value":"34"},{"text":"35","value":"35"},{"text":"36","value":"36"}],"shoes":[{"text":"31","value":"31"},{"text":"32","value":"32"},{"text":"33","value":"33"},{"text":"34","value":"34"},{"text":"35","value":"35"},{"text":"36","value":"36"},{"text":"37","value":"37"},{"text":"38","value":"38"},{"text":"39","value":"39"},{"text":"40","value":"40"},{"text":"41","value":"41"},{"text":"42","value":"42"},{"text":"43","value":"43"},{"text":"44","value":"44"},{"text":"45","value":"45"}]}
     */


    var status_code: Int = 0
    var result: String? = null
    /**
     * goods_item_get_response : {"items":[{"text":"黑色","value":"黑"},{"text":"白色","value":"白"},{"text":"灰色","value":"灰"},{"text":"粉色","value":"粉"},{"text":"红色","value":"红"},{"text":"紫色","value":"紫"},{"text":"黄色","value":"黄"},{"text":"蓝色","value":"蓝"},{"text":"绿色","value":"绿"},{"text":"橙色","value":"橙"},{"text":"杏色","value":"杏"},{"text":"卡其色","value":"卡其"},{"text":"咖啡色","value":"咖啡"},{"text":"藏青色","value":"藏青"},{"text":"枣红色","value":"枣红"},{"text":"墨绿色","value":"墨绿"}]}
     */

    var goods_item_get_response: GoodsItemGetResponseBean? = null


    class GoodsItemGetResponseBean {
        var items: List<KVBean>? = null
        var clothes: List<KVBean>? = null
        var pants: List<KVBean>? = null
        var shoes: List<KVBean>? = null


    }


}
