package yonky.yiqikotlin.v.adapter

import android.content.Context
import yonky.yiqikotlin.bean.ShopBean
import yonky.yiqikotlin.bean.GoodBean
import android.support.v7.widget.RecyclerView
import android.content.Intent
import android.graphics.Color
import android.support.annotation.NonNull
import yonky.yiqikotlin.utils.GlideUtil
import yonky.yiqikotlin.v.GoodsActivity
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.text.SpannableString
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.item_detail_banner.view.*
import kotlinx.android.synthetic.main.item_detail_img.view.*
import kotlinx.android.synthetic.main.item_detail_shop.view.*
import kotlinx.android.synthetic.main.item_detail_type.view.*
import yonky.yiqikotlin.R


/**
 * Created by Administrator on 2018/8/15.
 */

class DetailAdapter( val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var goodBean : GoodBean?=null
    var shopBean:ShopBean?=null
    var imgList= ArrayList<String>()

    private var type = TYPE_PIC



    override fun getItemCount(): Int {
        var i = 0
        var j = 0
        if (imgList != null) {
            i = imgList!!.size
        }
        if (goodBean != null) {
            j = goodBean!!.attributes!!.size
        }
        return if (type == TYPE_PIC) 3 + i else 3 + j
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_BANNER
        } else if (position == 1) {
            return TYPE_SHOP
        } else if (position == 2) {
            return TYPE_STYLE
        }
        return type
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val layoutInflater = LayoutInflater.from(mContext)
        when (viewType) {
            TYPE_BANNER -> return BannerHolder(layoutInflater.inflate(R.layout.item_detail_banner, parent, false))
            TYPE_SHOP -> return ShopHolder(layoutInflater.inflate(R.layout.item_detail_shop, parent, false))
            TYPE_STYLE -> return TypeHolder(layoutInflater.inflate(R.layout.item_detail_type, parent, false))
            else ->

                return PicTextHolder(layoutInflater.inflate(R.layout.item_detail_img, parent, false))
        }

    }

    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        if (goodBean != null) {
            when {
                holder is BannerHolder -> {
                    val s = goodBean!!.tb_imgs
                    val imgs = s!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val urls = ArrayList<String>()
                    for (i in imgs.indices) {
                        urls.add(imgs[i])
                    }
                    holder.itemView.detail_banner.setImages(urls).setImageLoader(GlideUtil()).start()


                    holder.itemView.tv_title.text = goodBean!!.title
                    holder.itemView.tv_price2.text = String.format("¥ %.1f", goodBean!!.price2)
                    //             删除线
                    val spannableString = SpannableString("淘宝价¥" + goodBean!!.price1)
                    val strikethroughSpan = StrikethroughSpan()
                    spannableString.setSpan(strikethroughSpan, 0, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                    holder.itemView.tv_price1.text = spannableString

                    holder.itemView.tv_goods_id.text = "货号" + goodBean!!.gno!!

                }
               holder is ShopHolder-> {
                    if (shopBean != null) {
                        GlideUtil.loadImage(shopBean!!.serller_head_original!!, holder.itemView.iv_shopImg)
                        holder.itemView.tv_major.text = shopBean!!.major
                        holder.itemView.tv_shop.text = shopBean!!.shop_name
                        holder.itemView.tv_discount.text = shopBean!!.discount
                        holder.itemView.tv_position.text = shopBean!!.market + "-" + shopBean!!.floor + "-" + shopBean!!.dangkou
//                        holder.goShop!!.setOnClickListener(object : View.OnClickListener() {
//                            fun onClick(view: View) {
//                                val intent = Intent(mContext, GoodsActivity::class.java)
//                                intent.putExtra("shopbean", shopBean)
//                                mContext.startActivity(intent)
//                            }
//                        })
                    }


                }
               holder is TypeHolder-> {
                    if (type == TYPE_PIC) {
                        holder.itemView.good_pic.setTextColor(Color.RED)
                        holder.itemView.good_param.setTextColor(Color.BLACK)
//                        holder.goodParam!!.setOnClickListener(DetailClickListener())

                    } else {
                        holder.itemView.good_pic.setTextColor(Color.BLACK)
                        holder.itemView.good_param.setTextColor(Color.RED)
//                        holder.goodPic!!.setOnClickListener(DetailClickListener())
                    }


                }
                holder is PicTextHolder-> {
                    if ((type == TYPE_PIC) and (imgList != null)) {
                        GlideUtil.loadImage(imgList!![position - 3], holder.itemView.iv_pic)
                    } else {
                        holder.itemView.tv_detail.text = goodBean!!.attributes!![position - 3]
                    }
                }
            }
        }

    }

   inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        init{
//            itemView.tv_add_list.setOnClickListener(){MyUtil.toast(mContext)}
//            itemView.tv_img_search.setOnClickListener{
//                if (goodBean != null) {
//                    val bean = goodBean
//                    val intent = Intent(mContext, ActivityFragment::class.java)
//                    //            不知道为什么这句会闪退,在stylefragment也可以传递过来
//                    //            原来是GoodBean里面含有非序列化的属性。置为null即可。
//                    bean!!.sku_attributes = null
//                    bean.skus = null
//                    intent.putExtra("goodbean", bean)
//                    intent.putExtra("searchtype", "img")
//                    mContext.startActivity(intent)
//                }
//            }
            //        @OnClick(R.id.tv_img_search)
//        fun setImgSearch() {
//            if (goodBean != null) {
//                val bean = goodBean
//                val intent = Intent(mContext, ActivityFragment::class.java)
//                //            不知道为什么这句会闪退,在stylefragment也可以传递过来
//                //            原来是GoodBean里面含有非序列化的属性。置为null即可。
//                bean!!.sku_attributes = null
//                bean.skus = null
//                intent.putExtra("goodbean", bean)
//                intent.putExtra("searchtype", "img")
//
//                mContext.startActivity(intent)
//            }
//        }
//
//        @OnClick(R.id.tv_search_titile)
//        fun setSearchTitle() {
//            if (goodBean != null) {
//                val bean = goodBean
//                val intent = Intent(mContext, ActivityFragment::class.java)
//                //            不知道为什么这句会闪退,在stylefragment也可以传递过来
//                //            原来是GoodBean里面含有非序列化的属性。置为null即可。
//                bean!!.sku_attributes = null
//                bean.skus = null
//                intent.putExtra("goodbean", bean)
//                intent.putExtra("searchtype", "title")
//
//                mContext.startActivity(intent)
//            }
//        }
//        }
   }

     inner class ShopHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

     inner class TypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

     inner class PicTextHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

//     inner class DetailClickListener : View.OnClickListener {
//        fun onClick(view: View) {
//            if (type == TYPE_PIC) {
//                type = TYPE_PARAM
//            } else if (type == TYPE_PARAM) {
//                type = TYPE_PIC
//            }
//
//            notifyDataSetChanged()
//        }
//    }

    companion object {
        val TYPE_BANNER = 0X001
        val TYPE_SHOP = 0X002
        val TYPE_PIC = 0X003
        val TYPE_PARAM = 0X004
        val TYPE_STYLE = 0X005
    }

}
