package com.noweto.nasaclone.core.data.local

import com.noweto.nasaclone.ui.home.model.NewsModelItem
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor (private val newsDao: NewsDao){

    fun getNasaNewsList() = newsDao.getLatestNews()

    fun insertIntoNasaList(list:ArrayList<NewsModelItem>) = newsDao.insertIntoNasaNewsList(list)

    fun deleteNasaNewsList () = newsDao.deleteNasaNewsList()



}