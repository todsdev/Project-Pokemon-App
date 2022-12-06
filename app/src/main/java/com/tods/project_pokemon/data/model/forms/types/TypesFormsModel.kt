package com.tods.project_pokemon.data.model.forms.types

import com.google.gson.annotations.SerializedName

data class TypesFormsModel(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeFormsModel
)
