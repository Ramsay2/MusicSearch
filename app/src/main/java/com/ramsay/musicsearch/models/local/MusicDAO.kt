package com.ramsay.musicsearch.models.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MusicDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMusic(result: ArrayList<Music>)

    @Query("select * from musics where artistName = :search")
    fun searchMusic(search :String) : LiveData<List<Music>>

    @Query("select * from musics ")
    fun getMusic() : LiveData<List<Music>>

    @Query("delete from musics")
    fun deleteAll()

}