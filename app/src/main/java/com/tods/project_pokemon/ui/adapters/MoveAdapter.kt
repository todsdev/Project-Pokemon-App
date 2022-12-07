package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.picassopalette.PicassoPalette
import com.squareup.picasso.Picasso
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.data.model.pokemons.moves.MoveModel
import com.tods.project_pokemon.data.model.pokemons.moves.MovesModel
import com.tods.project_pokemon.databinding.ItemMoveBinding
import com.tods.project_pokemon.databinding.ItemPokemonBinding
import com.tods.project_pokemon.util.Constants
import java.util.*

class MoveAdapter(): ListAdapter<MovesModel, MoveAdapter.MovesListViewHolder>(ItemDiff()) {

    inner class MovesListViewHolder(val binding: ItemMoveBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var moves: List<MovesModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesListViewHolder {
        return MovesListViewHolder(
            ItemMoveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovesListViewHolder, position: Int) {
        val move = moves[position]
        holder.binding.apply {
            textMove.text = move.move.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(move)
            }
        }
    }

    override fun getItemCount(): Int = moves.size


    class ItemDiff : DiffUtil.ItemCallback<MovesModel>() {
        override fun areItemsTheSame(oldItem: MovesModel, newItem: MovesModel): Boolean {
            return oldItem.move.name == newItem.move.name
        }

        override fun areContentsTheSame(oldItem: MovesModel, newItem: MovesModel): Boolean {
            return oldItem.move.name == newItem.move.name
        }
    }

    private var onItemClickListener: ((MovesModel) -> Unit)? = null

    fun setOnClickListener(listener: (MovesModel) -> Unit) {
        onItemClickListener = listener
    }
}