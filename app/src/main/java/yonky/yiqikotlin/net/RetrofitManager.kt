package yonky.yiqikotlin.net

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yonky.yiqikotlin.base.App
import yonky.yiqikotlin.net.ApiService.Companion.HOST
import java.io.File
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS

/**
 * Created by Administrator on 2018/7/9.
 */
object RetrofitManager{
    var client:OkHttpClient? =null
    private var retrofit: Retrofit? =null

    val service:ApiService by lazy{
        getRetrofit()!!.create(ApiService::class.java)
    }


    private fun getRetrofit():Retrofit?{
        if(retrofit ==null){
            synchronized(RetrofitManager::class.java){
                if(retrofit==null){
                    //添加log拦截器，打印所有的log
                    val httpLoggingIntercepter=HttpLoggingInterceptor()
                    //可以设置请求过滤的水平，body,basic,headers
                    httpLoggingIntercepter.level=HttpLoggingInterceptor.Level.BODY
//
//                    //设置 请求的焕春大小跟位置
//                    val cacheFile = File((App as Context).cacheDir,"cache")
//                    val cache = Cache(cacheFile,1024*1024*50)//50M 缓存大小

                    client =OkHttpClient.Builder()
//                            .addInterceptor(addQueryParameterInterceptor())
//                            .addInterceptor(addHeaderIntercepter())
//                            .addInterceptor(addCacheInterceptor())
                            .addInterceptor(httpLoggingIntercepter)
//                            .cache(cache)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            .writeTimeout(20,TimeUnit.SECONDS)
                            .build()


                    //获取retrofit实例
                    retrofit =Retrofit.Builder()
                            .baseUrl(HOST)
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                }
            }
        }
        return retrofit
    }
}