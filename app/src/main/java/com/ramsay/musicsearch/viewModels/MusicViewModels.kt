package com.ramsay.musicsearch.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ramsay.musicsearch.models.local.Music
import com.ramsay.musicsearch.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModels @Inject constructor(val repository: Repository):ViewModel() {

    fun getMusicFromApi(search :String){

        repository.getMusicFromApi(search)

    }

    fun  getMusicFromDB(search :String) :LiveData<List<Music>>{
        return repository.getAllMusic()
    }
}