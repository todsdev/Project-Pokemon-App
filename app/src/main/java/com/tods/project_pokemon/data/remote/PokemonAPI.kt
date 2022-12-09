package com.tods.project_pokemon.data.remote

import com.tods.project_pokemon.data.model.abilities.AbilityResponseModel
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
import com.tods.project_pokemon.data.model.moves.MovesResponseModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.data.model.types.TypesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    suspend fun list(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<PokemonListResponseModel>

    @GET("ability/{name}")
    suspend fun recoverAbilityByName(
        @Path(
            value = "name",
            encoded = true
        ) abilityName: String
    ): Response<AbilityResponseModel>

    @GET("type/{name}")
    suspend fun recoverInfoByTypeName(
        @Path(
            value = "name",
            encoded = true
        ) typeName: String
    ): Response<TypesResponseModel>

    @GET("move/{name}")
    suspend fun recoverMoveByName(
        @Path(
            value = "name",
            encoded = true
        ) moveName: String
    ): Response<MovesResponseModel>

    @GET("pokemon/{name}")
    suspend fun recoverPokemonByName(
        @Path(
            value = "name",
            encoded = true
        ) pokemonName: String
    ): Response<PokemonResponseModel>
}