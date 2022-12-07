package com.tods.project_pokemon.ui.type_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tods.project_pokemon.R
import com.tods.project_pokemon.databinding.FragmentTypeResultBinding
import com.tods.project_pokemon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TypesResultFragment: BaseFragment<FragmentTypeResultBinding, TypesResultViewModel>() {
    override val viewModel: TypesResultViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTypeResultBinding =
        FragmentTypeResultBinding.inflate(inflater, container, false)
    private val args: TypesResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configInitialSettings()
        configBackground()
    }

    private fun configBackground() {
        when(args.data) {
            getString(R.string.fairy) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.fairy_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_fairy)
                binding.textTypeResults.text = getString(R.string.fairy).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.fire) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.fire_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_fire)
                binding.textTypeResults.text = getString(R.string.fire).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.fighting) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.fighting_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_fighting)
                binding.textTypeResults.text = getString(R.string.fighting).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.flying) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.flying_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_flying)
                binding.textTypeResults.text = getString(R.string.flying).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.poison) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.poison_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_poison)
                binding.textTypeResults.text = getString(R.string.poison).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.dragon) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.dragon_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_dragon)
                binding.textTypeResults.text = getString(R.string.dragon).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.grass) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.grass_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_grass)
                binding.textTypeResults.text = getString(R.string.grass).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.ground) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.ground_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_ground)
                binding.textTypeResults.text = getString(R.string.ground).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.rock) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.rock_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_rock)
                binding.textTypeResults.text = getString(R.string.rock).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.psychic) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.psychic_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_psychic)
                binding.textTypeResults.text = getString(R.string.psychic).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.water) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.water_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_water)
                binding.textTypeResults.text = getString(R.string.water).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.dark) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.dark_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_dark)
                binding.textTypeResults.text = getString(R.string.dark).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.ghost) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.ghost_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_ghost)
                binding.textTypeResults.text = getString(R.string.ghost).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.normal) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.normal_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_normal)
                binding.textTypeResults.text = getString(R.string.normal).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.steel) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.steel_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_steel)
                binding.textTypeResults.text = getString(R.string.steel).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.electric) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.electric_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_electric)
                binding.textTypeResults.text = getString(R.string.electric).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.bug) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.bug_background)
                binding.imageTypeResults.setImageResource(R.drawable.ic_bug)
                binding.textTypeResults.text = getString(R.string.bug).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
            }
            getString(R.string.ice) -> {
                binding.mainContraintTypeResult.setBackgroundResource(R.color.ice_background)
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
