package org.shop.yogizogi_android.ui.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
            }

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment
        binding.mainBnv.setupWithNavController(navHostFragment.navController)
    }
}