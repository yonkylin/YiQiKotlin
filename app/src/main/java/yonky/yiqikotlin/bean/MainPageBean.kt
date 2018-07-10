package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/7/10.
 */
class MainPageBean {


    var popularize_items_list_get_response: PopularizeItemsListGetResponseBean? = null
    var status_code: Int = 0
    var result: String? = null


   class PopularizeItemsListGetResponseBean {
        var areaA: List<AreaBean>? = null
        var areaB1: List<AreaBean>? = null
        var areaB2: List<AreaBean>? = null
        var areaC1: List<AreaBean>? = null
        var areaC2: List<AreaBean>? = null
        var areaD: List<AreaBean>? = null
        var areaE: List<AreaEBean>? = null

    }

}
