package com.example.myapplication.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa el modo Edge-to-Edge
        setContentView(R.layout.activity_register)

        // Configuración de las ventanas y márgenes
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener el Spinner desde el layout
        val spinner = findViewById<Spinner>(R.id.TextEstado)

        // Crear un ArrayAdapter para el Spinner usando el string-array de strings.xml
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.estado_opciones, // Nombre del recurso string-array
            R.layout.spinner_item// Diseño del Spinner
        )

        // Especificar cómo se deben mostrar las opciones al desplegar
        adapter.setDropDownViewResource(R.layout.spinner_item)

        // Establecer el Adapter al Spinner
        spinner.adapter = adapter
    }
}