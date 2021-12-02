package com.ramsay.musicsearch.models.local

import android.content.Context
import androidx.room.Room
import com.ramsay.musicsearch.models.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MusicModule {

    @Singleton
    @Provides
    fun provideAPIService(): ApiService {
        val builder  = Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): MusicRoomDataBase {

        val builder = Room.databaseBuilder(
            context,
            MusicRoomDataBase::class.java,
            "music_dataBase"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideCoinDAO(dataBase: MusicRoomDataBase): MusicDAO {
        return dataBase.getMusicDAO()
    }
}
