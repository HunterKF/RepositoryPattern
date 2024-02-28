package com.jaegerapps.repositorypatterndemo.domain.repo

import com.jaegerapps.repositorypatterndemo.domain.util.Resource

interface PhotosRepo {
    suspend fun getPhotos(): Resource<String>
}