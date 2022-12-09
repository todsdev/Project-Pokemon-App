package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.picassopalette.PicassoPalette
import com.squareup.picasso.Picasso
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.databinding.ItemFavoritePokemonBinding
import java.util.*

class PokemonFavoriteAdapter(): ListAdapter<PokemonResponseModel, PokemonFavoriteAdapter.PokemonFavoriteViewHolder>(ItemDiff()) {

    inner class PokemonFavoriteViewHolder(val binding: ItemFavoritePokemonBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var pokemons: List<PokemonResponseModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonFavoriteViewHolder {
        return PokemonFavoriteViewHolder(
            ItemFavoritePokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonFavoriteViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.apply {
            textNameFavorite.text = pokemon.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }.replace("-", " ")
            Picasso.get()
                .load(pokemon.sprites.other.official_artwork.front_default)
                .into(imageFavoritePokemon, PicassoPalette.with(pokemon.sprites.other.official_artwork.front_default, imageFavoritePokemon)
                    .use(PicassoPalette.Profile.MUTED_LIGHT)
                    .intoBackground(imageFavoritePokemon))
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(pokemon)
            }
        }
    }

    class ItemDiff : DiffUtil.ItemCallback<PokemonResponseModel>() {
        override fun areItemsTheSame(oldItem: PokemonResponseModel, newItem: PokemonResponseModel): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonResponseModel, newItem: PokemonResponseModel): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.id == newItem.id
        }
    }

    private var onItemClickListener: ((PokemonResponseModel) -> Unit)? = null

    fun setOnClickListener(listener: (PokemonResponseModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = pokemons.size

    fun recoverPokemonPosition(position: Int): PokemonResponseModel {
        return pokemons[position]
    }
}