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

    private lateinit var topRatedTV: RecyclerView
    private lateinit var topRatedTVAdapter: TVAdapter
    private lateinit var topRatedTVLayoutMgr: LinearLayoutManager
    private var topRatedTVPage = 1

    private lateinit var onTheAirTV: RecyclerView
    private lateinit var onTheAirTVAdapter: TVAdapter
    private lateinit var onTheAirTVLayoutMgr: LinearLayoutManager
    private var onTheAirTVPage = 1

    private lateinit var airingTodayTV: RecyclerView
    private lateinit var airingTodayTVAdapter: TVAdapter
    private lateinit var airingTodayTVLayoutMgr: LinearLayoutManager
    private var airingTodayTVPage = 1

    private lateinit var discoverTV: RecyclerView
    private lateinit var discoverTVAdapter: TVAdapter
    private lateinit var discoverTVLayoutMgr: LinearLayoutManager
    private var discoverTVPage = 1


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {

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


        topRatedTV = root.findViewById(R.id.top_rated_tv)
        topRatedTVLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedTV.layoutManager = topRatedTVLayoutMgr
        topRatedTVAdapter = TVAdapter(mutableListOf()) { tv -> showTVDetails(tv) }
        topRatedTV.adapter = topRatedTVAdapter

        getTopRatedTV()


        onTheAirTV = root.findViewById(R.id.on_the_air_tv)
        onTheAirTVLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        onTheAirTV.layoutManager = onTheAirTVLayoutMgr
        onTheAirTVAdapter = TVAdapter(mutableListOf()) { tv -> showTVDetails(tv) }
        onTheAirTV.adapter = onTheAirTVAdapter

        getOnTheAirTV()


        airingTodayTV = root.findViewById(R.id.airing_today_tv)
        airingTodayTVLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        airingTodayTV.layoutManager = airingTodayTVLayoutMgr
        airingTodayTVAdapter = TVAdapter(mutableListOf()) { tv -> showTVDetails(tv) }
        airingTodayTV.adapter = airingTodayTVAdapter

        getAiringTodayTV()


        discoverTV = root.findViewById(R.id.discover_tv)
        discoverTVLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        discoverTV.layoutManager = discoverTVLayoutMgr
        discoverTVAdapter = TVAdapter(mutableListOf()) { tv -> showTVDetails(tv) }
        discoverTV.adapter = discoverTVAdapter

        getDiscoverTV()


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

    private fun getTopRatedTV() {
        TVRepository.getTopRatedTV(
            topRatedTVPage,
            ::onTopRatedTVFetched,
            ::onError
        )
    }

    private fun onTopRatedTVFetched(tvlist: List<TV>) {
        topRatedTVAdapter.appendTV(tvlist)
        attachTopRatedTVOnScrollListener()
    }

    private fun attachTopRatedTVOnScrollListener() {
        topRatedTV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedTVLayoutMgr.itemCount
                val visibleItemCount = topRatedTVLayoutMgr.childCount
                val firstVisibleItem = topRatedTVLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedTV.removeOnScrollListener(this)
                    topRatedTVPage++
                    getTopRatedTV()
                }
            }
        })
    }

    private fun getOnTheAirTV() {
        TVRepository.getOnTheAirTV(
            onTheAirTVPage,
            ::onOnTheAirTVFetched,
            ::onError
        )
    }

    private fun onOnTheAirTVFetched(tvlist: List<TV>) {
        onTheAirTVAdapter.appendTV(tvlist)
        attachOnTheAirTVOnScrollListener()
    }

    private fun attachOnTheAirTVOnScrollListener() {
        onTheAirTV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onTheAirTVLayoutMgr.itemCount
                val visibleItemCount = onTheAirTVLayoutMgr.childCount
                val firstVisibleItem = onTheAirTVLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onTheAirTV.removeOnScrollListener(this)
                    onTheAirTVPage++
                    getOnTheAirTV()
                }
            }
        })
    }


    private fun getAiringTodayTV() {
        TVRepository.getAiringTodayTV(
            airingTodayTVPage,
            ::onAiringTodayTVFetched,
            ::onError
        )
    }

    private fun onAiringTodayTVFetched(tvlist: List<TV>) {
        airingTodayTVAdapter.appendTV(tvlist)
        attachAiringTodayTVOnScrollListener()
    }

    private fun attachAiringTodayTVOnScrollListener() {
        airingTodayTV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = airingTodayTVLayoutMgr.itemCount
                val visibleItemCount = airingTodayTVLayoutMgr.childCount
                val firstVisibleItem = airingTodayTVLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    airingTodayTV.removeOnScrollListener(this)
                    airingTodayTVPage++
                    getAiringTodayTV()
                }
            }
        })
    }

    private fun getDiscoverTV() {
        TVRepository.getDiscoverTV(
            discoverTVPage,
            ::onDiscoverTVFetched,
            ::onError
        )
    }

    private fun onDiscoverTVFetched(tvlist: List<TV>) {
        discoverTVAdapter.appendTV(tvlist)
        attachDiscoverTVOnScrollListener()
    }

    private fun attachDiscoverTVOnScrollListener() {
        discoverTV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = discoverTVLayoutMgr.itemCount
                val visibleItemCount = discoverTVLayoutMgr.childCount
                val firstVisibleItem = discoverTVLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    discoverTV.removeOnScrollListener(this)
                    discoverTVPage++
                    getDiscoverTV()
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