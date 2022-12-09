package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tods.project_pokemon.data.model.moves.learnedby.LearnedByPokemonModel
import com.tods.project_pokemon.databinding.ItemPokemonNameBinding
import java.util.*

class PokemonNameAdapter(): ListAdapter<LearnedByPokemonModel, PokemonNameAdapter.PokemonNameViewHolder>(ItemDiff()) {

    inner class PokemonNameViewHolder(val binding: ItemPokemonNameBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var pokemons: List<LearnedByPokemonModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonNameViewHolder {
        return PokemonNameViewHolder(
            ItemPokemonNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonNameViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.apply {
            textPokemon.text = pokemon.name.replaceFirstChar {
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

    class ItemDiff : DiffUtil.ItemCallback<LearnedByPokemonModel>() {
        override fun areItemsTheSame(oldItem: LearnedByPokemonModel, newItem: LearnedByPokemonModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LearnedByPokemonModel, newItem: LearnedByPokemonModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private var onItemClickListener: ((LearnedByPokemonModel) -> Unit)? = null

    fun setOnClickListener(listener: (LearnedByPokemonModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = pokemons.size
}