package yonky.yiqikotlin.utils

import android.content.Context
import android.widget.ImageView
import yonky.yiqikotlin.base.App
import com.bumptech.glide.Glide
import yonky.yiqikotlin.R.drawable.default_icon
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader
import yonky.yiqikotlin.R


/**
 * Created by Administrator on 2018/7/9.
 */

class GlideUtil : ImageLoader() {
   override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imageView)
    }

    companion object {
        private val options = RequestOptions().placeholder(R.drawable.default_icon)

        fun loadImage( path: Any, imageView: ImageView) {

            Glide.with(App.instance as Context)
                    .load(path)
                    .apply(options)
                    .into(imageView)
        }

        fun loadRoundImage(path: Any, imageView: ImageView) {
            val option1 = RequestOptions().circleCrop()
                    .placeholder(R.drawable.default_icon)
            Glide.with(App.instance as Context)
                    .load(path)
                    .apply(option1)
                    .into(imageView)
        }
    }
}
