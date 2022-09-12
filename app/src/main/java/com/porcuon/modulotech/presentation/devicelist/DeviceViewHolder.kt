package com.porcuon.modulotech.presentation.devicelist

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.porcuon.modulotech.R
import com.porcuon.modulotech.databinding.ItemDeviceBinding
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter

class DeviceViewHolder(
    private val binding: ItemDeviceBinding,
    private val onDeviceClicked: (Device) -> Unit,
    private val onRemoveButtonClicked: (Device) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(device: Device) {
        with(binding) {
            deviceNameTextView.text = device.name
            deviceTypeTextView.text = getDeviceTypeText(device)

            removeDeviceImageView.setOnClickListener {
                onRemoveButtonClicked(device)
            }

            root.setOnClickListener {
                onDeviceClicked(device)
            }
        }
    }

    private fun getDeviceTypeText(device: Device): String {
        @StringRes
        val stringResId: Int = when (device) {
            is Light -> R.string.item_device_light
            is Heater -> R.string.item_device_heater
            is RollerShutter -> R.string.item_device_roller_shutter
        }

        return binding.root.resources.getString(stringResId)
    }
}