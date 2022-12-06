package com.tods.project_pokemon.data.model.pokemons.sprites

import com.google.gson.annotations.SerializedName

data class SpritesModel(
    @SerializedName("front_default")
    val front_default: String,
    @SerializedName("back_default")
    val back_default: String,
    @SerializedName("front_shiny")
    val front_shiny: String,
    @SerializedName("back_shiny")
    val back_shiny: String,
    @SerializedName("other")
    val other: OtherModel
)