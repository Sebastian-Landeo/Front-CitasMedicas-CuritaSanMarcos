package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Aquí debes usar binding.root

        // Configuramos el controlador de navegación
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Configuramos la barra de acción con el controlador de navegación
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        
        // Vinculamos el BottomNavigationView con el controlador de navegación
        binding.navView.setupWithNavController(navController)

    }
}