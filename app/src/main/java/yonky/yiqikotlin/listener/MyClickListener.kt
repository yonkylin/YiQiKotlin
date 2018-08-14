package yonky.yiqikotlin.listener

import android.content.Context
import android.content.Intent
import android.view.View
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.v.GoodsActivity
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_GOODS

/**
 * Created by Administrator on 2018/7/31.
 */
class MyClickListener(val mContext: Context,val areaBean: AreaBean?,val type:Int):View.OnClickListener{
    var dtype:String?=null
    constructor(mContext: Context,dtype:String,type:Int):this(mContext,null,type){
        val dtype=dtype
    }


    override fun onClick(v: View) {
        when(type){
            TYPE_GOODS->{
                val intent =Intent(mContext, GoodsActivity::class.java)
                intent.putExtra("areabean",areaBean)
                        mContext.startActivity(intent)
            }

        }
    }
}