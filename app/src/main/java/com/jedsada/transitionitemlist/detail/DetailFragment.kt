package com.jedsada.transitionitemlist.detail

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jedsada.transitionitemlist.R
import com.jedsada.transitionitemlist.ResultDetail
import com.jedsada.transitionitemlist.inflate
import com.jedsada.transitionitemlist.loadImage
import kotlinx.android.synthetic.main.detail_fragment.*
import org.parceler.Parcels

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(data: ResultDetail?, transitionName: String): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("data", Parcels.wrap(data))
            bundle.putString("img_name", transitionName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.detail_fragment, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = Parcels.unwrap<ResultDetail>(arguments.getParcelable("data"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_banner.transitionName = arguments.getString("img_name")
        }
        img_banner.loadImage("http://image.tmdb.org/t/p/w780/${data?.backdropPath}") {
            startPostponedEnterTransition()
        }
    }
}