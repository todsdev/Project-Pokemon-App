package com.tods.project_pokemon.data.model.list

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import java.io.Serializable

data class PokemonListResponseModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<ResultsModel>
): Serializable