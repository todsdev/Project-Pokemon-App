package com.tods.project_pokemon.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.project_pokemon.databinding.FragmentPokemonDetailsBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment: BaseFragment<FragmentPokemonDetailsBinding, PokemonDetailsViewModel>() {
    override val viewModel: PokemonDetailsViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonDetailsBinding =
        FragmentPokemonDetailsBinding.inflate(inflater, container, false)
}