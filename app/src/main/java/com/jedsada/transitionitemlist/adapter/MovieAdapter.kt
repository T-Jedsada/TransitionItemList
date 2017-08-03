package com.jedsada.transitionitemlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jedsada.transitionitemlist.*

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>(), MovieViewHolder.MovieViewHolderListener {

    private var list = mutableListOf<ResultDetail>()
    private var listener: MovieAdapterListener? = null

    interface MovieAdapterListener {
        fun navigateToDetailItem(data: ResultDetail?, img: CustomOneOneImageView)
    }

    fun setData(movies: MovieDao) {
        list = movies.result
        notifyDataSetChanged()
    }

    fun setListener(listener: MovieAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder =
            MovieViewHolder(parent?.inflate(R.layout.movie_item, false))

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder?.setListener(this)
        holder?.onBindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun onMovieClick(position: Int, img: CustomOneOneImageView) {
        listener?.navigateToDetailItem(list[position], img)
    }
}