package com.jaegerapps.repositorypatterndemo.data.remote

import com.jaegerapps.repositorypatterndemo.data.dto.PhotoDto
import retrofit2.http.GET

interface CoffeeApi {
    @GET("random.json")
    suspend fun getCoffee(): PhotoDto
}