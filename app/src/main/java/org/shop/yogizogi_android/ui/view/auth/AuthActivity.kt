package org.shop.yogizogi_android.ui.view.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.ActivityAuthBinding

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView<ActivityAuthBinding?>(this, R.layout.activity_auth)
            .apply {
                lifecycleOwner = this@AuthActivity
            }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_auth) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_auth)

        navGraph.setStartDestination(R.id.initialFragment)
        navController.graph = navGraph
    }
}