package com.tods.project_pokemon.ui.details

import androidx.lifecycle.ViewModel
import com.tods.project_pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {
}