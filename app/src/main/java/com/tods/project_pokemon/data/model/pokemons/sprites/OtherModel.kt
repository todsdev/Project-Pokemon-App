package com.tods.project_pokemon.data.model.pokemons.sprites

import com.google.gson.annotations.SerializedName

data class OtherModel(
    @SerializedName("home")
    val home: HomeModel,
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtworkModel
)
