package com.tods.project_pokemon.data.model.list.results

import com.google.gson.annotations.SerializedName

data class ResultsModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
