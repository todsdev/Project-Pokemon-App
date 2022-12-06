package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.databinding.ItemPokemonBinding
import com.tods.project_pokemon.util.Constants
import java.util.*

class PokemonAdapter(): ListAdapter<ResultsModel, PokemonAdapter.PokemonListViewHolder>(ItemDiff()) {

    inner class PokemonListViewHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var pokemons: List<ResultsModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.apply {
            textPokemonName.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            val url = pokemon.url.dropLast(1)
            val trimmedUrl = url.substring(url.lastIndexOf("/")+1).toInt()
            Picasso.get()
                .load("${Constants.BASE_IMAGE_URL}$trimmedUrl${Constants.END_IMAGE_URL}")
                .into(imagePokemon)
        }
    }

    override fun getItemCount(): Int = pokemons.size


    class ItemDiff : DiffUtil.ItemCallback<ResultsModel>() {
        override fun areItemsTheSame(oldItem: ResultsModel, newItem: ResultsModel): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ResultsModel, newItem: ResultsModel): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.url == newItem.url
        }
    }


    private var onItemClickListener: ((ResultsModel) -> Unit)? = null

    fun setOnClickListener(listener: (ResultsModel) -> Unit) {
        onItemClickListener = listener
    }

    fun getCharacterPosition(position: Int): ResultsModel {
        return pokemons[position]
    }
}