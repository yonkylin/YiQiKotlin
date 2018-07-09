package yonky.yiqikotlin.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Administrator on 2018/7/9.
 */
object RetrofitManager{
    var client:OkHttpClient? =null
    private var retrofit: Retrofit? =null

    val service:ApiService by lazy{

    }
}