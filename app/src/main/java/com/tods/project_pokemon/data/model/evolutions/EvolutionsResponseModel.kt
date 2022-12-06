package com.tods.project_pokemon.data.model.evolutions

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.evolutions.chain.ChainModel
import java.io.Serializable

data class EvolutionsResponseModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("chain")
    val chain: ChainModel
): Serializable
