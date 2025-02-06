package com.example.myapplication.ui

import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.os.Handler
import android.widget.Button
import android.widget.AdapterView
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

        // Obtener el Spinner de tipo de usuario (Estudiante, Docente, Externo)
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

        // Obtener el EditText del correo y del código
        val editTextCorreo = findViewById<EditText>(R.id.TextCorreo)
        val editTextCodigo = findViewById<EditText>(R.id.TextCodigo)
        val btnValidarCorreo = findViewById<Button>(R.id.buttonRegistro)

        // Función para habilitar o deshabilitar el EditText de código según el tipo de usuario
        fun updateCodigoFieldState() {
            val tipoUsuario = spinnerTipoUsuario.selectedItem.toString()

            // Si el tipo de usuario es "Externo", desactivar el EditText de código
            if (tipoUsuario == "Externo") {
                editTextCodigo.isEnabled = false  // Deshabilita el EditText de código
                editTextCodigo.isFocusable = false  // No permite interactuar con el campo
            } else {
                // Si el tipo de usuario no es "Externo", habilitar el EditText de código
                editTextCodigo.isEnabled = true
                editTextCodigo.isFocusable = true
            }
        }

        // Llamar a la función para verificar el estado inicial
        updateCodigoFieldState()

        // Configurar el Listener para el Spinner de tipo de usuario
        spinnerTipoUsuario.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: android.view.View?, position: Int, id: Long) {
                // Verificar y actualizar el estado del campo de código
                updateCodigoFieldState()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No hace nada si no hay selección
            }
        }

        // Lógica para la validación del correo cuando se presiona el botón
        btnValidarCorreo.setOnClickListener {
            val tipoUsuario = spinnerTipoUsuario.selectedItem.toString()
            val correo = editTextCorreo.text.toString()

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

            // Redirigir a la actividad RegisterSuccessfulActivity
            val intent = Intent(this, RegisterSuccessfulActivity::class.java)
            startActivity(intent)
        }
    }
}
