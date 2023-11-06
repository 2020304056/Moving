package com.example.moving.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moving.MainActivity
import com.example.moving.MoviesAdapter
import com.example.moving.R
import com.example.moving.TV
import com.example.moving.TVAdapter
import com.example.moving.TVRepository
import com.example.moving.common.MoviesRepository
import com.example.moving.databinding.FragmentNotificationsBinding
import com.example.moving.detail.MovieDetailsActivity
import com.example.moving.common.Movie

class NotificationsFragment : Fragment() {
    lateinit var root : View

    var searchKeyword = ""

    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MoviesAdapter
    private lateinit var searchMoviesLayoutMgr: LinearLayoutManager
    private var searchMoviesPage = 1

    private lateinit var searchTV: RecyclerView
    private lateinit var searchTVAdapter: TVAdapter
    private lateinit var searchTVLayoutMgr: LinearLayoutManager
    private var searchTVPage = 1

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {

        root = inflater.inflate(R.layout.fragment_notifications, container, false)

        val searchButton = root.findViewById<Button>(R.id.bSearch)
        val searchInputField = root.findViewById<EditText>(R.id.eSearchWord)

        searchMovies = root.findViewById(R.id.search_movies)
        searchMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        searchMovies.layoutManager = searchMoviesLayoutMgr
        searchMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        searchMovies.adapter = searchMoviesAdapter

        searchTV = root.findViewById(R.id.search_tv)
        searchTVLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        searchTV.layoutManager = searchTVLayoutMgr
        searchTVAdapter = TVAdapter(mutableListOf()) { tv -> showTVDetails(tv) }
        searchTV.adapter = searchTVAdapter

        searchButton.setOnClickListener {
            searchKeyword = searchInputField.text.toString()

            if(searchKeyword == "") {
                Toast.makeText(activity, "input keyword", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, searchKeyword, Toast.LENGTH_SHORT).show()
                getSearchMovies()
                getPopularTV()
            }
        }

        return root
    }

    private fun removeData() {
        searchMovies.removeAllViews()
        searchTV.removeAllViews()

        searchMoviesAdapter.removeMovies(searchMoviesAdapter.movies)
        searchMoviesAdapter.notifyDataSetChanged()

        searchTVAdapter.removeTV(searchTVAdapter.tvlist)
        searchTVAdapter.notifyDataSetChanged()
    }

    private fun getSearchMovies() {
        MoviesRepository.getSearchMovies(
            searchMoviesPage,
            searchKeyword,
            ::onSearchMoviesFetched,
            ::onError
        )
    }

    private fun attachSearchMoviesOnScrollListener() {
        searchMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = searchMoviesLayoutMgr.itemCount
                val visibleItemCount = searchMoviesLayoutMgr.childCount
                val firstVisibleItem = searchMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    searchMovies.removeOnScrollListener(this)
                    searchMoviesPage++
                    getSearchMovies()
                }
            }
        })
    }

    private fun onSearchMoviesFetched(movies: List<Movie>) {
        searchMoviesAdapter.appendMovies(movies)
        attachSearchMoviesOnScrollListener()
    }


    private fun getPopularTV() {
        TVRepository.getSearchTV(
            searchTVPage,
            searchKeyword,
            ::onSearchTVFetched,
            ::onError
        )
    }
    private fun onSearchTVFetched(tvlist: List<TV>) {
        searchTVAdapter.appendTV(tvlist)
        attachSearchTVOnScrollListener()
    }


    private fun attachSearchTVOnScrollListener() {
        searchTV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = searchTVLayoutMgr.itemCount
                val visibleItemCount = searchTVLayoutMgr.childCount
                val firstVisibleItem = searchTVLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    searchTV.removeOnScrollListener(this)
                    searchTVPage++
                    getPopularTV()
                }
            }
        })
    }


    private fun onError() {
        Toast.makeText(activity, "error error", Toast.LENGTH_SHORT).show()
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(MainActivity.MOVIE_BACKDROP, movie.backdrop_path)
        intent.putExtra(MainActivity.MOVIE_POSTER, movie.poster_path)
        intent.putExtra(MainActivity.MOVIE_TITLE, movie.title)
        intent.putExtra(MainActivity.MOVIE_RATING, movie.rating)
        intent.putExtra(MainActivity.MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MainActivity.MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
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