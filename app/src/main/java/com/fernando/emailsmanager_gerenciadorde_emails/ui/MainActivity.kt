package com.fernando.emailsmanager_gerenciadorde_emails.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.ui.setupWithNavController
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.ActivityMainBinding
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViewCompat()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding?.let { bindingNotNull ->
            val navController = findNavController(R.id.nav_host_fragment)
            bindingNotNull.bottomNavigation.setupWithNavController(navController)
        }
    }

    private fun setupViewCompat() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}