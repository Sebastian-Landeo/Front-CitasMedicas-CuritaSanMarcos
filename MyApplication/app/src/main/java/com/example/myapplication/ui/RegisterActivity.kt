package com.example.myapplication.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.os.Handler
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

@Suppress("DEPRECATION")
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

        // Obtener el Spinner de tipo de usuario (Estudiante o Docente)
        val spinnerTipoUsuario = findViewById<Spinner>(R.id.TextEstado)

        // Crear un ArrayAdapter para el Spinner usando el string-array de strings.xml
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.estado_opciones, // Nombre del recurso string-array
            R.layout.spinner_item // Diseño del Spinner
        )

        // Especificar cómo se deben mostrar las opciones al desplegar
        adapter.setDropDownViewResource(R.layout.spinner_item)

        // Establecer el Adapter al Spinner
        spinnerTipoUsuario.adapter = adapter

        // Obtener los elementos del layout
        val editTextCorreo = findViewById<EditText>(R.id.TextCorreo)
        val btnValidarCorreo = findViewById<Button>(R.id.buttonRegistro)

        // Lógica para la validación del correo cuando se presiona el botón
        btnValidarCorreo.setOnClickListener {
            val tipoUsuario = spinnerTipoUsuario.selectedItem.toString()
            val correo = editTextCorreo.text.toString()

            // Validar si el correo termina en @unmsm.edu.pe
            // Validar si el correo termina en @unmsm.edu.pe
            if (tipoUsuario == "Estudiante" || tipoUsuario == "Docente") {
                if (!correo.endsWith("@unmsm.edu.pe")) {
                    // Mostrar el error en el EditText si el correo no pertenece a la institución
                    editTextCorreo.error = "Correo ingresado no pertenece a la institución"

                    // Usar Handler para eliminar el error después de unos segundos (2 segundos)
                    Handler().postDelayed({
                        editTextCorreo.error = null
                    }, 2000)  // 2000 milisegundos = 2 segundos
                } else {
                    // Limpiar el error si el correo es válido
                    editTextCorreo.error = null
                }
            }
        }
    }
}

