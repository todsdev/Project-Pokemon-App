package com.tods.project_pokemon.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tods.project_pokemon.R
import com.tods.project_pokemon.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

class SplashActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configBinding()
        configSplash()
    }

    private fun configSplash() = with(binding) {
        textPokemonSplash.alpha = 0f
        textPokemonSplash.animate().setDuration(3000).alpha(1f).withEndAction {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }
    }

    private fun configBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}