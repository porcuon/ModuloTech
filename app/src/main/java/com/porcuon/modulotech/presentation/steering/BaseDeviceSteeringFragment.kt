package com.porcuon.modulotech.presentation.steering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.porcuon.modulotech.presentation.core.BaseBottomSheetFragment
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.presentation.devicelist.DevicesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

abstract class BaseDeviceSteeringFragment<V : ViewBinding, D : Device>(
    bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> V
) : BaseBottomSheetFragment<V>(bindingInflater) {

    protected abstract val device: D
    protected val deviceSteeringViewModel: DeviceSteeringViewModel by viewModel {
        parametersOf(device)
    }

    private val devicesViewModel: DevicesViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
    }

    private fun observeViewModels() {
        with(deviceSteeringViewModel) {
            getUpdatedDeviceLiveData().observe(viewLifecycleOwner, ::onUpdatedDeviceReceived)
        }
    }

    private fun onUpdatedDeviceReceived(updatedDevice: Device) {
        devicesViewModel.onDeviceUpdated(updatedDevice)
        dismiss()
    }
}