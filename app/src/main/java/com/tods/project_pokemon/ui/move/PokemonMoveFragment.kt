package com.tods.project_pokemon.ui.move

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.project_pokemon.databinding.FragmentPokemonMoveBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonMoveFragment: BaseFragment<FragmentPokemonMoveBinding, PokemonMoveViewModel>() {
    override val viewModel: PokemonMoveViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonMoveBinding =
        FragmentPokemonMoveBinding.inflate(inflater, container, false)
}