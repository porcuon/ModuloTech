package com.porcuon.modulotech.presentation.filters

import android.os.Bundle
import android.view.View
import com.porcuon.modulotech.presentation.core.BaseBottomSheetFragment
import com.porcuon.modulotech.databinding.FragmentDeviceFiltersBinding
import com.porcuon.modulotech.presentation.devicelist.DevicesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceFiltersFragment : BaseBottomSheetFragment<FragmentDeviceFiltersBinding>(FragmentDeviceFiltersBinding::inflate) {

    private val devicesViewModel: DevicesViewModel by sharedViewModel()
    private val deviceFiltersViewModel: DeviceFiltersViewModel by viewModel()
    private val deviceFilterAdapter: DeviceFilterAdapter by lazy {
        DeviceFilterAdapter(deviceFiltersViewModel::onDeviceFilterClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        deviceFiltersViewModel.onDestroy()
        devicesViewModel.onFiltersApplied()
    }

    private fun setupViews() {
        binding?.filtersRecyclerView?.adapter = deviceFilterAdapter
    }

    private fun observeViewModel() {
        with(deviceFiltersViewModel) {
            deviceFiltersLiveData.observe(viewLifecycleOwner, deviceFilterAdapter::submitList)
        }
    }
}