package org.shop.yogizogi_android.ui.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.databinding.ActivityProfileBinding

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityProfileBinding?>(this, R.layout.activity_profile)
                .apply {
                    lifecycleOwner = this@ProfileActivity
                }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_profile) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_profile)

        navGraph.setStartDestination(R.id.profileNicknameFragment)
        navController.graph = navGraph
    }
}