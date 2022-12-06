package com.tods.project_pokemon.data.model.types.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("pokemon")
    val pokemon: PokemonNameModel
)
