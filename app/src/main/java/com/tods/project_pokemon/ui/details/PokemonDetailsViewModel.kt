package com.tods.project_pokemon.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
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
class PokemonDetailsViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {
    private val _details = MutableStateFlow<ResourceState<PokemonResponseModel>>(ResourceState.Loading())
    val details: StateFlow<ResourceState<PokemonResponseModel>> = _details

    fun fetch(name: String) = viewModelScope.launch() {
        safeFetch(name)
    }

    private suspend fun safeFetch(name: String) {
        _details.value = ResourceState.Loading()
        try {
            val response = repository.recoverPokemonByName(name)
            _details.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _details.value = ResourceState.Error("An error with internet connection occurred")
                else -> _details.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<PokemonResponseModel>): ResourceState<PokemonResponseModel> {
        if(response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}