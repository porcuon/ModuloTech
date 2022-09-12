package com.porcuon.modulotech.presentation.filters

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.porcuon.modulotech.R
import com.porcuon.modulotech.databinding.ItemFilterBinding
import com.porcuon.modulotech.domain.model.DeviceType
import com.porcuon.modulotech.domain.model.DeviceFilter

class DeviceFilterViewHolder(
    private val binding: ItemFilterBinding,
    private val onDeviceFilterClicked: (DeviceFilter) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(deviceFilter: DeviceFilter) {
        with(binding) {
            filterCheckBox.isChecked = deviceFilter.isSelected
            filterCheckBox.text = getDeviceFilterText(deviceFilter.deviceType)

            filterCheckBox.setOnClickListener {
                onDeviceFilterClicked(deviceFilter)
            }
        }
    }

    private fun getDeviceFilterText(deviceType: DeviceType): String {
        @StringRes
        val stringResId: Int = when (deviceType) {
            DeviceType.Light -> R.string.item_device_light
            DeviceType.Heater -> R.string.item_device_heater
            DeviceType.RollerShutter -> R.string.item_device_roller_shutter
        }

        return binding.root.resources.getString(stringResId)
    }
}