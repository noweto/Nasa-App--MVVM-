package com.noweto.nasaclone.core.data.repository

import com.noweto.nasaclone.core.data.local.NewsLocalDataSource
import com.noweto.nasaclone.core.data.remote.NewsRemoteDataSource
import com.noweto.nasaclone.core.utils.performGetNewsOperations
import com.noweto.nasaclone.core.utils.performGetToDayPhotoOperation
import javax.inject.Inject

class NewsRepository @Inject constructor (private val newsRemoteDataSource: NewsRemoteDataSource,
                        private val newsLocalDataSource: NewsLocalDataSource)
{


    //~~ get latest news ..

    fun getLatestNews () =
        performGetNewsOperations(
            dataBaseQuery = {newsLocalDataSource.getNasaNewsList()},
            networkCall = {newsRemoteDataSource.getLatestNews()},
            saveCallResults = {newsLocalDataSource.insertIntoNasaList(it)}
        )

    //~~ get photo of day ..
    fun getPhotoOfDay() = performGetToDayPhotoOperation { newsRemoteDataSource.getPhotoOfDay() }



}