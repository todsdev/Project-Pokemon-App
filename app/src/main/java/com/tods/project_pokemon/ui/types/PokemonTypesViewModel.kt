package com.tods.project_pokemon.ui.types

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
class PokemonTypesViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

}