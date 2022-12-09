package com.tods.project_pokemon.data.model.list.results

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultsModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Serializable
