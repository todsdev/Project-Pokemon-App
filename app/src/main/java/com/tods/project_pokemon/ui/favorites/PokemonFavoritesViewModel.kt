package com.tods.project_pokemon.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.repository.PokemonRepository
import com.tods.project_pokemon.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonFavoritesViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {
    private val _favorites = MutableStateFlow<ResourceState<List<PokemonResponseModel>>>(ResourceState.Empty())
    val favorites: StateFlow<ResourceState<List<PokemonResponseModel>>> = _favorites

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        repository.getAll().collectLatest { pokemons ->
            if (pokemons.isNullOrEmpty()) {
                _favorites.value = ResourceState.Empty()
            } else {
                _favorites.value = ResourceState.Success(pokemons)
            }
        }
    }

    fun delete(pokemon: PokemonResponseModel) = viewModelScope.launch {
        repository.delete(pokemon)
    }
}