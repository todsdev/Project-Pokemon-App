package com.tods.project_pokemon.ui.type_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tods.project_pokemon.R
import com.tods.project_pokemon.data.model.types.TypesResponseModel
import com.tods.project_pokemon.databinding.FragmentTypeResultBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.MoveTypesAdapter
import com.tods.project_pokemon.ui.adapters.PokemonNameTypesAdapter
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
class TypesResultFragment: BaseFragment<FragmentTypeResultBinding, TypesResultViewModel>() {
    override val viewModel: TypesResultViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTypeResultBinding =
        FragmentTypeResultBinding.inflate(inflater, container, false)
    private val args: TypesResultFragmentArgs by navArgs()
    private val pokemonsAdapter by lazy { PokemonNameTypesAdapter() }
    private val movesAdapter by lazy { MoveTypesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialSettings()
        configBackground()
        configDataCollector()
        configRecyclerView()
        configClickAdapter()
    }

    private fun configClickAdapter() {
        pokemonsAdapter.setOnClickListener { data ->
            val action = TypesResultFragmentDirections.actionTypesResultFragmentToPokemonDetailsFragment(data.pokemon.name)
            findNavController().navigate(action)
        }
        movesAdapter.setOnClickListener { data ->
            val action = TypesResultFragmentDirections.actionTypesResultFragmentToPokemonMoveFragment(data.name)
            findNavController().navigate(action)
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerMoves.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movesAdapter
        }
        recyclerPokemons.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pokemonsAdapter
        }
    }

    private fun configDataCollector() = lifecycleScope.launch {
        viewModel.filter.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    binding.progressTypeResults.hide()
                    result.data?.let { values ->
                        movesAdapter.moves = values.moves.toList()
                        pokemonsAdapter.pokemons = values.pokemon.toList()
                        configDamageRelations(values)
                    }
                }
                is ResourceState.Error -> {
                    binding.progressTypeResults.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("PokemonListFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressTypeResults.show()
                }
                else -> { }
            }
        }
    }

    private fun configDamageRelations(values: TypesResponseModel) {
        when (values.damage_relations.double_damage_to.size) {
            0 -> {}
            1 -> {
                binding.textDdt3.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            2 -> {
                binding.textDdt3.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt4.text =
                    values.damage_relations.double_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            3 -> {
                binding.textDdt4.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt5.text =
                    values.damage_relations.double_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt6.text =
                    values.damage_relations.double_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            4 -> {
                binding.textDdt1.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt2.text =
                    values.damage_relations.double_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt3.text =
                    values.damage_relations.double_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt4.text =
                    values.damage_relations.double_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            5 -> {
                binding.textDdt1.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt2.text =
                    values.damage_relations.double_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt3.text =
                    values.damage_relations.double_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt4.text =
                    values.damage_relations.double_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt5.text =
                    values.damage_relations.double_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            6 -> {
                binding.textDdt1.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt2.text =
                    values.damage_relations.double_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt3.text =
                    values.damage_relations.double_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt4.text =
                    values.damage_relations.double_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt5.text =
                    values.damage_relations.double_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt6.text =
                    values.damage_relations.double_damage_to[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            7 -> {
                binding.textDdt1.text =
                    values.damage_relations.double_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt2.text =
                    values.damage_relations.double_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt3.text =
                    values.damage_relations.double_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt4.text =
                    values.damage_relations.double_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt5.text =
                    values.damage_relations.double_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt6.text =
                    values.damage_relations.double_damage_to[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdt7.text =
                    values.damage_relations.double_damage_to[6].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
        }
        when (values.damage_relations.double_damage_from.size) {
            0 -> {}
            1 -> {
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            2 -> {
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf5.text =
                    values.damage_relations.double_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            3 -> {
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf5.text =
                    values.damage_relations.double_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf6.text =
                    values.damage_relations.double_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            4 -> {
                binding.textDdf1.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf2.text =
                    values.damage_relations.double_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf3.text =
                    values.damage_relations.double_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            5 -> {
                binding.textDdf1.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf2.text =
                    values.damage_relations.double_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf3.text =
                    values.damage_relations.double_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf5.text =
                    values.damage_relations.double_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            6 -> {
                binding.textDdf1.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf2.text =
                    values.damage_relations.double_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf3.text =
                    values.damage_relations.double_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf5.text =
                    values.damage_relations.double_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf6.text =
                    values.damage_relations.double_damage_from[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            7 -> {
                binding.textDdf1.text =
                    values.damage_relations.double_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf2.text =
                    values.damage_relations.double_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf3.text =
                    values.damage_relations.double_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf4.text =
                    values.damage_relations.double_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf5.text =
                    values.damage_relations.double_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf6.text =
                    values.damage_relations.double_damage_from[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textDdf7.text =
                    values.damage_relations.double_damage_from[6].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
        }
        when (values.damage_relations.half_damage_to.size) {
            0 -> {}
            1 -> {
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            2 -> {
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt5.text =
                    values.damage_relations.half_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            3 -> {
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt5.text =
                    values.damage_relations.half_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt6.text =
                    values.damage_relations.half_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            4 -> {
                binding.textHdt1.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt2.text =
                    values.damage_relations.half_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt3.text =
                    values.damage_relations.half_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            5 -> {
                binding.textHdt1.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt2.text =
                    values.damage_relations.half_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt3.text =
                    values.damage_relations.half_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt5.text =
                    values.damage_relations.half_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            6 -> {
                binding.textHdt1.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt2.text =
                    values.damage_relations.half_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt3.text =
                    values.damage_relations.half_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt5.text =
                    values.damage_relations.half_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt6.text =
                    values.damage_relations.half_damage_to[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            7 -> {
                binding.textHdt1.text =
                    values.damage_relations.half_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt2.text =
                    values.damage_relations.half_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt3.text =
                    values.damage_relations.half_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt4.text =
                    values.damage_relations.half_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt5.text =
                    values.damage_relations.half_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt6.text =
                    values.damage_relations.half_damage_to[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdt7.text =
                    values.damage_relations.half_damage_to[6].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
        }
        when (values.damage_relations.half_damage_from.size) {
            0 -> {}
            1 -> {
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            2 -> {
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf5.text =
                    values.damage_relations.half_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            3 -> {
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf5.text =
                    values.damage_relations.half_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf6.text =
                    values.damage_relations.half_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            4 -> {
                binding.textHdf1.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf2.text =
                    values.damage_relations.half_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf3.text =
                    values.damage_relations.half_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            5 -> {
                binding.textHdf1.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf2.text =
                    values.damage_relations.half_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf3.text =
                    values.damage_relations.half_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf5.text =
                    values.damage_relations.half_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            6 -> {
                binding.textHdf1.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf2.text =
                    values.damage_relations.half_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf3.text =
                    values.damage_relations.half_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf5.text =
                    values.damage_relations.half_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf6.text =
                    values.damage_relations.half_damage_from[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            7 -> {
                binding.textHdf1.text =
                    values.damage_relations.half_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf2.text =
                    values.damage_relations.half_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf3.text =
                    values.damage_relations.half_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf4.text =
                    values.damage_relations.half_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf5.text =
                    values.damage_relations.half_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf6.text =
                    values.damage_relations.half_damage_from[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textHdf7.text =
                    values.damage_relations.half_damage_from[6].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
        }
        when (values.damage_relations.no_damage_to.size) {
            0 -> {}
            1 -> {
                binding.textNdt4.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            2 -> {
                binding.textNdt4.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt5.text =
                    values.damage_relations.no_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            3 -> {
                binding.textNdt6.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt2.text =
                    values.damage_relations.no_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt3.text =
                    values.damage_relations.no_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            4 -> {
                binding.textNdt1.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt2.text =
                    values.damage_relations.no_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt3.text =
                    values.damage_relations.no_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt4.text =
                    values.damage_relations.no_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            5 -> {
                binding.textNdt1.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt2.text =
                    values.damage_relations.no_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt3.text =
                    values.damage_relations.no_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt4.text =
                    values.damage_relations.no_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt5.text =
                    values.damage_relations.no_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            6 -> {
                binding.textNdt1.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt2.text =
                    values.damage_relations.no_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt3.text =
                    values.damage_relations.no_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt4.text =
                    values.damage_relations.no_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt5.text =
                    values.damage_relations.no_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt6.text =
                    values.damage_relations.no_damage_to[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            7 -> {
                binding.textNdt1.text =
                    values.damage_relations.no_damage_to[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt2.text =
                    values.damage_relations.no_damage_to[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt3.text =
                    values.damage_relations.no_damage_to[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt4.text =
                    values.damage_relations.no_damage_to[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt5.text =
                    values.damage_relations.no_damage_to[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt6.text =
                    values.damage_relations.no_damage_to[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdt7.text =
                    values.damage_relations.no_damage_to[6].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
        }
        when (values.damage_relations.no_damage_from.size) {
            0 -> {}
            1 -> {
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            2 -> {
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf5.text =
                    values.damage_relations.no_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            3 -> {
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf5.text =
                    values.damage_relations.no_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf6.text =
                    values.damage_relations.no_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            4 -> {
                binding.textNdf1.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf2.text =
                    values.damage_relations.no_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf3.text =
                    values.damage_relations.no_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            5 -> {
                binding.textNdf1.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf2.text =
                    values.damage_relations.no_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf3.text =
                    values.damage_relations.no_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf5.text =
                    values.damage_relations.no_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            6 -> {
                binding.textNdf1.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf2.text =
                    values.damage_relations.no_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf3.text =
                    values.damage_relations.no_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf5.text =
                    values.damage_relations.no_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf6.text =
                    values.damage_relations.no_damage_from[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
            7 -> {
                binding.textNdf1.text =
                    values.damage_relations.no_damage_from[0].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf2.text =
                    values.damage_relations.no_damage_from[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf3.text =
                    values.damage_relations.no_damage_from[2].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf4.text =
                    values.damage_relations.no_damage_from[3].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf5.text =
                    values.damage_relations.no_damage_from[4].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf6.text =
                    values.damage_relations.no_damage_from[5].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                binding.textNdf7.text =
                    values.damage_relations.no_damage_from[6].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
            }
        }
    }

    private fun configBackground() {
        when(args.data) {
            getString(R.string.fairy) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.fairy_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_fairy)
                binding.textTypeResults.text = getString(R.string.fairy).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.fire) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.fire_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_fire)
                binding.textTypeResults.text = getString(R.string.fire).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.fighting) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.fighting_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_fighting)
                binding.textTypeResults.text = getString(R.string.fighting).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.flying) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.flying_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_flying)
                binding.textTypeResults.text = getString(R.string.flying).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.poison) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.poison_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_poison)
                binding.textTypeResults.text = getString(R.string.poison).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.dragon) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.dragon_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_dragon)
                binding.textTypeResults.text = getString(R.string.dragon).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.grass) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.grass_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_grass)
                binding.textTypeResults.text = getString(R.string.grass).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.ground) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.ground_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_ground)
                binding.textTypeResults.text = getString(R.string.ground).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.rock) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.rock_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_rock)
                binding.textTypeResults.text = getString(R.string.rock).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.psychic) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.psychic_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_psychic)
                binding.textTypeResults.text = getString(R.string.psychic).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.water) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.water_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_water)
                binding.textTypeResults.text = getString(R.string.water).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.dark) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.dark_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_dark)
                binding.textTypeResults.text = getString(R.string.dark).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.ghost) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.ghost_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_ghost)
                binding.textTypeResults.text = getString(R.string.ghost).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.normal) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.normal_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_normal)
                binding.textTypeResults.text = getString(R.string.normal).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.steel) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.steel_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_steel)
                binding.textTypeResults.text = getString(R.string.steel).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.electric) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.electric_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_electric)
                binding.textTypeResults.text = getString(R.string.electric).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.bug) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.bug_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_bug)
                binding.textTypeResults.text = getString(R.string.bug).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.ice) -> {
                binding.mainConstraintTypeResult.setBackgroundResource(R.color.ice_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_ice)
                binding.textTypeResults.text = getString(R.string.ice).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
        }
    }

    private fun configInitialSettings() {
        viewModel.fetch(args.data)
    }
}
