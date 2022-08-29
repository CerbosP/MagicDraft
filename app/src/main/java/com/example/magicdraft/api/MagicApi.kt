package com.example.magicdraft.api

import com.example.magicdraft.model.response.BoosterResponse
import com.example.magicdraft.model.response.Card
import com.example.magicdraft.model.response.SetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MagicApi {
    @GET("sets")
    suspend fun getSets(
    ): Response<SetResponse>

    @GET("sets/{code}/booster")
    suspend fun drawBooster(
        @Path("code") code: String
    ): Response<BoosterResponse>

    @GET("cards")
    suspend fun findCard(
        @Query("name") name: String
    ): Response<BoosterResponse>

}
