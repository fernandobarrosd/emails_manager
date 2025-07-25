package com.fernando.emailsmanager_gerenciadorde_emails.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.ActivityMainBinding
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.getNavController
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.observeLiveData
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.setVisibilityToGone
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.setVisibilityToVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    private val navController: NavController by getNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViewCompat()
        setupBottomNavigation()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding?.mainToolbar?.setupWithNavController(navController)
    }

    private fun setupBottomNavigation() {
        binding?.bottomNavigation?.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        observeLiveData(mainViewModel.isShowBottomBar) { isShowBottomBar ->
            if (isShowBottomBar) {
                showBottomBar()
                return@observeLiveData
            }
            hideBottomBar()
        }
    }


    private fun initListeners() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            mainViewModel.onDestinationChange(destination.id)
        }
    }

    private fun hideBottomBar() {
        binding?.bottomNavigation?.setVisibilityToGone()
    }

    private fun showBottomBar() {
        binding?.bottomNavigation?.setVisibilityToVisible()
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