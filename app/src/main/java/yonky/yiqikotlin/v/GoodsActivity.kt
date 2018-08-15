package yonky.yiqikotlin.v

import kotlinx.android.synthetic.main.activity_goods.*
import yonky.yiqikotlin.R
import yonky.yiqikotlin.base.BaseActivity
import yonky.yiqikotlin.base.contract.GoodContract
import yonky.yiqikotlin.p.GoodPresenter
import yonky.yiqikotlin.utils.StatusBarUtil
import yonky.yiqikotlin.v.adapter.StyleAdapter
import yonky.yiqikotlin.R.id.fab
import yonky.yiqikotlin.utils.MyUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.orhanobut.logger.Logger
import yonky.yiqikotlin.v.adapter.StyleAdapter.Companion.TYPE_NODATA
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.shop.*
import yonky.yiqikotlin.bean.*
import yonky.yiqikotlin.utils.GlideUtil






/**
 * Created by Administrator on 2018/7/16.
 */
class GoodsActivity:BaseActivity(), GoodContract.View{

    val mPresenter:GoodPresenter by lazy{ GoodPresenter(this) }

//    var mGoodList: List<GoodBean>? = null
    lateinit var mAdapter: StyleAdapter
    var isLoadingMore=false
    var shopBean:ShopBean?=null
    var mdy:Int=0
    lateinit var filter: Filter


    override fun getLayoutId(): Int= R.layout.activity_goods

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)
        toolbar.background.mutate().alpha=0
        fab.hide()


    }

    override fun start() {
        mPresenter.attachView(this)
        filter =intent.getSerializableExtra("filter") as Filter

//        mGoodList = ArrayList()
        mAdapter = StyleAdapter(this)


        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (mAdapter.getItemViewType(position) == TYPE_NODATA) {
                    2
                } else {
                    1
                }
            }
        }
        rv_goods.setLayoutManager(layoutManager)
        rv_goods.setAdapter(mAdapter)


        mPresenter.loadGoods(filter, false)
        mPresenter.loadShop(filter)


        rv_goods.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemPosition = (recyclerView!!.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                val lastItemPosition = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                val totalCount = recyclerView.layoutManager.itemCount
                if (firstItemPosition < 2 || dy > 0) {
                    fab.hide()
                } else {
                    fab.show()
                }

                //                最后一个显示时，加载更多
                if (lastItemPosition >= totalCount - 1 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true
                        val page = Integer.valueOf(filter.pindex) + 1
                        filter.pindex=page.toString()
                        mPresenter.loadGoods(filter, true)
                    }
                }

                mdy += dy
                setToolbarTansparent(mdy, MyUtil.dp2px(mContext, 250))
            }
        })
        fab.setOnClickListener{
              v ->  rv_goods.smoothScrollToPosition(0)
        }

    }

    override fun showResult(list: List<GoodBean>, loadingMore: Boolean) {
        Logger.d(list)
        if(loadingMore){
            isLoadingMore=false;
            mAdapter.beanList.addAll(list)
        }else{
            mAdapter.beanList=list as ArrayList<GoodBean>
        }

        mAdapter.notifyDataSetChanged();
    }

    override fun showShop(shopBean: ShopBean) {
        this.shopBean=shopBean
        GlideUtil.loadRoundImage(shopBean.serller_head_original!!, iv_avatar)
        tv_shop_name.setText(shopBean.shop_name)
//        mToolbar.setTitle(shopBean.getShop_name());
        tv_position.setText(shopBean.market + "-" + shopBean.floor+ "-" + shopBean.dangkou)
        val blurTransformation = BlurTransformation(15, 2)
//        MultiTransformation multi= new MultiTransformation(new BlurTransformation(15,2),new ColorFilterTransformation(R.color.light_gray));
        Glide.with(this).load(shopBean.serller_head_original)
                .apply(RequestOptions.bitmapTransform(blurTransformation))
                .into(iv_shop)

        titile.setText(shopBean.shop_name)
    }

    override fun showEmpty() {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {

    }

    override fun showError(msg: String, errorCode: Int) {

    }


//关于改变背景透明度，view.getBackground().setAlpha()，设置时会改变背景设置的资源文件的透明度。
//    这样导致使用到此资源文件的透明度都将会被改变。
//    如果想改变具体的view的背景，可以使用mustate()方法
//    即 view.getBackground().mustate().setAlpha()，这样就只是改变这个view的透明度


    private fun setToolbarTansparent(dy: Int, limit: Int) {
        if (dy < limit && dy >= 0) {
            val fraction = dy * 255 / limit
            val alpha = 255 * dy / limit

            toolbar.getBackground().mutate().alpha=alpha
            titile.alpha=fraction.toFloat()

        }
    }
}