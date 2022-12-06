package com.tods.project_pokemon.ui.search

import androidx.lifecycle.ViewModel
import com.tods.project_pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
}