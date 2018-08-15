package yonky.yiqikotlin.v.adapter

import android.content.Context
import yonky.yiqikotlin.bean.ShopBean
import android.support.v7.widget.RecyclerView
import yonky.yiqikotlin.v.GoodsActivity
import android.content.Intent
import android.support.annotation.NonNull
import yonky.yiqikotlin.utils.GlideUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_market.view.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.listener.MyClickListener


/**
 * Created by Administrator on 2018/8/14.
 */

class MarketAdapter(private val mContext: Context) : RecyclerView.Adapter<MarketAdapter.MarketHolder>() {
    var beanList=ArrayList<ShopBean>()
    override fun getItemCount(): Int {
        return beanList.size
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MarketHolder {
        return MarketHolder(LayoutInflater.from(mContext).inflate(R.layout.item_market, parent, false))
    }

    override fun onBindViewHolder(@NonNull holder: MarketHolder, position: Int) {
        if (beanList.isNotEmpty()) {
            val item = beanList[position]
            GlideUtil.loadImage(item.serller_head_original!!, holder.itemView.iv_shop)
            holder.itemView.tv_discount.text = item.discount
            holder.itemView.tv_shopName.text = item.shop_name
            holder.itemView.tv_position.text = item.market + "-" + item.floor + "-" + item.dangkou
            holder.itemView.tv_major.text = "主营:" + item.major!!
            holder.itemView.tv_service.text = "服务:" + item.s_service!!

            val filter:Filter = convertFilter(beanList[position])
            holder.itemView.setOnClickListener (MyClickListener(mContext,filter, MainAdapter.TYPE_GOODS))

        }
    }


    fun convertFilter(shopBean: ShopBean): Filter {
        val filter= Filter()
        filter.spm=shopBean.spm
        filter.shop_id=shopBean.shop_id
        filter.zdid=shopBean.site_id
        return filter
    }

    inner class MarketHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
