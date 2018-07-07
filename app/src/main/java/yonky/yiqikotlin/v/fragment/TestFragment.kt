package yonky.yiqikotlin.v.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yonky.yiqikotlin.R

/**
 * Created by Administrator on 2018/7/7.
 */
class TestFragment : Fragment(){
    var mTitle:String = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test,null)

    }
    companion object {
        fun getInstance(title :String):TestFragment{
            val fragment = TestFragment
            fragment.
        }
    }

}