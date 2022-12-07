package com.tods.project_pokemon.ui.move

import androidx.lifecycle.ViewModel
import com.tods.project_pokemon.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonMoveViewModel @Inject constructor(
    val repository: PokemonRepository
): ViewModel() {

}