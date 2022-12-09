package com.tods.project_pokemon.repository

import com.tods.project_pokemon.data.local.PokemonDao
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.data.remote.PokemonAPI
import com.tods.project_pokemon.util.Constants
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonAPI,
    private val dao: PokemonDao
) {

    suspend fun list() = api.list(Constants.MAX_RESULTS)
    suspend fun recoverPokemonById(id: Int) = api.recoverPokemonById(id)
    suspend fun recoverPokemonByName(name: String) = api.recoverPokemonByName(name)
    suspend fun recoverMoveByName(name: String) = api.recoverMoveByName(name)
    suspend fun recoverInfoByTypeName(name: String) = api.recoverInfoByTypeName(name)
    suspend fun insert(pokemon: PokemonResponseModel) = dao.insert(pokemon)
    suspend fun delete(pokemon: PokemonResponseModel) = dao.delete(pokemon)
    fun getAll() = dao.getAll()
}