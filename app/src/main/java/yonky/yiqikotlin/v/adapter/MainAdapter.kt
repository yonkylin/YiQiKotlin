package yonky.yiqikotlin.v.adapter

import android.content.Context
import android.hardware.Camera
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_my.view.*
import kotlinx.android.synthetic.main.item_my_item.view.*
import kotlinx.android.synthetic.main.item_single_image.view.*
import kotlinx.android.synthetic.main.item_title.view.*
import kotlinx.android.synthetic.main.item_vertical.view.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.AreaEBean
import yonky.yiqikotlin.utils.GlideUtil
import java.io.CharArrayReader
import kotlin.coroutines.experimental.coroutineContext
import yonky.yiqikotlin.v.adapter.MainAdapter.SingleViewHolder
import yonky.yiqikotlin.R.id.iv
import yonky.yiqikotlin.utils.MyUtil



/**
 * Created by Administrator on 2018/7/9.
 */
class MainAdapter(val context:Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        val TAG = MainAdapter::class.java.simpleName

        const val TYPE_BANNER = 0x01   //轮播
        const val TYPE_MY = 0X02   //第二行
        const  val TYPE_TITLE = 0x03//标题
        const  val TYPE_SINGLE = 0X04    //推荐宝贝 单栏
        const  val TYPE_TWO = 0X05    //分2栏
        const  val TYPE_THREE = 0X06    //分3栏

        const val TYPE_ITEM_MY = 0X10    //嵌套recyclerview 我的
        const  val TYPE_ITEM_TJBB = 0X11    //嵌套recyclerview 推荐宝贝
        const val TYPE_ITEM_MRXK = 0X12    //嵌套recyclerview 每日新款


        const  val TYPE_GOODS = 0X20    //打开店铺详情
        const  val TYPE_GOOD_DETAIL = 0X21    //打开商品详情
        const  val TYPE_SERVICE = 0X22    //打开每日推荐，模特实拍等
    }



    val titles = arrayOf("推荐宝贝","精品热卖","每日新款")
   var bannerList:List<AreaBean>?=null
    var b1List:List<AreaBean>?=null
    var b2List:List<AreaBean>?=null
    var c1List:List<AreaBean>?=null
    var c2List:List<AreaBean>?=null
    var dList:List<AreaBean>?=null
    var eList:List<AreaEBean>?=null




    //这里暂时还不是动态的，待修改
    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return TYPE_BANNER
            1, 4, 14 -> return TYPE_MY
            2, 5, 13 -> return TYPE_TITLE
            3, 6, 15, 20, 25, 30, 35 -> return TYPE_SINGLE
            7, 8, 9, 10, 11, 12 -> return TYPE_THREE
            else -> return TYPE_TWO
        }
    }

    override fun getItemCount(): Int {

        val countBaner =if(bannerList ==null) 0 else 1
        val countB1 =if(b1List ==null) 0 else 1
        val countB2 =if(b2List ==null) 0 else 1
        val countC1 = if(c1List ==null) 0 else 1
        val countC2=c2List?.size?:0
        val countD =if(dList ==null) 0 else 1
        val countE=(eList?.size?:0)*5

//        return 2
        return 1+3+countBaner+countB1+countB2+countC1+countC2+countD+countE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater=LayoutInflater.from(context)
        var holder:RecyclerView.ViewHolder
        when(viewType){
            TYPE_BANNER ->holder =BannerViewHolder(inflater.inflate( R.layout.item_banner,parent,false))
            TYPE_MY -> holder=MyViewHolder(inflater.inflate(R.layout.item_my,parent,false))
            TYPE_TITLE-> holder =TitleViewHolder(inflater.inflate(R.layout.item_title,parent,false))
            TYPE_SINGLE, TYPE_THREE ->holder =SingleViewHolder(inflater.inflate(R.layout.item_single_image,parent,false))
            else ->holder =TwoViewHolder(inflater.inflate(R.layout.item_vertical,parent,false))

        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val images =ArrayList<String?>()
        when{
            holder is BannerViewHolder &&bannerList!=null ->{
                val list=bannerList
                for(i:Int in list!!.indices){
                    images.add(list[i].img_url)
                }
                holder.itemView.banner.setImages(images).setImageLoader(GlideUtil()).start()
            }

            holder is MyViewHolder->{
                if(position ==1){
                    holder.itemAdapter.type=TYPE_ITEM_MY
                }else if(b2List!=null &&position==4){
                    holder.itemAdapter.b2List=b2List
                    holder.itemAdapter.type=TYPE_ITEM_TJBB
                }
                holder.itemAdapter.notifyDataSetChanged()
            }

            holder is TitleViewHolder->{
                when(position){
                    2->holder.itemView.tv_title.setText(titles[0])
                    5->holder.itemView.tv_title.setText(titles[1])
                    13->holder.itemView.tv_title.setText(titles[2])
                }
            }

            holder is SingleViewHolder->{
                val layoutParams = holder.itemView.iv_single.getLayoutParams()
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                layoutParams.height = MyUtil.dp2px(context, 140)
                holder.itemView.iv_single.setLayoutParams(layoutParams)
                if (b1List!=null && position == 3) {
                   GlideUtil.loadImage(b1List!![0].img_url!!, holder.itemView.iv_single)

                } else if (c1List!=null && position == 6) {
                    GlideUtil.loadImage(c1List!![0].img_url!!, holder.itemView.iv_single)
                } else if (c2List !=null && position >= 7 && position <= 12) {
                    layoutParams.height = MyUtil.dp2px(context, 140)
                    GlideUtil.loadImage(c2List!![position-7].img_url!!, holder.itemView.iv_single)
                } else if (eList!=null && position > 14) {
                    /* 对应位置为
               15 20 25 30 35
                    -15
                0  5  10 15 20
                        对5取整
                0  1  2  3  4
  */
                    val i = (position - 15) / 5
                    val bean = eList!![i]?.m_Item1
                    bean?.let{
                        bean[0]!!.img_url!!
                        GlideUtil.loadImage( bean[0]!!.img_url!!, holder.itemView.iv_single)
                    }

                }
//                holder.itemView.setOnClickListener(MyClickListener(mContext, bean, TYPE_GOODS))
            }

            holder is TwoViewHolder && position>14 ->{
                /* 两列类型的位置为    转为elist对应的位置
           16 17 18 19               1  2  3  4                             0
           21 22 23 24               6  7  8  9                             1
           26 27 28 29       -15得   11 12 13 14      对5取整               2
           31 32 33 34               16 17 18 19                            3
           36 37 38 39               21 22 23 24                            4
         */
                if(eList!=null){
                    val i=(position-15)/5;
                    val j=(position-15)%5 -1;
                   var bean = eList!![i].m_Item2!![j]
                    bean?.let{

                    GlideUtil.loadImage(bean.img_url!!,holder.itemView.iv_img)
                    holder.itemView.title.setText(bean.title);
                    holder.itemView.tv_price.setText(context.getResources().getString(R.string.price,bean.price));

                }
//                    holder.itemView.setOnClickListener MyClickListener(mContext, bean, TYPE_GOOD_DETAIL)
            }
            }



        }
//        if(holder is BannerViewHolder &&bannerList!=null){
//            val list=bannerList
//            for(i:Int in list!!.indices){
//
//              images.add(list[i].img_url)
//
//
//            }
//            Logger.d(images)
//            holder.itemView.banner.setImages(images).setImageLoader(GlideUtil()).start()
//
//            //点击事件
//        }else if(holder is MyViewHolder){
//            if(position ==1){
//                holder.itemAdapter.type=TYPE_ITEM_MY
//            }else if(b2List!=null &&position==4){
//                holder.itemAdapter.b2List=b2List
//                holder.itemAdapter.type=TYPE_ITEM_TJBB
//            }
//            holder.itemAdapter.notifyDataSetChanged()
//        }
    }

    inner class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    inner class TitleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    inner class SingleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    inner class TwoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var rv=itemView.item_rv
        val itemAdapter by lazy{ItemAdapter(context) }
        init{
            rv.layoutManager =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            rv.adapter=itemAdapter

        }
    }
}