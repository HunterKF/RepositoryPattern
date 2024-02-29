package com.jaegerapps.repositorypatterndemo.data.remote

import com.jaegerapps.repositorypatterndemo.data.dto.PhotoDto
import retrofit2.http.GET

interface PizzaApi {
    @GET("api/images/pizza")
    suspend fun getCoffee(): PhotoDto
}
