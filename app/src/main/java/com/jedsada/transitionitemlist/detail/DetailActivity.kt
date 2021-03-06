package com.jedsada.transitionitemlist.detail

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jedsada.transitionitemlist.R
import com.jedsada.transitionitemlist.ResultDetail
import com.jedsada.transitionitemlist.loadImage
import kotlinx.android.synthetic.main.activity_detail.*
import org.parceler.Parcels

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportPostponeEnterTransition()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = Parcels.unwrap<ResultDetail>(intent.getParcelableExtra("data"))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = intent.getStringExtra("img")
            img_cover.transitionName = imageTransitionName
        }

        img_cover.loadImage("http://image.tmdb.org/t/p/w780/${data?.backdropPath}") {
            supportStartPostponedEnterTransition()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
