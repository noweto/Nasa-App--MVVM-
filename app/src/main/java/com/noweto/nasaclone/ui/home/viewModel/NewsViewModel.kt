package com.noweto.nasaclone.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.noweto.nasaclone.core.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val latestNews = newsRepository.getLatestNews()


}