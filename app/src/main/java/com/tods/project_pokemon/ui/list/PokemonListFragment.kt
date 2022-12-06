package com.tods.project_pokemon.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tods.project_pokemon.R
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.databinding.FragmentPokemonListBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.PokemonAdapter
import com.tods.project_pokemon.ui.base.BaseFragment
import com.tods.project_pokemon.util.hide
import com.tods.project_pokemon.util.show
import com.tods.project_pokemon.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PokemonListFragment: BaseFragment<FragmentPokemonListBinding, PokemonListViewModel>() {
    override val viewModel: PokemonListViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonListBinding =
        FragmentPokemonListBinding.inflate(inflater, container, false)
    private val pokemonListAdapter by lazy { PokemonAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        configDataCollection()
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.list.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    result.data?.let { values ->
                        binding.progressPokemonList.hide()
                        pokemonListAdapter.pokemons = values.results.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressPokemonList.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("PokemonListFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressPokemonList.show()
                }
                else -> { }
            }
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerPokemonList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = pokemonListAdapter
        }
    }
}