package com.tods.project_pokemon.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.data.model.pokemons.abilities.AbilitiesModel
import com.tods.project_pokemon.data.model.pokemons.forms.FormsModel
import com.tods.project_pokemon.data.model.pokemons.moves.MovesModel
import com.tods.project_pokemon.data.model.pokemons.sprites.SpritesModel
import com.tods.project_pokemon.data.model.pokemons.stats.StatsModel
import com.tods.project_pokemon.data.model.pokemons.types.TypesModel

class PokemonConverters {

    @TypeConverter
    fun fromSprites(value: SpritesModel): String = Gson().toJson(value)

    @TypeConverter
    fun toSprites(value: String): SpritesModel = Gson().fromJson(value, SpritesModel::class.java)

    @TypeConverter
     fun fromAbilitiesList(value: List<AbilitiesModel>): String {
        return Gson().toJson(value)
     }

    @TypeConverter
    fun toAbilitiesList(value: String): List<AbilitiesModel> {
        return try {
            Gson().fromJson<List<AbilitiesModel>>(value, AbilitiesModel::class.java)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromFormsList(value: List<FormsModel>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toFormsList(value: String): List<FormsModel> {
        return try {
            Gson().fromJson<List<FormsModel>>(value, FormsModel::class.java)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromStatsList(value: List<StatsModel>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStatsList(value: String): List<StatsModel> {
        return try {
            Gson().fromJson<List<StatsModel>>(value, StatsModel::class.java)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromMovesList(value: List<MovesModel>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMovesList(value: String): List<MovesModel> {
        return try {
            Gson().fromJson<List<MovesModel>>(value, MovesModel::class.java)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromTypesList(value: List<TypesModel>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTypesList(value: String): List<TypesModel> {
        return try {
            Gson().fromJson<List<TypesModel>>(value, TypesModel::class.java)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}