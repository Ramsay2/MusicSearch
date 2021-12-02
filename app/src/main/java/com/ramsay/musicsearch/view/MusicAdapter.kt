package com.ramsay.musicsearch.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramsay.musicsearch.R
import com.ramsay.musicsearch.databinding.ItemLayoutBinding
import com.ramsay.musicsearch.models.local.Music

class MusicAdapter(private val context: Context,private var musicList: MutableList<Music> ) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
      val layoutInflater = LayoutInflater.from(context)
        val binding:ItemLayoutBinding
        = DataBindingUtil.inflate(layoutInflater, R.layout.item_layout,parent,false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
       val music = musicList[position]
        Log.d("TAG56789", musicList.toString())
        holder.getData(music)
    }

    override fun getItemCount(): Int {

        return musicList.size

    }

    inner class MusicViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun getData(music: Music) {
            binding.music = music
            Glide.with(binding.ivArtImage)
                .load(music.artworkUrl100).into(binding.ivArtImage)
        }

    }

}