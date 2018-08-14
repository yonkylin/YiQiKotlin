package yonky.yiqikotlin.v.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_my_item.view.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_ITEM_MRXK
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_ITEM_MY
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_ITEM_TJBB
import yonky.yiqikotlin.utils.GlideUtil
import com.ashokvarma.bottomnavigation.utils.Utils.dp2px
import yonky.yiqikotlin.utils.MyUtil
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_GOODS
import yonky.yiqikotlin.v.adapter.MainAdapter.Companion.TYPE_SERVICE


/**
 * Created by Administrator on 2018/7/9.
 */
class ItemAdapter(val context:Context): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    val drawables=arrayOf(R.drawable.home_mrxk,R.drawable.home_mtsp,R.drawable.home_scbb,R.drawable.home_scdk, R.drawable.home_wtk)
    var type:Int=0
    var b2List :List<AreaBean>?=null
    var dList :List<AreaBean>?=null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_item,parent,false))
    }



    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val layoutParams =holder.itemView.iv.layoutParams


        when (type) {
            TYPE_ITEM_MY -> {
                layoutParams.width = MyUtil.dp2px(context, 100)
                layoutParams.height = MyUtil.dp2px(context, 100)
                holder.itemView.iv.layoutParams = layoutParams
                holder.itemView.iv.setImageResource(drawables[position])
            }
            TYPE_ITEM_TJBB -> {
                layoutParams.width = MyUtil.dp2px(context, 130)
                layoutParams.height = MyUtil.dp2px(context, 220)
                holder.itemView.iv.layoutParams = layoutParams

                if(b2List!=null){
                    val url =b2List!![position].img_url
                    url?.let{
                        GlideUtil.loadImage(url, holder.itemView.iv)
                    }
                }


//                holder.itemView.setOnClickListener(MyClickListener(mContext, b2List[position], TYPE_GOODS))
            }
            TYPE_ITEM_MRXK -> {
                layoutParams.width = MyUtil.dp2px(context, 200)
                layoutParams.height = MyUtil.dp2px(context, 250)
                holder.itemView.iv.layoutParams = layoutParams

                if(dList!=null){
                    val url =dList!![position].img_url
                    url?.let{
                        GlideUtil.loadImage(url, holder.itemView.iv)
                    }
                }



//                holder.itemView.setOnClickListener(MyClickListener(mContext, dList[position], TYPE_GOODS))
            }
        }

    }

    override fun getItemCount(): Int {
        when (type) {
            TYPE_ITEM_MY -> return drawables.size
            TYPE_ITEM_TJBB -> return b2List?.size?:0
            TYPE_ITEM_MRXK -> return dList?.size?:0
            else -> return 0
        }
    }

    class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}