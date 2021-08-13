package com.noweto.nasaclone.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.noweto.nasaclone.R
import com.noweto.nasaclone.core.utils.BusinessConst.NEWS_DETAILS
import com.noweto.nasaclone.core.utils.Resource
import com.noweto.nasaclone.ui.home.model.NewsModelItem

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    lateinit var newsItem : NewsModelItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNav()
        retrieveData()

    }


    private fun retrieveData() {
        newsItem =arguments?.getSerializable(NEWS_DETAILS) as NewsModelItem

        Glide.with(requireContext()).load(newsItem.hdurl).into(itemImage)

        itemName.text = newsItem.title

        itemDesc.text = newsItem.explanation
        itemDate.text = newsItem.date


    }

    private fun hideBottomNav() {
        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null) {
            v.visibility = View.GONE
        }
    }

}