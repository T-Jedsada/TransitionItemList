package com.jedsada.transitionitemlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jedsada.transitionitemlist.MovieDao
import com.jedsada.transitionitemlist.R
import com.jedsada.transitionitemlist.inflate

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var list = mutableListOf<MovieDao.ResultDetail>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder =
            MovieViewHolder(parent?.inflate(R.layout.movie_item, false))

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder?.onBindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(movies: MovieDao) {
        list = movies.result
        notifyDataSetChanged()
    }
}