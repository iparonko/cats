package com.example.cats_list_impl.data.models

import com.google.gson.annotations.SerializedName

data class CatDTO(
    @SerializedName("id")
    val id: String?,

    @SerializedName("url")
    val url: String?
)
