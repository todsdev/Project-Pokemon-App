package com.tods.project_pokemon.data.model.evolutions.chain

import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.evolutions.chain.evolves_to.EvolvesToModel
import com.tods.project_pokemon.data.model.evolutions.chain.evolves_to.SpeciesEvolutionModel

data class ChainModel(
    @SerializedName("species")
    val species: SpeciesEvolutionModel,
    @SerializedName("evolves_to")
    val evolves_to: List<EvolvesToModel>
)
