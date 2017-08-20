package com.jedsada.transitionitemlist

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.GsonBuilder

class JsonUtil {
    fun <T> getJsonToMock(fileName: String, className: Class<T>): T =
            GsonBuilder().create().fromJson<T>(getJsonFromResources(fileName), className)

    private fun getJsonFromResources(fileName: String): String? =
            this.javaClass.classLoader.getResourceAsStream(fileName)?.bufferedReader().use { it?.readText() }
}

infix fun <T : RecyclerView.ViewHolder> RecyclerView.setupGridLayout(mAdapter: RecyclerView.Adapter<T>) = let {
    layoutManager = GridLayoutManager(context, 2)
    setHasFixedSize(false)
    adapter = mAdapter
}

infix fun RecyclerView.setupLinearLayout(mAdapter: RecyclerView.Adapter<*>) = also {
    it.layoutManager = LinearLayoutManager(context)
    it.setHasFixedSize(false)
    it.adapter = mAdapter
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean): View? = let {
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImage(url: String?, func: () -> Unit): Target<Drawable> = let {
    Glide.with(this).load(url).listener(object : RequestListener<Drawable> {
        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            func()
            return false
        }

        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            func()
            return false
        }
    }).into(it)
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}

fun View.hide() = let { this.visibility = View.GONE }

fun View.show() = let { this.visibility = View.VISIBLE }

inline fun <reified T : Activity> Activity.navigate(options: ActivityOptionsCompat?, func: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.func()
    startActivity(intent, options?.toBundle())
}