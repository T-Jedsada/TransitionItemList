package com.jedsada.transitionitemlist.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jedsada.transitionitemlist.CustomOneOneImageView
import com.jedsada.transitionitemlist.R
import com.jedsada.transitionitemlist.ResultDetail
import com.jedsada.transitionitemlist.loadImage
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private var listener: MovieViewHolderListener? = null
    private var img: CustomOneOneImageView? = null

    interface MovieViewHolderListener {
        fun onMovieClick(position: Int, img: CustomOneOneImageView)
    }

    init {
        img = itemView?.findViewById(R.id.img)
    }

    fun setListener(listener: MovieViewHolderListener?) {
        this.listener = listener
    }

    fun onBindData(data: ResultDetail?) {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                listener?.onMovieClick(adapterPosition, img!!)
        }
        ViewCompat.setTransitionName(itemView.img, data?.title)
        itemView.img.loadImage("http://image.tmdb.org/t/p/w780/${data?.backdropPath}")
    }
}