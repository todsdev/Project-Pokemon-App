package com.tods.project_pokemon.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.florent37.picassopalette.PicassoPalette
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import com.tods.project_pokemon.R
import com.tods.project_pokemon.data.model.list.results.ResultsModel
import com.tods.project_pokemon.data.model.pokemons.PokemonResponseModel
import com.tods.project_pokemon.databinding.FragmentPokemonDetailsBinding
import com.tods.project_pokemon.state.ResourceState
import com.tods.project_pokemon.ui.adapters.MoveAdapter
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
class PokemonDetailsFragment: BaseFragment<FragmentPokemonDetailsBinding, PokemonDetailsViewModel>() {
    override val viewModel: PokemonDetailsViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPokemonDetailsBinding =
        FragmentPokemonDetailsBinding.inflate(inflater, container, false)
    private val args: PokemonDetailsFragmentArgs by navArgs()
    private val movesAdapter by lazy { MoveAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialSettings()
        configDataCollection()
        configRecyclerView()
        configClickAdapter()
    //    configClickButtons()
    }

    private fun configClickButtons() = with(binding) {
        imageFavorite.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(args.data.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
                .setMessage(getString(R.string.favorite_question))
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(getString(R.string.confirm)) { _, _ ->
    //                viewModel.insert(args.data)
                }.show()
        }
    }

