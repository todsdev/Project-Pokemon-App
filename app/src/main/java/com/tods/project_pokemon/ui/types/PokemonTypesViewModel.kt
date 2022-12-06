package com.tods.project_pokemon.ui.types

import androidx.lifecycle.ViewModel
import com.tods.project_pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonTypesViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

}