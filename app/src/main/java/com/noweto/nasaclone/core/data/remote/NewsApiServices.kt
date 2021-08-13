package com.noweto.nasaclone.core.data.remote

import com.noweto.nasaclone.ui.home.model.NewsModel
import com.noweto.nasaclone.ui.home.model.NewsModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    //~~ Fun to get latest news ...

    @GET("planetary/apod")
    suspend fun getLatestNews (@Query("api_key") apiKey:String , @Query ("count") count:Int)
    : Response<NewsModel>

    //~~ fun get photo of today [ Specific Day ] ..

    @GET("planetary/apod")
    suspend fun getPhotoOfDay(@Query("api_key" )apiKey:String ,@Query("date") date:String)
    :Response<NewsModelItem>




}