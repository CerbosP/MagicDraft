package com.example.magicdraft.api

import com.example.magicdraft.model.response.BoosterResponse
import com.example.magicdraft.model.response.SetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MagicApi {
    @GET("sets")
    suspend fun getSets(
    ): Response<SetResponse>

    @GET("sets/{code}/booster")
    suspend fun drawBooster(
        @Path("code") code: String
    ): Response<BoosterResponse>
}
