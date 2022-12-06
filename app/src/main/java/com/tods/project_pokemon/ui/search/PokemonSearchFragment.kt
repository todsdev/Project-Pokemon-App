package com.tods.project_pokemon.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.project_pokemon.databinding.FragmentPokemonSearchBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonSearchFragment: BaseFragment<FragmentPokemonSearchBinding, PokemonSearchViewModel>() {

    override val viewModel: PokemonSearchViewModel by viewModels()
    override fun recoverViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPokemonSearchBinding =
        FragmentPokemonSearchBinding.inflate(inflater, container, false)

}