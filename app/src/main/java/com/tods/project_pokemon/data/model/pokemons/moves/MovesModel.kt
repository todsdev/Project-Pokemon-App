package com.tods.project_pokemon.data.model.pokemons.moves

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovesModel(
    @SerializedName("move")
    val move: MoveModel
): Serializable
