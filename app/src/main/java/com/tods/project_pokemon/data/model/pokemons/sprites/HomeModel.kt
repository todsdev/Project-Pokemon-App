package com.tods.project_pokemon.data.model.pokemons.sprites

import com.google.gson.annotations.SerializedName

data class HomeModel(
    @SerializedName("front_default")
    val front_default: String,
    @SerializedName("front_shiny")
    val front_shiny: String
)
