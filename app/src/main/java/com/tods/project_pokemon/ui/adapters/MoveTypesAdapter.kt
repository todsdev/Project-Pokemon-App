package com.tods.project_pokemon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tods.project_pokemon.data.model.types.moves.MovesTypesModel
import com.tods.project_pokemon.databinding.ItemMoveBinding
import java.util.*

class MoveTypesAdapter(): ListAdapter<MovesTypesModel, MoveTypesAdapter.MovesTypeListViewHolder>(ItemDiff()) {

    inner class MovesTypeListViewHolder(val binding: ItemMoveBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var moves: List<MovesTypesModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesTypeListViewHolder {
        return MovesTypeListViewHolder(
            ItemMoveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovesTypeListViewHolder, position: Int) {
        val move = moves[position]
        holder.binding.apply {
            textMove.text = move.name.replaceFirstChar {
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


    class ItemDiff : DiffUtil.ItemCallback<MovesTypesModel>() {
        override fun areItemsTheSame(oldItem: MovesTypesModel, newItem: MovesTypesModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MovesTypesModel, newItem: MovesTypesModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private var onItemClickListener: ((MovesTypesModel) -> Unit)? = null

    fun setOnClickListener(listener: (MovesTypesModel) -> Unit) {
        onItemClickListener = listener
    }
}