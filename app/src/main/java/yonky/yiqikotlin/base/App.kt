package yonky.yiqikotlin.base

import android.app.Activity
import android.app.Application
import android.content.Context
import com.squareup.leakcanary.RefWatcher
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import yonky.yiqikotlin.BuildConfig
import kotlin.properties.Delegates


/**
 * Created by Administrator on 2018/7/5.
 */

class App : Application() {
    private var allActivities: MutableSet<Activity>? = null
    private var refWatcher: RefWatcher? = null


    companion object {
//        var context:Context by Delegates.notNull()
//            private set
        var instance: App? = null
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as App
            return myApplication.refWatcher
        }
    }


    override fun onCreate() {
        super.onCreate()
//        context =applicationContext
        refWatcher =setupLeakCanary()
        loggerConfig()

        instance = this
    }


    private fun setupLeakCanary():RefWatcher{
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        }else LeakCanary.install(this)
    }

    /**
     * LOGGER配置
     */
    private fun loggerConfig(){
//        val formatStrategy=PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(false)// 隐藏线程信息 默认：显示
//                .methodCount(0)// 决定打印多少行（每一行代表一个方法）默认：2
//                .tag("yonky")//日志标签
//                .build()
//        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy){
        Logger.addLogAdapter(object : AndroidLogAdapter(){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }

        })
    }


    fun addActivity(act: Activity) {
        if (allActivities == null) {
            allActivities = HashSet()
        }
        allActivities!!.add(act)
    }

    fun removeActivity(act: Activity) {
        if (allActivities != null) {
            allActivities!!.remove(act)
        }
    }

    fun exitApp() {

        if (allActivities != null) {
                for (act in allActivities!!) {
                    act.finish()
                }
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(0)
        }
    }


}
