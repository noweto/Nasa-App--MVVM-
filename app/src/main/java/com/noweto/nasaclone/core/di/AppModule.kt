package com.noweto.nasaclone.core.di

import android.content.Context
import com.noweto.nasaclone.core.data.local.AppDatabaseBuilder
import com.noweto.nasaclone.core.data.local.NewsDao
import com.noweto.nasaclone.core.data.local.NewsLocalDataSource
import com.noweto.nasaclone.core.data.remote.AppNetworkBuilder
import com.noweto.nasaclone.core.data.remote.NewsApiServices
import com.noweto.nasaclone.core.data.remote.NewsRemoteDataSource
import com.noweto.nasaclone.core.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitClient():Retrofit =
        AppNetworkBuilder().provideRetrofitClient()

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context) : AppDatabaseBuilder =
        AppDatabaseBuilder.provideRoomDb(context)

    @Provides
    fun provideNewsApiServices(retrofit: Retrofit):NewsApiServices=
        retrofit.create(NewsApiServices::class.java)

    @Provides
    fun provideNewsDao(appDatabaseBuilder: AppDatabaseBuilder):NewsDao =
        appDatabaseBuilder.getNews()

    @Provides
    fun provideNewsRemoteDs(newsApiServices: NewsApiServices)=
        NewsRemoteDataSource(newsApiServices)

    @Provides
    fun provideNewsLocalDs(newsDao: NewsDao) =
        NewsLocalDataSource(newsDao)

    @Provides
    fun provideNewsRepository(newsLocalDataSource: NewsLocalDataSource,newsRemoteDataSource: NewsRemoteDataSource)=
        NewsRepository(newsRemoteDataSource,newsLocalDataSource)


}