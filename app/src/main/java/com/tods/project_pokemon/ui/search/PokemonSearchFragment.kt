package com.tods.project_pokemon.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tods.project_pokemon.R
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.databinding.FragmentPokemonSearchBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.PokemonAdapter
import com.tods.project_pokemon.ui.base.BaseFragment
import com.tods.project_pokemon.util.Constants
import com.tods.project_pokemon.util.Constants.DEFAULT_QUERY
import com.tods.project_pokemon.util.Constants.LAST_SEARCH_QUERY
import com.tods.project_pokemon.util.hide
import com.tods.project_pokemon.util.show
import com.tods.project_pokemon.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PokemonSearchFragment: BaseFragment<FragmentPokemonSearchBinding, PokemonSearchViewModel>() {
    override val viewModel: PokemonSearchViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonSearchBinding =
        FragmentPokemonSearchBinding.inflate(inflater, container, false)
    private val pokemonAdapter by lazy { PokemonAdapter() }
    private lateinit var search: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        configClickAdapter()
        configQuerySearch(savedInstanceState)
        configDataCollector()
    }

    private fun configClickAdapter() {
        pokemonAdapter.setOnClickListener { data ->
            val action = PokemonSearchFragmentDirections.actionPokemonSearchFragmentToPokemonDetailsFragment(data.name)
            findNavController().navigate(action)
        }
    }

    private fun configDataCollector() = lifecycleScope.launch {
        viewModel.search.collect { result ->
            binding.progressSearchPokemon.hide()
            when(result) {
                is ResourceState.Success -> {
                    binding.progressSearchPokemon.hide()
                    result.data?.let { values ->
                        val filteredList = mutableListOf<ResultsModel>()
                        val list: List<ResultsModel> = values.results.toList()
                        if(search.isNotEmpty()) {
                            for(i in list.indices) {
                                if(list[i].name.contains(search)) {
                                    filteredList.add(list[i])
                                }
                            }
                        } else {
                            toast(getString(R.string.an_error_occurred))
                        }
                        pokemonAdapter.pokemons = filteredList.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressSearchPokemon.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("SearchPokemonFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> { }
                else -> { }
            }
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerSearchPokemon.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = pokemonAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY,
        binding.editSearchPokemon.editableText.trim().toString())
    }

    private fun configQuerySearch(savedInstanceState: Bundle?) {
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        configSearchInit(query)
    }

    private fun configSearchInit(query: String) = with(binding) {
        editSearchPokemon.setText(query)
        editSearchPokemon.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_GO) {
                configListRequest()
                true
            } else {
                false
            }
        }
        editSearchPokemon.setOnKeyListener { _, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                configListRequest()
                true
            } else {
                false
            }
        }
    }

    private fun configListRequest() = with(binding) {
        editSearchPokemon.editableText.trim().toString().let { query ->
            if(query.isNotEmpty()) {
                viewModel.fetch()
                search = query
            }
        }
    }
}