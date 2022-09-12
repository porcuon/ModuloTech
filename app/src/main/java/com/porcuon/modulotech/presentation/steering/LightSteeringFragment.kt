package com.porcuon.modulotech.presentation.steering

import android.os.Bundle
import android.view.View
import com.porcuon.modulotech.databinding.FragmentLightSteeringBinding
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.Light

class LightSteeringFragment : BaseDeviceSteeringFragment<FragmentLightSteeringBinding, Light>(FragmentLightSteeringBinding::inflate) {

    override val device: Light
        get() = LightSteeringFragmentArgs.fromBundle(requireArguments()).light

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.modeSwitch?.isChecked = device.deviceMode == DeviceMode.ON

        binding?.intensitySlider?.apply {
            value = device.intensity.toFloat()
            valueFrom = Light.MIN_INTENSITY.toFloat()
            valueTo = Light.MAX_INTENSITY.toFloat()
        }

        binding?.saveButton?.setOnClickListener {
            deviceSteeringViewModel.onUpdateLightClicked(
                isDeviceOn = binding?.modeSwitch?.isChecked == true,
                intensity = binding?.intensitySlider?.value?.toInt() ?: Light.MIN_INTENSITY
            )
        }
    }
}