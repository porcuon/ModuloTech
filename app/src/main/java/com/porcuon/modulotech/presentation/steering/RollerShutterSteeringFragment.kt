package com.porcuon.modulotech.presentation.steering

import android.os.Bundle
import android.view.View
import com.porcuon.modulotech.databinding.FragmentRollerShutterSteeringBinding
import com.porcuon.modulotech.domain.model.RollerShutter

class RollerShutterSteeringFragment :
    BaseDeviceSteeringFragment<FragmentRollerShutterSteeringBinding, RollerShutter>(
        FragmentRollerShutterSteeringBinding::inflate
    ) {

    override val device: RollerShutter
        get() = RollerShutterSteeringFragmentArgs.fromBundle(requireArguments()).rollerShutter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.rollerShutterNameTextView?.text = device.name
        binding?.positionValueTextView?.text = device.position.toString()

        binding?.positionSlider?.apply {
            value = device.position.toFloat()
            valueFrom = RollerShutter.MIN_POSITION.toFloat()
            valueTo = RollerShutter.MAX_POSITION.toFloat()

            addOnChangeListener { _, value, _ ->
                binding?.positionValueTextView?.text = value.toInt().toString()
            }
        }

        binding?.saveButton?.setOnClickListener {
            deviceSteeringViewModel.onUpdateRollerShutterClicked(
                position = binding?.positionSlider?.value?.toInt() ?: RollerShutter.MIN_POSITION
            )
        }
    }
}