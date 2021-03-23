package com.avans.assessment.models

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("first_brewed") val firstBrewed: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
)
