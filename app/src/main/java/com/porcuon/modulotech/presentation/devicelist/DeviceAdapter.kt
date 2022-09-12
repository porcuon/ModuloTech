package com.porcuon.modulotech.presentation.devicelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.porcuon.modulotech.databinding.ItemDeviceBinding
import com.porcuon.modulotech.domain.model.Device

class DeviceListAdapter(
    private val onDeviceClicked: (Device) -> Unit,
    private val onRemoveButtonClicked: (Device) -> Unit
) : ListAdapter<Device, DeviceViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDeviceBinding.inflate(layoutInflater, parent, false)

        return DeviceViewHolder(binding, onDeviceClicked, onRemoveButtonClicked)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class DiffUtilCallback : DiffUtil.ItemCallback<Device>() {

    override fun areItemsTheSame(
        oldItem: Device,
        newItem: Device
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Device,
        newItem: Device
    ): Boolean = oldItem == newItem
}