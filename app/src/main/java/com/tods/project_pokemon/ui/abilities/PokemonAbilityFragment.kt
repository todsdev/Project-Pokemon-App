package com.tods.project_pokemon.ui.abilities

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
import com.tods.project_pokemon.databinding.FragmentPokemonAbilityBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.PokemonAdapter
import com.tods.project_pokemon.ui.adapters.PokemonNameAbilityAdapter
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
class PokemonAbilityFragment: BaseFragment<FragmentPokemonAbilityBinding, PokemonAbilityViewModel>() {
    override val viewModel: PokemonAbilityViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonAbilityBinding =
        FragmentPokemonAbilityBinding.inflate(inflater, container, false)
    private val adapterPokemonName by lazy { PokemonNameAbilityAdapter() }
    private val args: PokemonAbilityFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialSettings()
        configRecyclerView()
        configDataCollection()
        configClickAdapter()
    }

    private fun configClickAdapter() {
        adapterPokemonName.setOnClickListener { data ->
            val action = PokemonAbilityFragmentDirections.actionPokemonAbilityFragmentToPokemonDetailsFragment(data.pokemon.name)
            findNavController().navigate(action)
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerPokemonAbilities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterPokemonName
        }
    }

    private fun configInitialSettings() {
        viewModel.fetch(args.data)
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.data.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    binding.progressAbilities.hide()
                    result.data?.let { values ->
                        binding.textAbilityName.text = values.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        binding.textAbilityDescription.text = values.effect_entries[1].effect
                            .replace("[", " ")
                            .replace("]", " ")
                            .replace("_", " ")
                            .replace("$", " ")
                            .replace("&", " ")
                        adapterPokemonName.pokemons = values.pokemon.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressAbilities.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("PokemonAbilityFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressAbilities.show()
                }
                else -> { }
            }
        }
    }
}