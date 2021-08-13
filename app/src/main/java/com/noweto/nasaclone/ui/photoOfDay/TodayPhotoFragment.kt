package com.noweto.nasaclone.ui.photoOfDay

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.noweto.nasaclone.R
import com.noweto.nasaclone.core.utils.BusinessConst
import com.noweto.nasaclone.core.utils.Resource
import com.noweto.nasaclone.ui.home.model.NewsModelItem
import com.noweto.nasaclone.ui.photoOfDay.viewModels.TodayPhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_today_photo.*

@AndroidEntryPoint
class TodayPhotoFragment : Fragment() {


    val viewModel : TodayPhotoViewModel by viewModels()
    lateinit var model :NewsModelItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        showNav()
    }

    private fun setUpObserver(){
        viewModel.todayItem.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.REMOTE_SUCCESS->{
                    Log.e("Status"," "+it.status+it.data)
                    model = it.data!!
                    setData(model)

                }
                Resource.Status.ERROR->{
                    Log.e("Status", " "+it.message)
                }
                Resource.Status.LOADING->{
                    Log.e("Status","Loading")
                }
            }


        })

    }

    private fun setData(model:NewsModelItem){

        Glide.with(itemImage.context)
            .load(model.hdurl)
            .placeholder(R.drawable.progress_bar)
            .into(itemImage)

        newsItemTitle.text = model.title

        //~~ To more details fragment ...
        itemImage.setOnClickListener {
            findNavController().navigate(R.id.from_todayPhoto_to_details, bundleOf(BusinessConst.NEWS_DETAILS to model))
        }

    }

    private fun  showNav(){
        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null&&v.visibility== View.GONE) {
            v.visibility = View.VISIBLE
        }
    }





}