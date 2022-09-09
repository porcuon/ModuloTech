package com.porcuon.modulotech.devices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.porcuon.modulotech.databinding.ItemDeviceBinding

class DeviceListAdapter : ListAdapter<Device, DeviceViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDeviceBinding.inflate(layoutInflater, parent, false)

        return DeviceViewHolder(binding)
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