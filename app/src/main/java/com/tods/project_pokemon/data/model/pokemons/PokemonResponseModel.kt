package com.tods.project_pokemon.data.model.pokemons

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tods.project_pokemon.data.model.pokemons.abilities.AbilitiesModel
import com.tods.project_pokemon.data.model.pokemons.forms.FormsModel
import com.tods.project_pokemon.data.model.pokemons.moves.MovesModel
import com.tods.project_pokemon.data.model.pokemons.sprites.SpritesModel
import com.tods.project_pokemon.data.model.pokemons.stats.StatsModel
import com.tods.project_pokemon.data.model.pokemons.types.TypesModel
import java.io.Serializable

@Entity(tableName = "pokemon")
data class PokemonResponseModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @ColumnInfo
    @SerializedName("abilities")
    val abilities: List<AbilitiesModel>,
    @SerializedName("forms")
    val forms: List<FormsModel>,
    @SerializedName("moves")
    val moves: List<MovesModel>,
    @SerializedName("sprites")
    val sprites: SpritesModel,
    @SerializedName("stats")
    val stats: List<StatsModel>,
    @ColumnInfo
    @SerializedName("types")
    val types: List<TypesModel>,
): Serializable
