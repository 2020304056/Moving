package com.example.moving.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moving.MainActivity
import com.example.moving.MoviesAdapter
import com.example.moving.R
import com.example.moving.common.MoviesRepository
import com.example.moving.common.Movie
import com.example.moving.detail.MovieDetailsActivity

class MovieFragment : Fragment() {
    lateinit var root: View

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var popularMoviesPage = 1

    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MoviesAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topRatedMoviesPage = 1

    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: MoviesAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager
    private var upcomingMoviesPage = 1

    private lateinit var nowplayingMovies: RecyclerView
    private lateinit var nowplayingMoviesAdapter: MoviesAdapter
    private lateinit var nowplayingMoviesLayoutMgr: LinearLayoutManager
    private var nowplayingMoviesPage = 1

    private lateinit var discoverMovies: RecyclerView
    private lateinit var discoverMoviesAdapter: MoviesAdapter
    private lateinit var discoverMoviesLayoutMgr: LinearLayoutManager
    private var discoverMoviesPage = 1

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {
        root = inflater.inflate(R.layout.fragment_movie, container, false)

        popularMovies = root.findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter

        getPopularMovies()


        topRatedMovies = root.findViewById(R.id.top_rated_movies)
        topRatedMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        topRatedMovies.adapter = topRatedMoviesAdapter

        getTopRatedMovies()


        upcomingMovies = root.findViewById(R.id.upcoming_movies)
        upcomingMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingMovies.layoutManager = upcomingMoviesLayoutMgr
        upcomingMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        upcomingMovies.adapter = upcomingMoviesAdapter

        getUpcomingMovies()


        nowplayingMovies = root.findViewById(R.id.nowplaying_movies)
        nowplayingMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        nowplayingMovies.layoutManager = nowplayingMoviesLayoutMgr
        nowplayingMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        nowplayingMovies.adapter = nowplayingMoviesAdapter

        getNowplayingMovies()


        discoverMovies = root.findViewById(R.id.discover_movies)
        discoverMoviesLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        discoverMovies.layoutManager = discoverMoviesLayoutMgr
        discoverMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        discoverMovies.adapter = discoverMoviesAdapter

        getDiscoverMovies()


        return root
    }

    private fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }

    private fun attachPopularMoviesOnScrollListener() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }


    private fun getTopRatedMovies() {
        MoviesRepository.getTopRatedMovies(
            topRatedMoviesPage,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }

    private fun attachTopRatedMoviesOnScrollListener() {
        topRatedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedMoviesLayoutMgr.itemCount
                val visibleItemCount = topRatedMoviesLayoutMgr.childCount
                val firstVisibleItem = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedMovies.removeOnScrollListener(this)
                    topRatedMoviesPage++
                    getTopRatedMovies()
                }
            }
        })
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
        topRatedMoviesAdapter.appendMovies(movies)
        attachTopRatedMoviesOnScrollListener()
    }


    private fun getUpcomingMovies() {
        MoviesRepository.getUpcomingMovies(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }

    private fun attachUpcomingMoviesOnScrollListener() {
        upcomingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = upcomingMoviesLayoutMgr.itemCount
                val visibleItemCount = upcomingMoviesLayoutMgr.childCount
                val firstVisibleItem = upcomingMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcomingMovies.removeOnScrollListener(this)
                    upcomingMoviesPage++
                    getUpcomingMovies()
                }
            }
        })
    }

    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
        upcomingMoviesAdapter.appendMovies(movies)
        attachUpcomingMoviesOnScrollListener()
    }


    private fun getNowplayingMovies() {
        MoviesRepository.getNowPlayingMovies(
            nowplayingMoviesPage,
            ::onNowplayingMoviesFetched,
            ::onError
        )
    }

    private fun attachNowplayingMoviesOnScrollListener() {
        nowplayingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = nowplayingMoviesLayoutMgr.itemCount
                val visibleItemCount = nowplayingMoviesLayoutMgr.childCount
                val firstVisibleItem = nowplayingMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    nowplayingMovies.removeOnScrollListener(this)
                    nowplayingMoviesPage++
                    getNowplayingMovies()
                }
            }
        })
    }

    private fun onNowplayingMoviesFetched(movies: List<Movie>) {
        nowplayingMoviesAdapter.appendMovies(movies)
        attachNowplayingMoviesOnScrollListener()
    }


    private fun getDiscoverMovies() {
        MoviesRepository.getDiscoverMovies(
            discoverMoviesPage,
            ::onDiscoverMoviesFetched,
            ::onError
        )
    }

    private fun attachDiscoverMoviesOnScrollListener() {
        discoverMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = discoverMoviesLayoutMgr.itemCount
                val visibleItemCount = discoverMoviesLayoutMgr.childCount
                val firstVisibleItem = discoverMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    discoverMovies.removeOnScrollListener(this)
                    discoverMoviesPage++
                    getDiscoverMovies()
                }
            }
        })
    }

    private fun onDiscoverMoviesFetched(movies: List<Movie>) {
        discoverMoviesAdapter.appendMovies(movies)
        attachDiscoverMoviesOnScrollListener()
    }


    private fun onError() {
        Toast.makeText(activity, "error Movies", Toast.LENGTH_SHORT).show()
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
}