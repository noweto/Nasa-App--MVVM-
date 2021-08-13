package com.noweto.nasaclone.ui.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.noweto.nasaclone.R
import com.noweto.nasaclone.core.utils.BusinessConst
import com.noweto.nasaclone.core.utils.Resource
import com.noweto.nasaclone.ui.home.adapter.NewsAdapter
import com.noweto.nasaclone.ui.home.model.NewsModelItem
import com.noweto.nasaclone.ui.home.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

//~~ Fragment handle tab layout  layout

@AndroidEntryPoint
class HomeFragment : Fragment() ,NewsAdapter.OnItemClick{

    private val adapter : NewsAdapter = NewsAdapter(this)
    private val newsViewModel : NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNav()
        newsRv.layoutManager = LinearLayoutManager(requireContext())
        setUpObserver()


    }

    private fun setUpObserver() {

        newsViewModel.latestNews.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.REMOTE_SUCCESS->{
                    Log.e("Status"," "+it.status+it.data)
                }
                Resource.Status.LOCAL_SUCCESS->{
                    Log.e("Status"," "+it.status+it.data)
                    adapter.setData(it.data as ArrayList<NewsModelItem>)
                    newsRv.adapter = adapter
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

    private fun  showNav(){
        val v : View? = activity?.findViewById(R.id.bottom_nav)
        if (v != null&&v.visibility== View.GONE) {
            v.visibility = View.VISIBLE
        }
    }




    override fun onOpenDetailsClicked(model: NewsModelItem) {
        findNavController().navigate(R.id.from_home_to_details, bundleOf(BusinessConst.NEWS_DETAILS to model))
    }




}