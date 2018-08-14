package yonky.yiqikotlin.v.adapter

import android.content.Context
import yonky.yiqikotlin.bean.ShopBean
import android.widget.TextView
import yonky.yiqikotlin.R.id.iv_img
import yonky.yiqikotlin.R.id.tv_position
import android.support.v7.widget.RecyclerView
import android.support.v4.content.ContextCompat.startActivity
import yonky.yiqikotlin.v.GoodsActivity
import android.content.Intent
import android.support.annotation.NonNull
import yonky.yiqikotlin.utils.GlideUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_market.view.*
import yonky.yiqikotlin.R


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

            holder.itemView.setOnClickListener {
                    goGoodsActivity(position)
                }

        }
    }

    private fun goGoodsActivity(position: Int) {
        val bean = beanList[position]
        val intent = Intent(mContext, GoodsActivity::class.java)
        intent.putExtra("shopbean", bean)
        mContext.startActivity(intent)
    }

    inner class MarketHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
