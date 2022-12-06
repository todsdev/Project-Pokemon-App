package com.tods.project_pokemon.data.model.pokemons.abilities

import com.google.gson.annotations.SerializedName

data class AbilitiesModel(
    @SerializedName("is_hidden")
    val is_hidden: Boolean,
    @SerializedName("slots")
    val slots: Int,
    @SerializedName("ability")
    val ability: AbilityModel
)
