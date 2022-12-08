package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tods.project_pokemon.data.model.types.pokemon.PokemonModel
import com.tods.project_pokemon.databinding.ItemPokemonNameBinding
import java.util.*

class PokemonNameTypesAdapter(): ListAdapter<PokemonModel, PokemonNameTypesAdapter.PokemonNameTypesViewHolder>(ItemDiff()) {

    inner class PokemonNameTypesViewHolder(val binding: ItemPokemonNameBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var pokemons: List<PokemonModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonNameTypesViewHolder {
        return PokemonNameTypesViewHolder(
            ItemPokemonNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonNameTypesViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.apply {
            textPokemon.text = pokemon.pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(pokemon)
            }
        }
    }

    class ItemDiff : DiffUtil.ItemCallback<PokemonModel>() {
        override fun areItemsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
            return oldItem.pokemon.name == newItem.pokemon.name
        }

        override fun areContentsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
            return oldItem.pokemon.name == newItem.pokemon.name
        }
    }

    private var onItemClickListener: ((PokemonModel) -> Unit)? = null

    fun setOnClickListener(listener: (PokemonModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = pokemons.size
}