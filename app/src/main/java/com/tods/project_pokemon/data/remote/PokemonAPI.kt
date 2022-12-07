package com.tods.project_pokemon.data.remote

import com.tods.project_pokemon.data.model.evolutions.EvolutionsResponseModel
import com.tods.project_pokemon.data.model.forms.FormsResponseModel
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.moves.MovesResponseModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.data.model.types.TypesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

interface PokemonAPI {

    @GET("pokemon")
    suspend fun list(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<PokemonListResponseModel>

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

    @GET("pokemon/{id}")
    suspend fun recoverPokemonById(
        @Path(
            value = "id",
            encoded = true
        ) pokemonId: Int
    ): Response<PokemonResponseModel>

    @GET("type/{name}")
    suspend fun recoverTypesByName(
        @Path(
            value = "name",
            encoded = true
        ) pokemonName: String
    ): Response<TypesResponseModel>

    @GET("pokemon-form/{id}")
    suspend fun recoverPokemonFormsById(
        @Path(
            value = "id",
            encoded = true
        ) pokemonId: Int
    ): Response<FormsResponseModel>

    @GET("evolution-chain/{id}")
    suspend fun recoverEvolutionById(
        @Path(
            value = "id",
            encoded = true
        ) pokemonId: Int
    ): Response<EvolutionsResponseModel>
}