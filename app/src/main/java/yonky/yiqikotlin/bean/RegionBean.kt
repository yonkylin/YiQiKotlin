package yonky.yiqikotlin.bean

/**
 * Created by Administrator on 2018/8/15.
 */


data class RegionBean(
    val region_items_list_get_response: RegionItemsListGetResponse,
    val status_code: Int,
    val result: String
)

data class RegionItemsListGetResponse(
    val items: List<Item>
)

data class Item(
    val site_id: Int,
    val reg_id: Int,
    val title: String
)