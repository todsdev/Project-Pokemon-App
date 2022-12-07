package com.tods.project_pokemon.data.model.moves.learned_by

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LearnedByPokemonModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Serializable
