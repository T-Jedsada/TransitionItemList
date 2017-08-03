package com.jedsada.transitionitemlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.parceler.Parcels

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data = Parcels.unwrap<ResultDetail>(intent.getParcelableExtra("data"))
        img_cover loadImage "http://image.tmdb.org/t/p/w780/${data?.backdropPath}"
    }
}
