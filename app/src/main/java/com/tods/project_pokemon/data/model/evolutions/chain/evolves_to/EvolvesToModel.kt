package com.tods.project_pokemon.data.model.evolutions.chain.evolves_to

import com.google.gson.annotations.SerializedName

data class EvolvesToModel(
    @SerializedName("species")
    val species: SpeciesModel
)
