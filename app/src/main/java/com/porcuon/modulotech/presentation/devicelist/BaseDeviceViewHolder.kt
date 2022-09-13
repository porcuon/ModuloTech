package com.porcuon.modulotech.presentation.devicelist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.porcuon.modulotech.domain.model.Device

abstract class BaseDeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(device: Device)
}