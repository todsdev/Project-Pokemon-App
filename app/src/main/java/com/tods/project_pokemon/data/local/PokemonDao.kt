package com.tods.project_pokemon.data.local

import androidx.room.*
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonResponseModel): Long

    @Delete
    suspend fun delete(pokemon: PokemonResponseModel)

    @Query("SELECT * FROM pokemon ORDER BY id")
    fun getAll(): Flow<List<PokemonResponseModel>>
}