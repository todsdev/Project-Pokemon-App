package com.tods.project_pokemon.data.model.pokemons.moves

import com.google.gson.annotations.SerializedName

data class MoveModel(
    @SerializedName("name")
    val name: String
)
