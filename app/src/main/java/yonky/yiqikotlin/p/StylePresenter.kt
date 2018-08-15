package yonky.yiqikotlin.p

import yonky.yiqikotlin.base.BasePresenter
import yonky.yiqikotlin.base.contract.StyleContract
import yonky.yiqikotlin.bean.GoodFilterBean
import yonky.yiqikotlin.m.DataManager

/**
 * Created by Administrator on 2018/8/15.
 */
class StylePresenter:BasePresenter<StyleContract.View>(),StyleContract.Presenter{
    val mDataManager by lazy{
        DataManager()
    }

    override fun loadDatas(filter: GoodFilterBean, isLoadingMore: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGoodColor(type: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}