package com.tods.project_pokemon.data.model.types.damagerelations

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.types.damagerelations.doubledamage.DoubleDamageFromModel
import com.tods.project_pokemon.data.model.types.damagerelations.doubledamage.DoubleDamageToModel
import com.tods.project_pokemon.data.model.types.damagerelations.halfdamage.HalfDamageFromModel
import com.tods.project_pokemon.data.model.types.damagerelations.halfdamage.HalfDamageToModel
import com.tods.project_pokemon.data.model.types.damagerelations.nodamage.NoDamageFromModel
import com.tods.project_pokemon.data.model.types.damagerelations.nodamage.NoDamageToModel

data class DamageRelationsModel(
    @SerializedName("no_damage_to")
    val no_damage_to: List<NoDamageToModel>,
    @SerializedName("half_damage_to")
    val half_damage_to: List<HalfDamageToModel>,
    @SerializedName("double_damage_to")
    val double_damage_to: List<DoubleDamageToModel>,
    @SerializedName("no_damage_from")
    val no_damage_from: List<NoDamageFromModel>,
    @SerializedName("half_damage_from")
    val half_damage_from: List<HalfDamageFromModel>,
    @SerializedName("double_damage_from")
    val double_damage_from: List<DoubleDamageFromModel>,
)
