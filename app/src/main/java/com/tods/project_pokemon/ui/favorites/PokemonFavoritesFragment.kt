package com.tods.project_pokemon.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tods.project_pokemon.R
import com.tods.project_pokemon.databinding.FragmentPokemonFavoritesBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.PokemonFavoriteAdapter
import com.tods.project_pokemon.ui.base.BaseFragment
import com.tods.project_pokemon.util.hide
import com.tods.project_pokemon.util.show
import com.tods.project_pokemon.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFavoritesFragment: BaseFragment<FragmentPokemonFavoritesBinding, PokemonFavoritesViewModel>() {
    override val viewModel: PokemonFavoritesViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonFavoritesBinding =
        FragmentPokemonFavoritesBinding.inflate(inflater, container, false)
    private val pokemonAdapter by lazy { PokemonFavoriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configDataCollection()
        configRecyclerView()
        configClickAdapter()
    }

    private fun itemTouchHelperCallback(): ItemTouchHelper.SimpleCallback {
        return object: ItemTouchHelper
        .SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.delete_favorite_title))
                    .setMessage(getString(R.string.sure_delete))
                    .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                        val pokemon = pokemonAdapter.recoverPokemonPosition(viewHolder.adapterPosition)
                        viewModel.delete(pokemon).also {
                            toast(getString(R.string.deleted_character))
                        }
                    }.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                        dialog.dismiss().also {

                        }
                    }.show()
            }
        }
    }

    private fun configClickAdapter() {
        pokemonAdapter.setOnClickListener { data ->
            val action = PokemonFavoritesFragmentDirections.actionPokemonFavoritesFragmentToPokemonDetailsFragment(data.name)
            findNavController().navigate(action)
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pokemonAdapter
        }
        ItemTouchHelper(itemTouchHelperCallback()).attachToRecyclerView(recyclerFavorites)
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.favorites.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    result.data?.let { values ->
                        binding.textEmpty.hide()
                        pokemonAdapter.pokemons = values.toList()
                    }
                }
                is ResourceState.Empty -> {
                    binding.textEmpty.show()
                }
                else -> { }
            }
        }
    }
}