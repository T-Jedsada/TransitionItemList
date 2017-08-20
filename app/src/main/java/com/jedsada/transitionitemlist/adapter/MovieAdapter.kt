package com.jedsada.transitionitemlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jedsada.transitionitemlist.CustomOneOneImageView
import com.jedsada.transitionitemlist.R
import com.jedsada.transitionitemlist.ResultDetail
import com.jedsada.transitionitemlist.inflate
import kotlin.properties.Delegates

typealias MovieOnClick = (ResultDetail, CustomOneOneImageView) -> Unit

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var list: MutableList<ResultDetail> by Delegates.observable(mutableListOf(), { _, oldeValue, newValue ->
        notifyDataSetChanged()
    })

    var movieOnClick: MovieOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder =
            MovieViewHolder(parent?.inflate(R.layout.movie_item, false))

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder?.apply {
            onBindData(list[position])
            itemOnClick = { position, img ->
                movieOnClick?.invoke(list[position], img)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}