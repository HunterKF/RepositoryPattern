package com.jaegerapps.repositorypatterndemo.data.repo

import com.jaegerapps.repositorypatterndemo.data.remote.PizzaApi
import com.jaegerapps.repositorypatterndemo.domain.repo.PhotosRepo
import com.jaegerapps.repositorypatterndemo.domain.util.Resource
import javax.inject.Inject

class PhotosRepoImpl @Inject constructor(
    private val api: PizzaApi,
) : PhotosRepo {
    override suspend fun getPhotos(): Resource<String> {
       return try {
           Resource.Success(api.getCoffee().photoUrl)
       } catch (e: Exception) {
           e.printStackTrace()
           Resource.Error(e)
       }
    }
}