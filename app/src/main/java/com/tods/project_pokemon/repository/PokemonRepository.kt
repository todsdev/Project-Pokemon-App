package com.tods.project_pokemon.repository

import com.tods.project_pokemon.data.remote.PokemonAPI
import com.tods.project_pokemon.util.Constants
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonAPI
) {

    suspend fun list() = api.list(Constants.MAX_RESULTS)
    suspend fun recoverPokemonById(id: Int) = api.recoverPokemonById(id)
    suspend fun recoverPokemonByName(name: String) = api.recoverPokemonByName(name)
    suspend fun recoverMoveByName(name: String) = api.recoverMoveByName(name)
}