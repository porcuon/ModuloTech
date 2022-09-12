package com.porcuon.modulotech.presentation.steering

import android.os.Bundle
import android.view.View
import com.porcuon.modulotech.databinding.FragmentHeaterSteeringBinding
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.Heater

private const val TEMPERATURE_CHANGE_STEP = 0.5

class HeaterSteeringFragment :
    BaseDeviceSteeringFragment<FragmentHeaterSteeringBinding, Heater>(FragmentHeaterSteeringBinding::inflate) {

    override val device: Heater
        get() = HeaterSteeringFragmentArgs.fromBundle(requireArguments()).heater

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.modeSwitch?.isChecked = device.deviceMode == DeviceMode.ON
        binding?.temperatureValueTextView?.text = device.temperature.toString()

        binding?.saveButton?.setOnClickListener {
            deviceSteeringViewModel.onUpdateHeaterClicked(
                isDeviceOn = binding?.modeSwitch?.isChecked == true,
                temperature = getCurrentTemperature()
            )
        }
        binding?.increaseTemperatureImageView?.setOnClickListener {
            increaseTemperature()
        }
        binding?.decreaseTemperatureImageView?.setOnClickListener {
            decreaseTemperature()
        }
    }

    private fun increaseTemperature() {
        val currentTemperature: Double = getCurrentTemperature()
        val newTemperature = (currentTemperature + TEMPERATURE_CHANGE_STEP).coerceAtMost(Heater.MAX_TEMPERATURE)

        binding?.temperatureValueTextView?.text = newTemperature.toString()
    }

    private fun decreaseTemperature() {
        val currentTemperature: Double = getCurrentTemperature()
        val newTemperature = (currentTemperature - TEMPERATURE_CHANGE_STEP).coerceAtLeast(Heater.MIN_TEMPERATURE)

        binding?.temperatureValueTextView?.text = newTemperature.toString()
    }

    private fun getCurrentTemperature(): Double {
        return binding?.temperatureValueTextView?.text
            ?.toString()
            ?.toDoubleOrNull()
            ?: Heater.MIN_TEMPERATURE
    }
}