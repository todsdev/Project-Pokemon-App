package com.tods.project_pokemon.ui.type_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.project_pokemon.data.model.types.TypesResponseModel
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
class TypesResultViewModel @Inject constructor(val repository: PokemonRepository): ViewModel() {
    private val _filter = MutableStateFlow<ResourceState<TypesResponseModel>>(ResourceState.Loading())
    val filter: StateFlow<ResourceState<TypesResponseModel>> = _filter

    fun fetch(name: String) = viewModelScope.launch {
        safeFetch(name)
    }

    private suspend fun safeFetch(name: String) {
        _filter.value = ResourceState.Loading()
        try {
            val response = repository.recoverInfoByTypeName(name)
            _filter.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _filter.value = ResourceState.Error("An error with internet connection occurred")
                else -> _filter.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<TypesResponseModel>): ResourceState<TypesResponseModel> {
        if(response.isSuccessful) {
            response.body().let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}