package yonky.yiqikotlin.base

/**
 * Created by Administrator on 2018/7/7.
 */
interface IPresenter<in V:IBaseView>{

    fun attachView(mRootView:V)

    fun detachView()
}