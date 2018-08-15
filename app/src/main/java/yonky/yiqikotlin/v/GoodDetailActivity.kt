package yonky.yiqikotlin.v

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_good_detail.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.base.contract.GoodDetailContract
import yonky.yiqikotlin.bean.*
import yonky.yiqikotlin.p.GoodDetailPresenter
import yonky.yiqikotlin.utils.MyUtil
import yonky.yiqikotlin.utils.StatusBarUtil
import yonky.yiqikotlin.v.adapter.DetailAdapter



/**
 * Created by Administrator on 2018/8/15.
 */
class GoodDetailActivity : BaseActivity(), GoodDetailContract.View{
    val mPresenter by lazy{GoodDetailPresenter(this)}
    val mAdapter by lazy{DetailAdapter(this)}
//    var goodFilter=GoodFilterBean()
//    var shopFilter =ShopFilterBean()
    lateinit var filter:Filter
    var mdy=0
    var mGoodBean:GoodBean? = null


    override fun getLayoutId(): Int = R.layout.activity_good_detail

    override fun initData() {
         filter=intent.getSerializableExtra("filter")as Filter
//        val areaBean=intent.getSerializableExtra("areabean")as AreaBean
//        val goodBean = intent.getSerializableExtra("goodbean")as GoodBean
        mPresenter.attachView(this)
//        if(areaBean!=null){
//            goodFilter.spm=areaBean.spm
//            goodFilter.zdid=areaBean.site_id
//            goodFilter.goods_id=areaBean.goods_id
//            shopFilter.shop_id = areaBean.shop_id
//            shopFilter.zdid = areaBean.site_id
//            shopFilter.spm=areaBean.spm
//
//        }
//        if(goodBean!=null){
//            goodFilter.spm=goodBean.spm
//            goodFilter.zdid=goodBean.site_id
//            goodFilter.goods_id=goodBean.goods_id
//            shopFilter.shop_id = goodBean.shop_id
//            shopFilter.zdid = goodBean.site_id
//            shopFilter.spm=goodBean.spm
//        }

    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)
        fab.hide()
        rv_detail.layoutManager= LinearLayoutManager(this)
        rv_detail.adapter = mAdapter
        toolbar.bringToFront()

        rv_detail.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
               val  firstItemPosition =(recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
                val lastItemPosition =(recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition();
                val totalCount =recyclerView.layoutManager.itemCount;
                if(firstItemPosition<3||dy>0 ){
                    fab.hide();
                }else {
                    fab.show();
                }
                //      坑
//      快速滑动时，会因为先滑到底部，然后图片再加载，导致recyclerview长度于滑动距离不一致。
//          这里加上判断，只有在第一个item时计算滑动距离
//                但是这样快速滑动也会出现mdy为负的现象
//                所以在调用setToolbarTansparent时判断，如果mdy<0时，mdy归零；
                if(firstItemPosition==0){
                    mdy+=dy;
                    setToolbarTansparent(mdy,MyUtil.dp2px(mContext,250).toFloat());
//                    Log.e(TAG,"mdy:"+mdy);
                }
            }
        })
        fab.setOnClickListener { rv_detail.smoothScrollToPosition(0) }
    }

    override fun start() {
        mPresenter.loadImgs(filter);
        mPresenter.loadGoodDetail(filter);
        mPresenter.getShopDetail(filter);
    }

    override fun showResult(goodBean: GoodBean) {
        this.mGoodBean = goodBean;
        toolbar_title.setText(goodBean.title);
        mAdapter.goodBean=goodBean;
        mAdapter.notifyDataSetChanged();
    }

    override fun showImgs(imgList: List<String>) {
        mAdapter.imgList=imgList as ArrayList<String>
        mAdapter.notifyDataSetChanged();
    }

    override fun showShop(shopBean: ShopBean) {
        mAdapter.shopBean=shopBean
        mAdapter.notifyDataSetChanged();
    }

    override fun showError(msg:String,errorCode:Int){

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    private fun setToolbarTansparent(dy: Int, limit: Float) {
        var dy = dy
        if (dy < 0) {
            mdy = 0
            dy = 0
        }
        if (dy <= limit && dy >= 0) {
            val fraction = (dy * 255 / limit).toInt()
            val alpha = dy / limit
            //            String alpha=Integer.toHexString(255*dy/limit);
            //            if(alpha.length()==1){
            //                alpha="0"+alpha;
            //            }
            //            Log.e(TAG,"#"+alpha+"ffffff");
            //            mToolbar.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(alpha)+"ffffff"));
            toolbar.getBackground().mutate().setAlpha(fraction)
            toolbar_title.setAlpha(alpha)
        } else if (dy > limit) {
            toolbar.getBackground().mutate().setAlpha(255)
        }
    }
}