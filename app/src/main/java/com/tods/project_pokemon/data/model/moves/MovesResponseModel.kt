package com.tods.project_pokemon.data.model.moves

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.moves.damage_class.DamageClassModel
import com.tods.project_pokemon.data.model.moves.effect_entries.EffectEntriesModel
import com.tods.project_pokemon.data.model.moves.learned_by.LearnedByPokemonModel
import com.tods.project_pokemon.data.model.moves.target.TargetModel
import com.tods.project_pokemon.data.model.moves.type.TypeMovesModel
import dagger.multibindings.IntoMap
import java.io.Serializable

data class MovesResponseModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("accuracy")
    val accuracy: Int,
    @SerializedName("power")
    val power: Int,
    @SerializedName("pp")
    val pp: Int,
    @SerializedName("damage_class")
    val damage_class: DamageClassModel,
    @SerializedName("effect_entries")
    val effect_entries: List<EffectEntriesModel>,
    @SerializedName("target")
    val target: TargetModel,
    @SerializedName("type")
    val type: TypeMovesModel,
    @SerializedName("learned_by_pokemon")
    val learned_by_pokemon: List<LearnedByPokemonModel>
): Serializable