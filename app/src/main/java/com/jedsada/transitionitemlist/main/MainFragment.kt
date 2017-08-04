package com.jedsada.transitionitemlist.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jedsada.transitionitemlist.*
import com.jedsada.transitionitemlist.adapter.MovieAdapter
import com.jedsada.transitionitemlist.detail.DetailFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.concurrent.TimeUnit

class MainFragment : Fragment(), MovieAdapter.MovieAdapterListener {

    private var movieAdapter: MovieAdapter = MovieAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_main, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("POND", (savedInstanceState == null).toString())
        Log.d("POND", "created")
        val movies = JsonUtil().getJsonToMock("movies.json", MovieDao::class.java)

        list?.setupGridLayout(movieAdapter)
        movieAdapter.setListener(this)

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    pb?.let { it.hide() }
                    movieAdapter.setData(movies)
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun navigateToDetailItem(data: ResultDetail?, img: CustomOneOneImageView) {
        val animalDetailFragment = DetailFragment.newInstance(data, ViewCompat.getTransitionName(img))
//        fragmentManager.beginTransaction()
//                .addSharedElement(img, ViewCompat.getTransitionName(img))
//                .addToBackStack(DetailFragment::class.java.simpleName)
//                .replace(R.id.container, animalDetailFragment)
//                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }
}