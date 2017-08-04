package com.jedsada.transitionitemlist.main

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import com.jedsada.transitionitemlist.*
import com.jedsada.transitionitemlist.adapter.MovieAdapter
import com.jedsada.transitionitemlist.detail.DetailActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import org.parceler.Parcels
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MovieAdapter.MovieAdapterListener {

    private var movieAdapter: MovieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movies = JsonUtil().getJsonToMock("movies.json", MovieDao::class.java)

        list?.setupGridLayout(movieAdapter)
        movieAdapter.setListener(this)

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    pb.hide()
                    movieAdapter.setData(movies)
                }
    }

    override fun navigateToDetailItem(data: ResultDetail?, img: CustomOneOneImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, img, ViewCompat.getTransitionName(img))
        navigate<DetailActivity>(options) {
            putExtra("data", Parcels.wrap(data))
            putExtra("img", ViewCompat.getTransitionName(img))
        }
    }
}