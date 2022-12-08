package com.tods.project_pokemon.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.project_pokemon.data.model.list.PokemonListResponseModel
import com.tods.project_pokemon.repository.PokemonRepository
import com.tods.project_pokemon.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {
    private val _search = MutableStateFlow<ResourceState<PokemonListResponseModel>>(ResourceState.Loading())
    val search: MutableStateFlow<ResourceState<PokemonListResponseModel>> = _search

    fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch()  {
        _search.value = ResourceState.Loading()
        try {
            val response = repository.list()
            _search.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _search.value = ResourceState.Error("An error with internet connection occurred")
                else -> _search.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<PokemonListResponseModel>): ResourceState<PokemonListResponseModel> {
        if(response.isSuccessful) {
            response.body().let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}