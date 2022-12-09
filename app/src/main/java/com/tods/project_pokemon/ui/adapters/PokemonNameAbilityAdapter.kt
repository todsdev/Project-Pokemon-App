package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tods.project_pokemon.data.model.abilities.pokemon.PokemonAbilityModel
import com.tods.project_pokemon.data.model.moves.learnedby.LearnedByPokemonModel
import com.tods.project_pokemon.databinding.ItemPokemonNameBinding
import java.util.*

class PokemonNameAbilityAdapter(): ListAdapter<PokemonAbilityModel, PokemonNameAbilityAdapter.PokemonNameAbilityViewHolder>(ItemDiff()) {

    inner class PokemonNameAbilityViewHolder(val binding: ItemPokemonNameBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var pokemons: List<PokemonAbilityModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonNameAbilityViewHolder {
        return PokemonNameAbilityViewHolder(
            ItemPokemonNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonNameAbilityViewHolder, position: Int) {
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

    class ItemDiff : DiffUtil.ItemCallback<PokemonAbilityModel>() {
        override fun areItemsTheSame(oldItem: PokemonAbilityModel, newItem: PokemonAbilityModel): Boolean {
            return oldItem.pokemon.name == newItem.pokemon.name
        }

        override fun areContentsTheSame(oldItem: PokemonAbilityModel, newItem: PokemonAbilityModel): Boolean {
            return oldItem.pokemon.name == newItem.pokemon.name
        }
    }

    private var onItemClickListener: ((PokemonAbilityModel) -> Unit)? = null

    fun setOnClickListener(listener: (PokemonAbilityModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = pokemons.size
}