package com.jedsada.transitionitemlist

import android.graphics.drawable.Drawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.google.gson.GsonBuilder

class JsonUtil {
    fun <T> getJsonToMock(fileName: String, className: Class<T>): T =
            GsonBuilder().create().fromJson<T>(getJsonFromResources(fileName), className)

    private fun getJsonFromResources(fileName: String): String? =
            this.javaClass.classLoader.getResourceAsStream(fileName)?.bufferedReader().use { it?.readText() }
}

infix fun <T : RecyclerView.ViewHolder> RecyclerView.setupLet(mAdapter: RecyclerView.Adapter<T>) = let {
    layoutManager = GridLayoutManager(context, 2)
    setHasFixedSize(false)
    adapter = mAdapter
}

infix fun RecyclerView.setupAlso(mAdapter: RecyclerView.Adapter<*>) = also {
    it.layoutManager = GridLayoutManager(context, 2)
    it.setHasFixedSize(false)
    it.adapter = mAdapter
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean): View? = let {
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

infix fun ImageView.loadImage(url: String?): Target<Drawable> = let { Glide.with(this).load(url).into(it) }