package com.porcuon.modulotech.presentation.devicelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.porcuon.modulotech.R
import com.porcuon.modulotech.domain.model.Device
import com.porcuon.modulotech.domain.model.Heater
import com.porcuon.modulotech.domain.model.Light
import com.porcuon.modulotech.domain.model.RollerShutter

private const val ITEM_LIGHT_LAYOUT_ID = R.layout.item_light
private const val ITEM_HEATER_LAYOUT_ID = R.layout.item_heater
private const val ITEM_ROLLER_SHUTTER_LAYOUT_ID = R.layout.item_roller_shutter

class DeviceListAdapter(
    private val onDeviceClicked: (Device) -> Unit,
    private val onRemoveButtonClicked: (Device) -> Unit
) : ListAdapter<Device, BaseDeviceViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            ITEM_LIGHT_LAYOUT_ID -> LightViewHolder(view, onDeviceClicked, onRemoveButtonClicked)
            ITEM_HEATER_LAYOUT_ID -> HeaterViewHolder(view, onDeviceClicked, onRemoveButtonClicked)
            ITEM_ROLLER_SHUTTER_LAYOUT_ID -> RollerShutterViewHolder(view, onDeviceClicked, onRemoveButtonClicked)
            else -> throw IllegalArgumentException("viewType is not supported")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Light -> R.layout.item_light
            is Heater -> R.layout.item_heater
            is RollerShutter -> R.layout.item_roller_shutter
        }
    }

    override fun onBindViewHolder(holder: BaseDeviceViewHolder, position: Int) {
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