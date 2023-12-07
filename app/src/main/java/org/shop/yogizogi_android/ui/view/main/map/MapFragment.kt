package org.shop.yogizogi_android.ui.view.main.map

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.gun0912.tedpermission.rx3.TedPermission
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.Resource
import org.shop.yogizogi_android.databinding.FragmentMapBinding
import org.shop.yogizogi_android.ui.base.BaseFragment

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(
    MapViewModel::class.java,
    R.layout.fragment_map
), OnMapReadyCallback {
    private val navArgs: MapFragmentArgs by navArgs()
    private lateinit var naverMap: NaverMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationSource: FusedLocationSource

    private val mapFragment by lazy { MapFragment() }

    override fun initView() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        requestPermissions()
        setMapFragment()
    }

    override fun initAfterBinding() {
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userMapProcess.collect { result ->
                when (result) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        val mapData = result.data
                        if (mapData.isNotEmpty()) {
                            for (mapItem in mapData) {
                                val marker = Marker()
                                marker.position = LatLng(
                                    mapItem.restaurantDetails.coordinate.latitude.toDouble(),
                                    mapItem.restaurantDetails.coordinate.longitude.toDouble()
                                )
                                marker.map = naverMap
                            }
                        }
                    }

                    is Resource.Error -> {
                        showToast(result.message)
                    }

                    null -> {}
                }
            }
        }
    }

    private fun setMapFragment() {
        val fm = childFragmentManager
        fm.beginTransaction().replace(R.id.fragment_naver_map, mapFragment).commit()
        mapFragment.getMapAsync(this)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.None
        naverMap.uiSettings.isLocationButtonEnabled = true

        val args = navArgs.storeDetail
        if (args != null) {
            Log.d("MapFragment - OnMapReady", "navArgs 존재함!")
            val marker = Marker()
            marker.position = LatLng(
                args.restaurantDetails.coordinate.latitude.toDouble(),
                args.restaurantDetails.coordinate.longitude.toDouble()
            )
            marker.map = naverMap
            naverMap.cameraPosition = CameraPosition(marker.position, 16.0)
        } else {
            Log.d("MapFragment - OnMapReady", "navArgs 존재 안 함!")
            naverMap.locationTrackingMode = LocationTrackingMode.Follow

            viewModel.getUserMap()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("MapFragment", "onPause")
        viewModel.initMapProcess()
    }

    override fun onResume() {
        super.onResume()
        Log.d("MapFragment", "onResume")
        val args = navArgs.storeDetail
        if (args != null) {
            Log.d("MapFragment", "navArgs 존재함!")
        } else {
            Log.d("MapFragment", "navArgs 존재 안 함!")
            viewModel.getUserMap()
        }
    }

    @SuppressLint("CheckResult")
    private fun requestPermissions() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        TedPermission.create()
            .setRationaleTitle(resources.getString(R.string.location_permission_title))
            .setRationaleMessage(resources.getString(R.string.location_permission_message))
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .request()
            .subscribe({ tedPermissionResult ->
                if (!tedPermissionResult.isGranted) {
                    showToast(resources.getString(R.string.location_permission_denied))
                }
            }) { throwable -> Log.e("MapFragment-requestPermission", throwable.message.toString()) }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}