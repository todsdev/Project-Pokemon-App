package com.tods.project_pokemon.ui.move

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.project_pokemon.data.model.moves.MovesResponseModel
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
class PokemonMoveViewModel @Inject constructor(val repository: PokemonRepository): ViewModel() {
    private val _moveDetails = MutableStateFlow<ResourceState<MovesResponseModel>>(ResourceState.Loading())
    val moveDetails: StateFlow<ResourceState<MovesResponseModel>> = _moveDetails

    fun fetch(name: String) = viewModelScope.launch {
        safeFetch(name)
    }

    private suspend fun safeFetch(name: String) {
        _moveDetails.value = ResourceState.Loading()
        try {
            val response = repository.recoverMoveByName(name)
            _moveDetails.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _moveDetails.value = ResourceState.Error("An error with internet connection occurred")
                else -> _moveDetails.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<MovesResponseModel>): ResourceState<MovesResponseModel> {
        if(response.isSuccessful) {
            response.body().let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}