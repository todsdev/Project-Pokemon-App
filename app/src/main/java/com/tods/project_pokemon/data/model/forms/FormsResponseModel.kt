package com.tods.project_pokemon.data.model.forms

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.forms.sprites.SpritesFormsModel
import com.tods.project_pokemon.data.model.forms.types.TypesFormsModel
import java.io.Serializable

data class FormsResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: SpritesFormsModel,
    @SerializedName("forms")
    val types: List<TypesFormsModel>,
): Serializable