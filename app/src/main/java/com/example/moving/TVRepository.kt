package com.example.moving

import com.example.moving.common.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TVRepository {
    private val api: Api //인터페이스 구현

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getPopularTV(page: Int = 1,
                     onSuccess: (tvlist: List<TV>) -> Unit,
                     onError: () -> Unit ) {
        TVRepository.api.getPopularTV(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(
                    call: Call<GetTVResponse>,
                    response: Response<GetTVResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvlist)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getTopRatedTV(page: Int = 1,
                      onSuccess: (tvlist: List<TV>) -> Unit,
                      onError: () -> Unit){
        api.getTopRatedTV(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(
                    call: Call<GetTVResponse>,
                    response: Response<GetTVResponse>
                ) {
                    if(response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null) {
                            onSuccess.invoke(responseBody.tvlist)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getOnTheAirTV(page: Int = 1,
                      onSuccess: (tvlist: List<TV>) -> Unit,
                      onError: () -> Unit){
        api.getOnTheAirTV(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(
                    call: Call<GetTVResponse>,
                    response: Response<GetTVResponse>
                ) {
                    if(response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null) {
                            onSuccess.invoke(responseBody.tvlist)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getAiringTodayTV(page: Int = 1,
                         onSuccess: (tvlist: List<TV>) -> Unit,
                         onError: () -> Unit){
        api.getAiringTodayTV(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(
                    call: Call<GetTVResponse>,
                    response: Response<GetTVResponse>
                ) {
                    if(response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null) {
                            onSuccess.invoke(responseBody.tvlist)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getDiscoverTV(page: Int = 1,
                      onSuccess: (tvlist: List<TV>) -> Unit,
                      onError: () -> Unit){
        api.getDiscoverTV(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(
                    call: Call<GetTVResponse>,
                    response: Response<GetTVResponse>
                ) {
                    if(response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null) {
                            onSuccess.invoke(responseBody.tvlist)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}