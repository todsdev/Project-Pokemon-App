package com.tods.project_pokemon.ui.abilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.project_pokemon.data.model.abilities.AbilityResponseModel
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
class PokemonAbilityViewModel @Inject constructor(val repository: PokemonRepository): ViewModel() {
    private val _data = MutableStateFlow<ResourceState<AbilityResponseModel>>(ResourceState.Loading())
    val data: StateFlow<ResourceState<AbilityResponseModel>> = _data

    fun fetch(name: String) =  viewModelScope.launch() {
        safeFetch(name)
    }

    private suspend fun safeFetch(name: String) {
        _data.value = ResourceState.Loading()
        try {
            val response = repository.recoverAbilityByName(name)
            _data.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _data.value = ResourceState.Error("An error with internet connection occurred")
                else -> _data.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<AbilityResponseModel>): ResourceState<AbilityResponseModel> {
        if(response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}