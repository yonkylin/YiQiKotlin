package yonky.yiqikotlin.v.adapter

import android.content.Context
import android.content.Intent
import yonky.yiqikotlin.bean.GoodBean
import android.support.v7.widget.RecyclerView
import android.support.annotation.NonNull
import yonky.yiqikotlin.utils.GlideUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_style.view.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.Filter
import yonky.yiqikotlin.v.GoodDetailActivity


/**
 * Created by Administrator on 2018/8/13.
 */

class StyleAdapter( var mContext:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var emptyCount: Int = 0
   var beanList=ArrayList<GoodBean>()


//    init{
//        beanList=beanList1 as ArrayList<GoodBean>
//    }
    override fun getItemViewType(position: Int): Int {
        return if (beanList.isEmpty()) {
            TYPE_NODATA  //加载失败或返回201 没有数据
        } else {
            TYPE_NORMAL //正常情况下
        }
    }

    override fun getItemCount(): Int {
        return if (beanList.isEmpty()) emptyCount else beanList.size
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

//        if (viewType == TYPE_NODATA) {
//            NoDataHolder(LayoutInflater.from(mContext).inflate(R.layout.item_nodata, parent, false))
//        } else {
        return    StyleHolder(LayoutInflater.from(mContext).inflate(R.layout.item_style, parent, false))
//        }
    }


    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        if (beanList.size != 0) {
            val bean = beanList[position]
            GlideUtil.loadImage(bean.tb_img!!, (holder as StyleHolder).itemView.iv_shopImg)
            holder.itemView.tv_title!!.text = bean.title
            holder.itemView.tv_price!!.setText(mContext.getResources().getString(R.string.price, bean.price2))
            holder.itemView.setOnClickListener {
                startDetail(position)

            }

        }


    }

    private fun startDetail(position: Int) {
        val bean :GoodBean= beanList[position]
        val intent = Intent(mContext, GoodDetailActivity::class.java)
        intent.putExtra("filter", convertFilter(bean))
        mContext.startActivity(intent)

    }
    fun convertFilter(goodBean: GoodBean): Filter {
        val filter= Filter()
        filter.spm=goodBean.spm
        filter.goods_id=goodBean.goods_id
        filter.shop_id=goodBean.shop_id
        filter.zdid=goodBean.site_id
        return filter
    }

    inner class StyleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class NoDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    fun setEmptyCount(emptyCount: Int) {
        this.emptyCount = emptyCount
    }

    companion object {
        val TYPE_NORMAL = 0X01
        val TYPE_NODATA = 0X10
    }
}
