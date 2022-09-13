package com.porcuon.modulotech.presentation.devicelist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.porcuon.modulotech.presentation.core.BaseFragment
import com.porcuon.modulotech.R
import com.porcuon.modulotech.databinding.FragmentDevicesBinding
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter
import com.porcuon.modulotech.presentation.utils.observeEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DevicesFragment : BaseFragment<FragmentDevicesBinding>(FragmentDevicesBinding::inflate) {

    private val viewModel: DevicesViewModel by sharedViewModel()
    private val deviceListAdapter: DeviceListAdapter by lazy {
        DeviceListAdapter(
            onDeviceClicked = viewModel::onDeviceClicked,
            onRemoveButtonClicked = viewModel::onRemoveButtonClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding?.recyclerView?.adapter = deviceListAdapter
        binding?.recyclerView?.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        binding?.toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_filter -> viewModel.onFilterClicked()
            }

            true
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            devicesLiveData.observe(viewLifecycleOwner, deviceListAdapter::submitList)
            navigationLiveData.observeEvent(viewLifecycleOwner, ::handleNavigation)
        }
    }

    private fun handleNavigation(navigation: DevicesNavigation) {
        when (navigation) {
            is DevicesNavigation.OpenFilters -> openFilters()
            is DevicesNavigation.OpenLightSteering -> openLightSteering(navigation.light)
            is DevicesNavigation.OpenHeaterSteering -> openHeaterSteering(navigation.heater)
            is DevicesNavigation.OpenRollerShutterSteering -> openRollerShutterSteering(navigation.rollerShutter)
        }
    }

    private fun openFilters() {
        findNavController().navigate(R.id.action_devicesFragment_to_deviceFiltersFragment)
    }

    private fun openLightSteering(light: Light) {
        val direction = DevicesFragmentDirections.actionDevicesFragmentToLightSteeringFragment(light)
        findNavController().navigate(direction)
    }

    private fun openHeaterSteering(heater: Heater) {
        val direction = DevicesFragmentDirections.actionDevicesFragmentToHeaterSteeringFragment(heater)
        findNavController().navigate(direction)
    }

    private fun openRollerShutterSteering(rollerShutter: RollerShutter) {
        val direction = DevicesFragmentDirections.actionDevicesFragmentToRollerShutterSteeringFragment(rollerShutter)
        findNavController().navigate(direction)
    }
}