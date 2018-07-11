package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/7/11.
 */

data class MainPageBean(
    val popularize_items_list_get_response: PopularizeItemsListGetResponse,
    val status_code: Int,
    val result: String
)

 class PopularizeItemsListGetResponse{
     var AreaA: List<AreaBean>?=null
     var AreaB1: List<AreaBean>? = null
     var AreaB2: List<AreaBean>? = null
     var AreaC1: List<AreaBean>? = null
     var AreaC2: List<AreaBean>? = null


     var AreaD: List<AreaBean>? = null
     var AreaE: List<AreaEBean>? = null
 }

