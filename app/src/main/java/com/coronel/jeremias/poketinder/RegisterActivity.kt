package com.coronel.jeremias.poketinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coronel.jeremias.poketinder.databinding.ActivityRegisterBinding
import android.util.Patterns

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa el repositorio de SharedPreferences
        sharedPreferencesRepository = SharedPreferencesRepository().apply {
            setSharedPreference(this@RegisterActivity)
        }

        setupUI()
    }

    private fun setupUI() {
        // Configura el botón de regreso
        binding.btnBackClose.setOnClickListener {
            finish()  // Cierra la actividad actual y vuelve a la anterior
        }

        // Configura el botón de registro
        binding.btnRegister.setOnClickListener {
            registerNewUser() // Llama directamente a registerNewUser sin pasar la vista
        }

        // Configura el botón para redirigir al login si ya tiene cuenta
        binding.btntengounacuenta.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))  // Redirige al login
        }
    }

    private fun registerNewUser() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val confirmPassword = binding.edtPassword2.text.toString()

        // Verificar si el email es válido
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return
        }

        // Verificar si la contraseña es válida
        if (!isValidPassword(password)) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        // Verificar si las contraseñas coinciden
        if (!doPasswordsMatch(password, confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar el email y la contraseña en SharedPreferences
        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)

        // Mostrar mensaje de éxito y redirigir al LoginActivity
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()  // Finaliza la actividad actual para evitar volver a ella con el botón atrás
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()  // Valida el formato del correo electrónico
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8  // Verifica que la contraseña tenga al menos 8 caracteres
    }

    private fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword  // Verifica si las contraseñas coinciden
    }
}
