package com.ramsay.musicsearch.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ramsay.musicsearch.models.ApiService
import com.ramsay.musicsearch.models.Network
import com.ramsay.musicsearch.models.local.Music
import com.ramsay.musicsearch.models.local.MusicDAO
import com.ramsay.musicsearch.models.response.ResponseDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(val musicDAO: MusicDAO) {

    private var apiService: ApiService = Network.instance().create(ApiService::class.java)

    fun getMusicFromApi(search :String) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getSong(search)
            Log.d("TAG123", response.toString())
            saveToDataBase(response)
        }
    }

    fun saveToDataBase(result: ResponseDTO) {
        val musicList = ArrayList<Music>()
        result.results.forEach {
            val music = Music(
                it.artistId,
                it.artistName,
                it.artistViewUrl,
                it.artworkUrl100,
                it.artworkUrl30,
                it.artworkUrl60,
                it.collectionArtistId,
                it.collectionArtistName,
                it.collectionCensoredName,
                it.collectionExplicitness,
                it.collectionId,
                it.collectionName,
                it.collectionPrice,
                it.collectionViewUrl,
                it.contentAdvisoryRating,
                it.copyright,
                it.country,
                it.currency,
                it.description,
                it.discCount,
                it.discNumber,
                it.isStreamable,
                it.kind,
                it.previewUrl,
                it.primaryGenreName,
                it.releaseDate,
                it.trackCensoredName,
                it.trackCount,
                it.trackExplicitness,
                it.trackId,
                it.trackName,
                it.trackNumber,
                it.trackPrice,
                it.trackTimeMillis,
                it.trackViewUrl,
                it.wrapperType
            )
            musicList.add(music)
        }
       // musicDAO.deleteAll()
        musicDAO.addMusic(musicList)
    }

    fun getAllMusic():LiveData<List<Music>>{
        return musicDAO.getMusic()
    }
}