package com.tods.project_pokemon.data.model.abilities

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.abilities.effectentries.EffectEntriesAbilityModel
import com.tods.project_pokemon.data.model.abilities.pokemon.PokemonAbilityModel

data class AbilityResponseModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("effect_entries")
    val effect_entries: List<EffectEntriesAbilityModel>,
    @SerializedName("pokemon")
    val pokemon: List<PokemonAbilityModel>
)
