package com.jaegerapps.repositorypatterndemo.data.dto

import com.squareup.moshi.Json

data class PhotoDto(
    @field:Json(name = "file")
    val photoUrl: String
)


