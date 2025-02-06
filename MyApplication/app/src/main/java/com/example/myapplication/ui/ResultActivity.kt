package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Esta función activa el modo Edge-to-Edge
        setContentView(R.layout.activity_result)

        // Configuración de las ventanas y márgenes
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Establecer OnClickListener para el botón de inicio
        val buttonInicio = findViewById<Button>(R.id.button_inicio)
        buttonInicio.setOnClickListener {
            // Crear un Intent para iniciar LoginActivity
            val intent = Intent(this@ResultActivity, LoginActivity::class.java)
            startActivity(intent)
            finish() // Opcional: Cierra ResultActivity si ya no la necesitas
        }

        // Establecer OnClickListener para el botón de registro
        val buttonRegistro = findViewById<Button>(R.id.button_registro)
        buttonRegistro.setOnClickListener {
            // Crear un Intent para iniciar RegisterActivity
            val intent = Intent(this@ResultActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}