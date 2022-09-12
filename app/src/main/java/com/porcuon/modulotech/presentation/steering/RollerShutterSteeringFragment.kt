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
        binding?.positionSlider?.apply {
            value = device.position.toFloat()
            valueFrom = RollerShutter.MIN_POSITION.toFloat()
            valueTo = RollerShutter.MAX_POSITION.toFloat()
        }

        binding?.saveButton?.setOnClickListener {
            deviceSteeringViewModel.onUpdateRollerShutterClicked(
                position = binding?.positionSlider?.value?.toInt() ?: RollerShutter.MIN_POSITION
            )
        }
    }
}