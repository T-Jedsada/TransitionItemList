package com.jedsada.transitionitemlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jedsada.transitionitemlist.adapter.MovieAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var movieAdapter: MovieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movies = JsonUtil().getJsonToMock("movies.json", MovieDao::class.java)
        Log.d("POND", "result $movies")

        list?.setupLet(movieAdapter)

        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movieAdapter.setData(movies) }
    }
}