package com.noweto.nasaclone.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noweto.nasaclone.ui.home.model.NewsModelItem
import retrofit2.http.GET

@Dao
interface NewsDao {

    //~~ get all from NASA News List  ..

    @Query("SELECT * FROM nasaNewsList")
    fun getLatestNews():LiveData<List<NewsModelItem>>

    //~~ insert into Nasa News List
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoNasaNewsList(newsList : ArrayList<NewsModelItem>)

    //~~ delete Nasa News List
    @Query("DELETE FROM nasaNewsList")
    fun deleteNasaNewsList()









}