package com.tods.project_pokemon.data.model.pokemons

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.pokemons.abilities.AbilitiesModel
import com.tods.project_pokemon.data.model.pokemons.forms.FormsModel
import com.tods.project_pokemon.data.model.pokemons.moves.MovesModel
import com.tods.project_pokemon.data.model.pokemons.sprites.SpritesModel
import com.tods.project_pokemon.data.model.pokemons.types.TypesModel
import java.io.Serializable

data class PokemonResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("abilities")
    val abilities: List<AbilitiesModel>,
    @SerializedName("forms")
    val forms: List<FormsModel>,
    @SerializedName("moves")
    val moves: List<MovesModel>,
    @SerializedName("sprites")
    val sprites: SpritesModel,
    @SerializedName("types")
    val types: List<TypesModel>
): Serializable
