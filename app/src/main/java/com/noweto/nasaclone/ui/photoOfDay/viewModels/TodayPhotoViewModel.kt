package com.noweto.nasaclone.ui.photoOfDay.viewModels

import androidx.lifecycle.ViewModel
import com.noweto.nasaclone.core.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayPhotoViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel(){

    val todayItem = newsRepository.getPhotoOfDay()

}