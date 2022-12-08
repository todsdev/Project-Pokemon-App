package com.tods.project_pokemon.ui.move

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.tods.project_pokemon.R
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.moves.MovesResponseModel
import com.tods.project_pokemon.data.model.pokemons.moves.MovesModel
import com.tods.project_pokemon.databinding.FragmentPokemonMoveBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.PokemonNameAdapter
import com.tods.project_pokemon.ui.base.BaseFragment
import com.tods.project_pokemon.util.hide
import com.tods.project_pokemon.util.show
import com.tods.project_pokemon.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class PokemonMoveFragment: BaseFragment<FragmentPokemonMoveBinding, PokemonMoveViewModel>() {
    override val viewModel: PokemonMoveViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonMoveBinding =
        FragmentPokemonMoveBinding.inflate(inflater, container, false)
    private val args: PokemonMoveFragmentArgs by navArgs()
    private val pokemonNameAdapter by lazy { PokemonNameAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialSettings()
        configRecyclerView()
        configDataCollector()
        configClickAdapter()
    }

    private fun configClickAdapter() {
        pokemonNameAdapter.setOnClickListener { data ->
            val action = PokemonMoveFragmentDirections.actionPokemonMoveFragmentToPokemonDetailsFragment(data.name)
            findNavController().navigate(action)
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerPokemonNames.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pokemonNameAdapter
        }
    }

    private fun configDataCollector() = lifecycleScope.launch {
        viewModel.moveDetails.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    binding.progressMoveDetails.hide()
                    result.data?.let { values ->
                        pokemonNameAdapter.pokemons = values.learned_by_pokemon.toList()
                        binding.textMoveName.text = values.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                        binding.textAccuracy.text = values.accuracy.toString()
                        binding.textPower.text = values.power.toString()
                        binding.textPowerPoints.text = values.pp.toString()
                        binding.textEffectEntries.text = values.effect_entries[0].short_effect
                            .replace("%", " ")
                            .replace("$", " ")
                            .replace("_", " ")
                        binding.textType.text = values.type.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                        binding.textDamageClass.text = values.damage_class.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                        binding.textTarget.text = values.target.name
                            .replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                            .replace("-", " ")
                        configBackground(values)
                    }
                }
                is ResourceState.Error -> {
                    binding.progressMoveDetails.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("PokemonListFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressMoveDetails.show()
                }
                else -> { }
            }
        }
    }

    private fun configBackground(values: MovesResponseModel) {
        when (values.type.name) {
            getString(R.string.poison) -> {
                binding.imageType.setImageResource(R.drawable.ic_poison)
                binding.mainConstraintMoves.setBackgroundResource(R.color.poison_background)
            }
            getString(R.string.bug) -> {
                binding.imageType.setImageResource(R.drawable.ic_bug)
                binding.mainConstraintMoves.setBackgroundResource(R.color.bug_background)
            }
            getString(R.string.dark) -> {
                binding.imageType.setImageResource(R.drawable.ic_dark)
                binding.mainConstraintMoves.setBackgroundResource(R.color.dark_background)
            }
            getString(R.string.dragon) -> {
                binding.imageType.setImageResource(R.drawable.ic_dragon)
                binding.mainConstraintMoves.setBackgroundColor(R.color.dragon_background)
            }
            getString(R.string.electric) -> {
                binding.imageType.setImageResource(R.drawable.ic_electric)
                binding.mainConstraintMoves.setBackgroundResource(R.color.electric_background)
            }
            getString(R.string.fairy) -> {
                binding.imageType.setImageResource(R.drawable.ic_fairy)
                binding.mainConstraintMoves.setBackgroundResource(R.color.fairy_background)
            }
            getString(R.string.fighting) -> {
                binding.imageType.setImageResource(R.drawable.ic_fighting)
                binding.mainConstraintMoves.setBackgroundResource(R.color.fighting_background)
            }
            getString(R.string.fire) -> {
                binding.imageType.setImageResource(R.drawable.ic_fire)
                binding.mainConstraintMoves.setBackgroundResource(R.color.fire_background)
            }
            getString(R.string.flying) -> {
                binding.imageType.setImageResource(R.drawable.ic_flying)
                binding.mainConstraintMoves.setBackgroundResource(R.color.flying_background)
            }
            getString(R.string.ghost) -> {
                binding.imageType.setImageResource(R.drawable.ic_ghost)
                binding.mainConstraintMoves.setBackgroundResource(R.color.ghost_background)
            }
            getString(R.string.grass) -> {
                binding.imageType.setImageResource(R.drawable.ic_grass)
                binding.mainConstraintMoves.setBackgroundResource(R.color.grass_background)
            }
            getString(R.string.ground) -> {
                binding.imageType.setImageResource(R.drawable.ic_ground)
                binding.mainConstraintMoves.setBackgroundResource(R.color.ground_background)
            }
            getString(R.string.ice) -> {
                binding.imageType.setImageResource(R.drawable.ic_ice)
                binding.mainConstraintMoves.setBackgroundResource(R.color.ice_background)
            }
            getString(R.string.normal) -> {
                binding.imageType.setImageResource(R.drawable.ic_normal)
                binding.mainConstraintMoves.setBackgroundResource(R.color.normal_background)
            }
            getString(R.string.psychic) -> {
                binding.imageType.setImageResource(R.drawable.ic_psychic)
                binding.mainConstraintMoves.setBackgroundResource(R.color.psychic_background)
            }
            getString(R.string.rock) -> {
                binding.imageType.setImageResource(R.drawable.ic_rock)
                binding.mainConstraintMoves.setBackgroundResource(R.color.rock_background)
            }
            getString(R.string.steel) -> {
                binding.imageType.setImageResource(R.drawable.ic_steel)
                binding.mainConstraintMoves.setBackgroundResource(R.color.steel_background)
            }
            getString(R.string.water) -> {
                binding.imageType.setImageResource(R.drawable.ic_water)
                binding.mainConstraintMoves.setBackgroundResource(R.color.water_background)
            }
            else -> {}
        }
    }

    private fun configInitialSettings() {
        viewModel.fetch(args.data)
    }
}