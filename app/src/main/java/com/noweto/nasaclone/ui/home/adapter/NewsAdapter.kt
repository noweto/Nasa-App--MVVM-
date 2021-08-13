package com.noweto.nasaclone.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noweto.nasaclone.R
import com.noweto.nasaclone.ui.home.model.NewsModelItem
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter ( private val onItemClick: OnItemClick) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList : ArrayList<NewsModelItem> = arrayListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(list:ArrayList<NewsModelItem>){
        newsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(newsList[position])

        //~~ on item click

        holder.itemView.open_details.setOnClickListener {
            onItemClick.onOpenDetailsClicked(newsList[position])
        }



    }

    override fun getItemCount(): Int = newsList.size

    class NewsViewHolder(v:View): RecyclerView.ViewHolder(v){
        private var newsTitle : TextView = v.findViewById(R.id.newsTitle)
        private var newsDate : TextView = v.findViewById(R.id.newsDate)
        private var newsImage : ImageView = v.findViewById(R.id.newsImage)
        private var openDetails : ImageView = v.findViewById(R.id.open_details)

        fun bindData(model: NewsModelItem){

            Glide.with(newsImage.context).load(model.url).into(newsImage)
            newsTitle.text = model.title
            newsDate.text = model.date


        }

    }





    interface OnItemClick{
        fun onOpenDetailsClicked(model: NewsModelItem)
    }



}