    private fun configClickAdapter() {
        movesAdapter.setOnClickListener { data ->
            val action = PokemonDetailsFragmentDirections.actionPokemonDetailsFragmentToPokemonMoveFragment(data.move.name)
            findNavController().navigate(action)
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerMoves.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movesAdapter
        }
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.details.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    binding.progressPokemonDetails.hide()
                    result.data?.let { values ->
                        movesAdapter.moves = values.moves.toList()
                        configName(values)
                        configAbilities(values)
                        configImageAndBackground(values)
                        configIcons(values)
                        configStats(values)
                        configCarousel(values)
                        binding.imageFavorite.setOnClickListener {
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle(values.name.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.ROOT
                                    ) else it.toString()
                                })
                                .setMessage(getString(R.string.favorite_question))
                                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                                    dialog.dismiss()
                                }.setPositiveButton(getString(R.string.confirm)) { _, _ ->
                                    viewModel.insert(values)
                                    toast(getString(R.string.favorite_success))
                                }.show()
                        }
                    }
                }
                is ResourceState.Error -> {
                    binding.progressPokemonDetails.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("PokemonListFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressPokemonDetails.show()
                }
                else -> { }
            }
        }
    }

    private fun configCarousel(values: PokemonResponseModel) {
        val imageList = mutableListOf<String>()
        if (!values.sprites.front_default.isNullOrEmpty()) {
            imageList.add(values.sprites.front_default)
        }
        if (!values.sprites.back_default.isNullOrEmpty()) {
            imageList.add(values.sprites.back_default)
        }
        if (!values.sprites.front_shiny.isNullOrEmpty()) {
            imageList.add(values.sprites.front_shiny)
        }
        if (!values.sprites.back_shiny.isNullOrEmpty()) {
            imageList.add(values.sprites.back_shiny)
        }
        if (!values.sprites.other.home.front_default.isNullOrEmpty()) {
            imageList.add(values.sprites.other.home.front_default)
        }
        if (!values.sprites.other.home.front_shiny.isNullOrEmpty()) {
            imageList.add(values.sprites.other.home.front_shiny)
        }
        val imageListener: ImageListener = ImageListener { position, imageView ->
            val url = imageList[position]
            Picasso.get().load(url).into(imageView)
        }
        if(imageList.isNotEmpty()) {
            binding.carouselView.setImageListener(imageListener)
            binding.carouselView.pageCount = imageList.size
        }
    }

    private fun configStats(values: PokemonResponseModel) {
        binding.textCountHp.text = values.stats[0].base_stat.toString()
        binding.progressHp.progress = values.stats[0].base_stat
        binding.textCountAttack.text = values.stats[1].base_stat.toString()
        binding.progressAttack.progress = values.stats[1].base_stat
        binding.textCountDefense.text = values.stats[2].base_stat.toString()
        binding.progressDefense.progress = values.stats[2].base_stat
        binding.textCountSpecialAttack.text = values.stats[3].base_stat.toString()
        binding.progressSpecialAttack.progress = values.stats[3].base_stat
        binding.textCountSpecialDefense.text = values.stats[4].base_stat.toString()
        binding.progressSpecialDefense.progress = values.stats[4].base_stat
        binding.textCountSpeed.text = values.stats[5].base_stat.toString()
        binding.progressSpeed.progress = values.stats[5].base_stat
    }

    private fun configName(values: PokemonResponseModel) {
        binding.textPokemonNameDetails.text = values.name
            .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
            .replace("-", " ")
    }

    private fun configAbilities(values: PokemonResponseModel) {
        when (values.abilities.size) {
            1 -> {
                binding.textAbility1.text = values.abilities[0].ability.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }
            2 -> {
                binding.textAbility1.text = values.abilities[0].ability.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                binding.textAbility2.text = values.abilities[1].ability.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }
            3 -> {
                binding.textAbility1.text = values.abilities[0].ability.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                binding.textAbility2.text = values.abilities[1].ability.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                binding.textAbility3.text = values.abilities[2].ability.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }
            else -> { }
        }
    }

    private fun configIcons(values: PokemonResponseModel) {
        when (values.types.size) {
            1 -> {
                binding.imageType2.isVisible = false
                binding.imageType3.isVisible = false
                when (values.types[0].type.name) {
                    getString(R.string.poison) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_poison)
                    }
                    getString(R.string.bug) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_bug)
                    }
                    getString(R.string.dark) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_dark)
                    }
                    getString(R.string.dragon) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_dragon)
                    }
                    getString(R.string.electric) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_electric)
                    }
                    getString(R.string.fairy) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fairy)
                    }
                    getString(R.string.fighting) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fighting)
                    }
                    getString(R.string.fire) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fire)
                    }
                    getString(R.string.flying) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_flying)
                    }
                    getString(R.string.ghost) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ghost)
                    }
                    getString(R.string.grass) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_grass)
                    }
                    getString(R.string.ground) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ground)
                    }
                    getString(R.string.ice) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ice)
                    }
                    getString(R.string.normal) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_normal)
                    }
                    getString(R.string.psychic) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_psychic)
                    }
                    getString(R.string.rock) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_rock)
                    }
                    getString(R.string.steel) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_steel)
                    }
                    getString(R.string.water) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_water)
                    }
                }
            }
            2 -> {
                binding.imageType3.isVisible = false
                when (values.types[0].type.name) {
                    getString(R.string.poison) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_poison)
                    }
                    getString(R.string.bug) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_bug)
                    }
                    getString(R.string.dark) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_dark)
                    }
                    getString(R.string.dragon) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_dragon)
                    }
                    getString(R.string.electric) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_electric)
                    }
                    getString(R.string.fairy) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fairy)
                    }
                    getString(R.string.fighting) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fighting)
                    }
                    getString(R.string.fire) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fire)
                    }
                    getString(R.string.flying) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_flying)
                    }
                    getString(R.string.ghost) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ghost)
                    }
                    getString(R.string.grass) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_grass)
                    }
                    getString(R.string.ground) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ground)
                    }
                    getString(R.string.ice) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ice)
                    }
                    getString(R.string.normal) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_normal)
                    }
                    getString(R.string.psychic) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_psychic)
                    }
                    getString(R.string.rock) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_rock)
                    }
                    getString(R.string.steel) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_steel)
                    }
                    getString(R.string.water) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_water)
                    }
                }
                when (values.types[1].type.name) {
                    getString(R.string.poison) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_poison)
                    }
                    getString(R.string.bug) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_bug)
                    }
                    getString(R.string.dark) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_dark)
                    }
                    getString(R.string.dragon) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_dragon)
                    }
                    getString(R.string.electric) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_electric)
                    }
                    getString(R.string.fairy) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_fairy)
                    }
                    getString(R.string.fighting) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_fighting)
                    }
                    getString(R.string.fire) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_fire)
                    }
                    getString(R.string.flying) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_flying)
                    }
                    getString(R.string.ghost) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_ghost)
                    }
                    getString(R.string.grass) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_grass)
                    }
                    getString(R.string.ground) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_ground)
                    }
                    getString(R.string.ice) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_ice)
                    }
                    getString(R.string.normal) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_normal)
                    }
                    getString(R.string.psychic) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_psychic)
                    }
                    getString(R.string.rock) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_rock)
                    }
                    getString(R.string.steel) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_steel)
                    }
                    getString(R.string.water) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_water)
                    }
                }
            }
            3 -> {
                when (values.types[0].type.name) {
                    getString(R.string.poison) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_poison)
                    }
                    getString(R.string.bug) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_bug)
                    }
                    getString(R.string.dark) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_dark)
                    }
                    getString(R.string.dragon) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_dragon)
                    }
                    getString(R.string.electric) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_electric)
                    }
                    getString(R.string.fairy) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fairy)
                    }
                    getString(R.string.fighting) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fighting)
                    }
                    getString(R.string.fire) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_fire)
                    }
                    getString(R.string.flying) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_flying)
                    }
                    getString(R.string.ghost) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ghost)
                    }
                    getString(R.string.grass) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_grass)
                    }
                    getString(R.string.ground) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ground)
                    }
                    getString(R.string.ice) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_ice)
                    }
                    getString(R.string.normal) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_normal)
                    }
                    getString(R.string.psychic) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_psychic)
                    }
                    getString(R.string.rock) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_rock)
                    }
                    getString(R.string.steel) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_steel)
                    }
                    getString(R.string.water) -> {
                        binding.imageType1.setImageResource(R.drawable.ic_water)
                    }
                }
                when (values.types[1].type.name) {
                    getString(R.string.poison) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_poison)
                    }
                    getString(R.string.bug) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_bug)
                    }
                    getString(R.string.dark) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_dark)
                    }
                    getString(R.string.dragon) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_dragon)
                    }
                    getString(R.string.electric) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_electric)
                    }
                    getString(R.string.fairy) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_fairy)
                    }
                    getString(R.string.fighting) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_fighting)
                    }
                    getString(R.string.fire) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_fire)
                    }
                    getString(R.string.flying) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_flying)
                    }
                    getString(R.string.ghost) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_ghost)
                    }
                    getString(R.string.grass) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_grass)
                    }
                    getString(R.string.ground) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_ground)
                    }
                    getString(R.string.ice) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_ice)
                    }
                    getString(R.string.normal) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_normal)
                    }
                    getString(R.string.psychic) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_psychic)
                    }
                    getString(R.string.rock) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_rock)
                    }
                    getString(R.string.steel) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_steel)
                    }
                    getString(R.string.water) -> {
                        binding.imageType2.setImageResource(R.drawable.ic_water)
                    }
                }
                when (values.types[2].type.name) {
                    getString(R.string.poison) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_poison)
                    }
                    getString(R.string.bug) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_bug)
                    }
                    getString(R.string.dark) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_dark)
                    }
                    getString(R.string.dragon) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_dragon)
                    }
                    getString(R.string.electric) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_electric)
                    }
                    getString(R.string.fairy) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_fairy)
                    }
                    getString(R.string.fighting) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_fighting)
                    }
                    getString(R.string.fire) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_fire)
                    }
                    getString(R.string.flying) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_flying)
                    }
                    getString(R.string.ghost) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_ghost)
                    }
                    getString(R.string.grass) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_grass)
                    }
                    getString(R.string.ground) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_ground)
                    }
                    getString(R.string.ice) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_ice)
                    }
                    getString(R.string.normal) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_normal)
                    }
                    getString(R.string.psychic) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_psychic)
                    }
                    getString(R.string.rock) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_rock)
                    }
                    getString(R.string.steel) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_steel)
                    }
                    getString(R.string.water) -> {
                        binding.imageType3.setImageResource(R.drawable.ic_water)
                    }
                }
            }
            else -> {
                binding.imageType1.isVisible = false
                binding.imageType2.isVisible = false
                binding.imageType3.isVisible = false
            }
        }
    }

    private fun configImageAndBackground(values: PokemonResponseModel) {
        val urlMainImage = values.sprites.other.official_artwork.front_default
        Picasso.get()
            .load(urlMainImage)
            .into(
                binding.imagePokemonDetails,
                PicassoPalette.with(urlMainImage, binding.imagePokemonDetails)
                    .use(PicassoPalette.Profile.MUTED_LIGHT)
                    .intoBackground(binding.imagePokemonDetails)
                    .intoBackground(binding.mainConstraint)
                    .intoBackground(binding.recyclerMoves)
            )
    }

    private fun configInitialSettings() {
        viewModel.fetch(args.data)
    }
}