package com.tods.project_pokemon.data.model.abilities.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonAbilityModel(
    @SerializedName("pokemon")
    val pokemon: PokemonAbilityInnerModel
)
