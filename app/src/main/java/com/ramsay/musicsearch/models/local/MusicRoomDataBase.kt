package com.ramsay.musicsearch.models.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Music::class],version = 2)

abstract class MusicRoomDataBase: RoomDatabase() {
    abstract fun getMusicDAO(): MusicDAO

    companion object{
        private var INSTANCE: MusicRoomDataBase? = null

        fun getDataBaseObject(context: Context): MusicRoomDataBase {
            return if(INSTANCE == null){

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    MusicRoomDataBase::class.java,
                    "coin_dataBase"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                INSTANCE!!
            }else
                INSTANCE!!
        }
    }

}