package com.porcuon.modulotech.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.porcuon.modulotech.presentation.core.BaseFragment
import com.porcuon.modulotech.R
import com.porcuon.modulotech.databinding.FragmentProfileBinding
import com.porcuon.modulotech.domain.model.Address
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.presentation.utils.format
import com.porcuon.modulotech.presentation.utils.observeEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val DATE_FORMAT = "dd.mm.yyyy"

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding?.toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> viewModel.onProfileEditButtonClicked()
            }

            true
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            userLiveData.observe(viewLifecycleOwner, ::handleUser)
            profileNavigationLiveData.observeEvent(viewLifecycleOwner, ::handleNavigation)
        }
    }

    private fun handleUser(user: User) {
        binding?.nameValueTextView?.text = getFullUserName(user.firstName, user.lastName)
        binding?.dateOfBirthValueTextView?.text = user.dateOfBirth.format(DATE_FORMAT)
        binding?.countryValueTextView?.text = user.address.country
        binding?.cityValueTextView?.text = user.address.city
        binding?.addressValueTextView?.text = getFormattedAddress(user.address)
        binding?.postalCodeValueTextView?.text = user.address.postalCode.toString()
    }

    private fun handleNavigation(navigation: ProfileNavigation) {
        when (navigation) {
            is ProfileNavigation.OpenProfileEdit -> openProfileEdit(navigation.user)
        }
    }

    private fun openProfileEdit(user: User) {
        val direction = ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment(user)
        findNavController().navigate(direction)
    }

    private fun getFullUserName(firstName: String, lastName: String): String {
        return "$firstName $lastName"
    }

    private fun getFormattedAddress(address: Address): String {
        return "${address.street}, ${address.streetCode}"
    }
}