package com.jedsada.transitionitemlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.jedsada.transitionitemlist.MovieDao
import com.jedsada.transitionitemlist.loadImage
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBindData(data: MovieDao.ResultDetail?) {
        itemView.img loadImage "http://image.tmdb.org/t/p/w780/${data?.backdropPath}"
    }
}