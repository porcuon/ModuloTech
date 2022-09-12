package com.porcuon.modulotech.presentation.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.porcuon.modulotech.databinding.ItemFilterBinding
import com.porcuon.modulotech.domain.model.DeviceFilter

class DeviceFilterAdapter(
    private val onDeviceFilterClicked: (DeviceFilter) -> Unit
) : ListAdapter<DeviceFilter, DeviceFilterViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceFilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterBinding.inflate(layoutInflater, parent, false)

        return DeviceFilterViewHolder(binding, onDeviceFilterClicked)
    }

    override fun onBindViewHolder(holder: DeviceFilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class DiffUtilCallback : DiffUtil.ItemCallback<DeviceFilter>() {

    override fun areItemsTheSame(
        oldItem: DeviceFilter,
        newItem: DeviceFilter
    ): Boolean = oldItem.deviceType == newItem.deviceType

    override fun areContentsTheSame(
        oldItem: DeviceFilter,
        newItem: DeviceFilter
    ): Boolean = oldItem == newItem
}