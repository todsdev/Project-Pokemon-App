package com.tods.project_pokemon.data.model.pokemons.types

import com.google.gson.annotations.SerializedName

data class TypesModel(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeModel
)
