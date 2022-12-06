package com.tods.project_pokemon.ui.list

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
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {
    private val _list = MutableStateFlow<ResourceState<PokemonListResponseModel>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<PokemonListResponseModel>> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        try {
            val response = repository.list()
            _list.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _list.value = ResourceState.Error("An error with internet connection occurred")
                else -> _list.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<PokemonListResponseModel>): ResourceState<PokemonListResponseModel> {
        if (response.isSuccessful) {
            response.body().let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}