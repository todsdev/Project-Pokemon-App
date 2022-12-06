package com.tods.project_pokemon.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.project_pokemon.databinding.FragmentPokemonFavoritesBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFavoritesFragment: BaseFragment<FragmentPokemonFavoritesBinding, PokemonFavoritesViewModel>() {

    override val viewModel: PokemonFavoritesViewModel by viewModels()
    override fun recoverViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPokemonFavoritesBinding =
        FragmentPokemonFavoritesBinding.inflate(inflater, container, false)

}