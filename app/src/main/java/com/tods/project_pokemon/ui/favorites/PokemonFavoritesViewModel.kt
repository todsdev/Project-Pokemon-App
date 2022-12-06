package com.tods.project_pokemon.ui.favorites

import androidx.lifecycle.ViewModel
import com.tods.project_pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonFavoritesViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

}