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
import yonky.yiqikotlin.R
import yonky.yiqikotlin.bean.AreaBean
import yonky.yiqikotlin.bean.AreaEBean
import yonky.yiqikotlin.utils.GlideUtil
import java.io.CharArrayReader
import kotlin.coroutines.experimental.coroutineContext

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
    val b1List=ArrayList<AreaBean>()
    val b2List=ArrayList<AreaBean>()
    val c1List=ArrayList<AreaBean>()
    val c2List=ArrayList<AreaBean>()
    val dList=ArrayList<AreaBean>()
    val eList=ArrayList<AreaEBean>()




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
        val countB1 =if(b1List.size==0) 0 else 1
        val countB2 =if(b2List.size ==0) 0 else 1
        val countC1 = if(c1List.size==0) 0 else 1
        val countC2=c2List.size
        val countD =if(dList.size ==0) 0 else 1
        val countE=eList.size*5

        return 2
//        return 1+3+countBaner+countB1+countB2+countC1+countC2+countD+countE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder:RecyclerView.ViewHolder
        when(viewType){
            TYPE_BANNER ->holder =BannerViewHolder(LayoutInflater.from(context).inflate( R.layout.item_banner,parent,false))
            else -> holder=MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my,parent,false))


        }
        return holder
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val images =ArrayList<String?>()
        if(holder is BannerViewHolder &&bannerList!=null){
            val list=bannerList
            for(i:Int in list!!.indices){

              images.add(list[i].img_url)


            }
            Logger.d(images)
            holder.itemView.banner.setImages(images).setImageLoader(GlideUtil()).start()

            //点击事件
        }else if(holder is MyViewHolder){
            if(position ==1){
                holder.itemAdapter.type=TYPE_ITEM_MY
            }else if(b2List.size!=0 &&position==4){
                holder.itemAdapter.b2List=b2List
                holder.itemAdapter.type=TYPE_ITEM_TJBB
            }
            holder.itemAdapter.notifyDataSetChanged()
        }
    }

    inner class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var rv=itemView.item_rv
        val itemAdapter by lazy{ItemAdapter(context) }
        init{
            rv.layoutManager =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            rv.adapter=itemAdapter

        }
    }
}