package com.tods.project_pokemon.ui.types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tods.project_pokemon.R
import com.tods.project_pokemon.databinding.FragmentPokemonTypesBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonTypesFragment: BaseFragment<FragmentPokemonTypesBinding, PokemonTypesViewModel>() {
    override val viewModel: PokemonTypesViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonTypesBinding =
        FragmentPokemonTypesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configButtons()
    }

    private fun configButtons() = with(binding) {
        buttonBug.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.bug))
            findNavController().navigate(action)
        }
        buttonDark.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.dark))
            findNavController().navigate(action)
        }
        buttonDragon.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.dragon))
            findNavController().navigate(action)
        }
        buttonFairy.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.fairy))
            findNavController().navigate(action)
        }
        buttonElectric.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.electric))
            findNavController().navigate(action)
        }
        buttonFighting.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.fighting))
            findNavController().navigate(action)
        }
        buttonFire.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.fire))
            findNavController().navigate(action)
        }
        buttonFlying.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.flying))
            findNavController().navigate(action)
        }
        buttonGhost.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.ghost))
            findNavController().navigate(action)
        }
        buttonGrass.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.grass))
            findNavController().navigate(action)
        }
        buttonGround.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.ground))
            findNavController().navigate(action)
        }
        buttonIce.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.ice))
            findNavController().navigate(action)
        }
        buttonNormal.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.normal))
            findNavController().navigate(action)
        }
        buttonPoison.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.poison))
            findNavController().navigate(action)
        }
        buttonRock.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.rock))
            findNavController().navigate(action)
        }
        buttonWater.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.water))
            findNavController().navigate(action)
        }
        buttonSteel.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.steel))
            findNavController().navigate(action)
        }
        buttonPsychic.setOnClickListener {
            val action = PokemonTypesFragmentDirections.actionPokemonTypesFragmentToTypesResultFragment(getString(R.string.psychic))
            findNavController().navigate(action)
        }
    }
}