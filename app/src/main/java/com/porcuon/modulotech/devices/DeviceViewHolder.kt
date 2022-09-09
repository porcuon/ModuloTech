package com.porcuon.modulotech.devices

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.porcuon.modulotech.R
import com.porcuon.modulotech.databinding.ItemDeviceBinding

class DeviceViewHolder(
    private val binding: ItemDeviceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(device: Device) {
        with(binding) {
            deviceNameTextView.text = device.name
            deviceTypeTextView.text = getDeviceTypeText(device)
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