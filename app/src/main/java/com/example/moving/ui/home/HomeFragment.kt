package com.example.moving.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moving.MainActivity
import com.example.moving.R
import com.example.moving.TV
import com.example.moving.TVAdapter
import com.example.moving.TVRepository
import com.example.moving.databinding.FragmentHomeBinding
import com.example.moving.detail.MovieDetailsActivity

class HomeFragment : Fragment() {

    lateinit var root : View

    private lateinit var popularTV: RecyclerView
    private lateinit var popularTVAdapter: TVAdapter
    private lateinit var popularTVLayoutMgr: LinearLayoutManager
    private var popularTVPage = 1

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        root = inflater.inflate(R.layout.fragment_home, container, false)

        popularTV = root.findViewById(R.id.popular_tv)
        popularTVLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularTV.layoutManager = popularTVLayoutMgr
        popularTVAdapter = TVAdapter(mutableListOf()) { tv -> showTVDetails(tv) }
        popularTV.adapter = popularTVAdapter


        getPopularTV()

        return root
    }


    private fun getPopularTV() {
        TVRepository.getPopularTV(
            popularTVPage,
            ::onPopularTVFetched,
            ::onError
        )
    }

    private fun onPopularTVFetched(tvlist: List<TV>) {
        popularTVAdapter.appendTV(tvlist)
        attachPopularTVOnScrollListener()
    }

    private fun attachPopularTVOnScrollListener() {
        popularTV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularTVLayoutMgr.itemCount
                val visibleItemCount = popularTVLayoutMgr.childCount
                val firstVisibleItem = popularTVLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularTV.removeOnScrollListener(this)
                    popularTVPage++
                    getPopularTV()
                }
            }
        })
    }


    private fun onError() {
        Toast.makeText(activity, "error Movies", Toast.LENGTH_SHORT).show()
    }

    private fun showTVDetails(tv: TV) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(MainActivity.MOVIE_BACKDROP, tv.backdrop_path)
        intent.putExtra(MainActivity.MOVIE_POSTER, tv.poster_path)
        intent.putExtra(MainActivity.MOVIE_TITLE, tv.name)
        intent.putExtra(MainActivity.MOVIE_RATING, tv.rating)
        intent.putExtra(MainActivity.MOVIE_RELEASE_DATE, tv.first_air_date)
        intent.putExtra(MainActivity.MOVIE_OVERVIEW, tv.overview)
        startActivity(intent)
    }
}