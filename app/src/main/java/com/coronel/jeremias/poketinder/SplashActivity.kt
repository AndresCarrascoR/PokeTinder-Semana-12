package com.coronel.jeremias.poketinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.rommansabbir.animationx.Attention
import com.rommansabbir.animationx.animationXAttention
import com.coronel.jeremias.poketinder.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Muestra animación y configura el retraso para cambiar a la pantalla de inicio de sesión
        showAnimationLogo()
        transitionToLogin()
    }

    private fun showAnimationLogo() {
        // Inicia la animación con un retraso de 1 segundo
        binding.imvLogo.postDelayed({
            binding.imvLogo.animationXAttention(Attention.ATTENTION_RUBERBAND)
        }, 1000)
    }

    private fun transitionToLogin() {
        // Cambia a la pantalla de Login después de 4 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}
