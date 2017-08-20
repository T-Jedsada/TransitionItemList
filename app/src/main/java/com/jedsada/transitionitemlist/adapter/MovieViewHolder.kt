package com.jedsada.transitionitemlist.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jedsada.transitionitemlist.CustomOneOneImageView
import com.jedsada.transitionitemlist.ResultDetail
import com.jedsada.transitionitemlist.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.android.synthetic.main.movie_item.view.*

typealias ItemOnClick = (Int, CustomOneOneImageView) -> Unit

class MovieViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var itemOnClick: ItemOnClick? = null

    fun onBindData(data: ResultDetail?) {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) itemOnClick?.invoke(adapterPosition, img)
        }
        ViewCompat.setTransitionName(itemView.img, data?.title)
        img.loadImage("http://image.tmdb.org/t/p/w780/${data?.backdropPath}")
    }
}