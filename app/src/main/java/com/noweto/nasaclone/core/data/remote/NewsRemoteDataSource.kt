package com.noweto.nasaclone.core.data.remote

import com.noweto.nasaclone.core.utils.BusinessConst.API_KEY
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor (private val newsApiServices: NewsApiServices) :BaseRemoteDataSource() {

    suspend fun getLatestNews() = getResults { newsApiServices.getLatestNews(API_KEY,20) }

    suspend fun getPhotoOfDay() = getResults { newsApiServices.getPhotoOfDay(API_KEY,"2017-07-22") }






}