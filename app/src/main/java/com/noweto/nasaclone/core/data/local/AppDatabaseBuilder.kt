package com.noweto.nasaclone.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.noweto.nasaclone.ui.home.model.NewsModelItem

@Database(entities = [NewsModelItem::class] ,version = 1,exportSchema = false)
abstract class AppDatabaseBuilder : RoomDatabase() {

    companion object{

        private var db : AppDatabaseBuilder ?= null

        fun provideRoomDb(context: Context):AppDatabaseBuilder =
            db?: synchronized(this){
                db?: buildDataBase(context).also { db = it }
            }
        private fun buildDataBase(context: Context)  =
            Room.databaseBuilder(context,AppDatabaseBuilder::class.java,"nasaDB")
                .fallbackToDestructiveMigration()
                .build()


    }

    abstract fun getNews():NewsDao




}