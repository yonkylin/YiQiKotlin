package yonky.yiqikotlin.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yonky.multiple_status_view.MultipleStatusView

/**
 * Created by Administrator on 2018/7/6.
 */
abstract class BaseFragment: Fragment(){
  lateinit var mContext:Context
    var isViewPrepare = false
    var hasLoadData = false
    var mLayoutStatusView: MultipleStatusView? = null
    override fun onAttach(context: Context) {

            mContext = context

        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare =true
        initView()
        lazyLoadDataIfPrepared()
        //多种状态切换的view 重试点击事件
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }


    open val mRetryClickListener =View.OnClickListener {
        lazyLoad()
    }
    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    open fun lazyLoadDataIfPrepared(){
        if(userVisibleHint && isViewPrepare &&!hasLoadData){
            lazyLoad()
            hasLoadData = true
        }
    }

    /**
     * 懒加载
     */
    abstract fun lazyLoad()
    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        App.getRefWatcher(activity  as Context)?.watch(activity)
    }


}