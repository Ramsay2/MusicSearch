package com.ramsay.musicsearch.view

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramsay.musicsearch.databinding.ActivityMainBinding
import com.ramsay.musicsearch.models.local.Music
import com.ramsay.musicsearch.viewModels.MusicViewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var musicAdapter: MusicAdapter
    private val musicViewModels: MusicViewModels by viewModels()
    private var musicList = mutableListOf<Music>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()

        searchItem()

    }

    private fun setAdapter() {
        musicAdapter = MusicAdapter(this, musicList)
        Log.d("TAG567", musicList.toString())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = musicAdapter

    }

    private fun getMusic(search: String) {

            musicViewModels.getMusicFromApi(search)

            musicViewModels.getMusicFromDB(search).observe(this, Observer {
                val music = it
                musicList.clear()
                musicList.addAll(music)
                musicList.reverse()
                musicAdapter.notifyDataSetChanged()

            })

    }

    private fun searchItem() {
        binding.musicSearch.setOnQueryChangeListener { _, newQuery ->
            if (newQuery != null) {
                getMusic(newQuery)
            }
        }
    }

}