package com.ramsay.musicsearch.models

import com.ramsay.musicsearch.models.response.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search")
   suspend fun getSong(@Query("term") search :String) : ResponseDTO


}