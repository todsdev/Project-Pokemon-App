package com.tods.project_pokemon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel


@Database(entities = [PokemonResponseModel::class], version = 1, exportSchema = false)
@TypeConverters(PokemonConverters::class)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}