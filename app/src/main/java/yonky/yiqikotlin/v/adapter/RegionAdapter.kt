package yonky.yiqikotlin.v.adapter

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.region_text.view.*
import yonky.yiqikotlin.R

/**
 * Created by Administrator on 2018/7/13.
 */
class RegionAdapter(val mContext:Context,region:String) :RecyclerView.Adapter<RegionAdapter.RegionViewHolder>(){

val regions=arrayOf( "广州","42",
        "潮汕","48",
        "新塘","52",
        "杭州","43",
        "白沟","55",
        "株洲","46",
        "花都","44",
        "郑州","47",
        "东莞","50",
        "深圳","53",
        "北京","45",
        "揭阳","54",
        "葫芦岛","56")
    var isSelected=0
  lateinit var editor: SharedPreferences.Editor
    init {
        isSelected = searchString(regions,region)/2
        editor=mContext.getSharedPreferences("data",0).edit()
    }

    override fun getItemCount(): Int {
        return regions.size/2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder{
      return  RegionViewHolder(LayoutInflater.from(mContext).inflate(R.layout.region_text,parent,false))
    }



    override fun onBindViewHolder(holder:RegionViewHolder, position: Int) {
        if (position==isSelected) holder.itemView.tv_region.setTextColor(mContext.getResources().getColor(R.color.colorPrimary))
        else holder.itemView.tv_region.setTextColor(mContext.getResources().getColor(R.color.gray))
        holder.itemView.tv_region.setText(regions[position*2])
        holder.itemView.setOnClickListener(){
            setSelect(position)
        }
    }

    private fun searchString(strings: Array<String>, s: String): Int {
        for (i in strings.indices) {
            if (s == strings[i]) {
                return i
            }
        }
        return -1
    }

    private fun setSelect(position:Int){
        isSelected = position
        editor.putString("region",regions[isSelected*2])
        editor.putString("regionId",regions[isSelected*2+1]);
        editor.apply();
        notifyDataSetChanged();
    }

    class RegionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}