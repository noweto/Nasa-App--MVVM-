package com.noweto.nasaclone.ui.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

class NewsModel : ArrayList<NewsModelItem>()

const val NEWS_ID = 0
@Entity(tableName = "nasaNewsList")
data class NewsModelItem(
    val copyright: String?= null,
    val date: String?= null,
    val explanation: String?= null,
    val hdurl: String?= null,
    val media_type: String?= null,
    val service_version: String?= null,
    val title: String?= null,
    val url: String?= null,
    val saved :Boolean?=false
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = NEWS_ID
}



