package yonky.yiqikotlin.listener

import android.content.Context
import android.content.Intent
import android.view.View
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.v.GoodDetailActivity
import yonky.yiqikotlin.v.GoodsActivity
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_GOODS
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_GOOD_DETAIL

/**
 * Created by Administrator on 2018/7/31.
 */
class MyClickListener(val mContext: Context,val filter:Filter,val type:Int):View.OnClickListener{
    var dtype:String?=null
//    var filter= Filter()
//    constructor(mContext: Context,dtype:String,type:Int):this(mContext,null,type){
//        val dtype=dtype
//    }
//    init{
//        filter.spm=areaBean?.spm
//        filter.goods_id=areaBean?.goods_id
//        filter.shop_id=areaBean?.shop_id
//        filter.zdid=areaBean?.site_id
//
//    }


    override fun onClick(v: View) {
        var intent:Intent?=null
        when(type){
            TYPE_GOODS->intent =Intent(mContext, GoodsActivity::class.java)

            TYPE_GOOD_DETAIL-> intent = Intent(mContext,GoodDetailActivity::class.java)

        }
        intent?.putExtra("filter",filter)
        mContext.startActivity(intent)
    }
}