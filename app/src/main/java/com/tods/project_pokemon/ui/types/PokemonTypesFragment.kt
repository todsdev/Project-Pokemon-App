package com.tods.project_pokemon.ui.types

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.project_pokemon.databinding.FragmentPokemonTypesBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonTypesFragment: BaseFragment<FragmentPokemonTypesBinding, PokemonTypesViewModel>() {

    override val viewModel: PokemonTypesViewModel by viewModels()
    override fun recoverViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPokemonTypesBinding =
        FragmentPokemonTypesBinding.inflate(inflater, container, false)


}