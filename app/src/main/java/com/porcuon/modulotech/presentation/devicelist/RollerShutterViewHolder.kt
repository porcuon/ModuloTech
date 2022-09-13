package com.porcuon.modulotech.presentation.devicelist

import android.view.View
import com.porcuon.modulotech.databinding.ItemRollerShutterBinding
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.RollerShutter

class RollerShutterViewHolder(
    private val view: View,
    private val onDeviceClicked: (Device) -> Unit,
    private val onRemoveButtonClicked: (Device) -> Unit
) : BaseDeviceViewHolder(view) {

    private val binding: ItemRollerShutterBinding by lazy {
        ItemRollerShutterBinding.bind(view)
    }

    override fun bind(device: Device) {
        if (device !is RollerShutter) return

        with(binding) {
            rollerShutterNameTextView.text = device.name
            positionValueTextView.text = device.position.toString()

            removeDeviceImageView.setOnClickListener {
                onRemoveButtonClicked(device)
            }

            binding.root.setOnClickListener {
                onDeviceClicked(device)
            }
        }
    }
}