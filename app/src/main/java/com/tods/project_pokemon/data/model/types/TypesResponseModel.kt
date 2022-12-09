package com.tods.project_pokemon.data.model.types

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.types.damagerelations.DamageRelationsModel
import com.tods.project_pokemon.data.model.types.moves.MovesTypesModel
import com.tods.project_pokemon.data.model.types.pokemon.PokemonModel
import java.io.Serializable

data class TypesResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("damage_relations")
    val damage_relations: DamageRelationsModel,
    @SerializedName("pokemon")
    val pokemon: List<PokemonModel>,
    @SerializedName("moves")
    val moves: List<MovesTypesModel>
): Serializable
