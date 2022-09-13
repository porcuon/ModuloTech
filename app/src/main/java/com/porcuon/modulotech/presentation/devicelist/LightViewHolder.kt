package com.porcuon.modulotech.presentation.devicelist

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.porcuon.modulotech.R
import com.porcuon.modulotech.databinding.ItemLightBinding
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.DeviceMode
import com.porcuon.modulotech.domain.model.Light

class LightViewHolder(
    private val view: View,
    private val onDeviceClicked: (Device) -> Unit,
    private val onRemoveButtonClicked: (Device) -> Unit
) : BaseDeviceViewHolder(view) {

    private val binding: ItemLightBinding by lazy {
        ItemLightBinding.bind(view)
    }

    override fun bind(device: Device) {
        if (device !is Light) return

        with(binding) {
            lightNameTextView.text = device.name
            intensityValueTextView.text = device.intensity.toString()
            lightModeTextView.text = getHeaterModeText(device.deviceMode)
            lightModeTextView.setTextColor(getHeaterModeTextColor(device.deviceMode))

            removeDeviceImageView.setOnClickListener {
                onRemoveButtonClicked(device)
            }

            binding.root.setOnClickListener {
                onDeviceClicked(device)
            }
        }
    }

    private fun getHeaterModeText(deviceMode: DeviceMode): String {
        @StringRes
        val stringResId: Int = when (deviceMode) {
            DeviceMode.ON -> R.string.item_light_mode_on
            DeviceMode.OFF -> R.string.item_light_mode_off
        }

        return view.resources.getString(stringResId)
    }

    private fun getHeaterModeTextColor(deviceMode: DeviceMode): Int {
        @ColorRes
        val colorResId: Int = when (deviceMode) {
            DeviceMode.ON -> android.R.color.holo_green_dark
            DeviceMode.OFF -> android.R.color.holo_red_dark
        }

        return ContextCompat.getColor(view.context, colorResId)
    }
}