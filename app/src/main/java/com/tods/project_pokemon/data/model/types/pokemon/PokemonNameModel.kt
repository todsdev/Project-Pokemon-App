package com.tods.project_pokemon.data.model.types.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonNameModel(
    @SerializedName("name")
    val name: String
)
