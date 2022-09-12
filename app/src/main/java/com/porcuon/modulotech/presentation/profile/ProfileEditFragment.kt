package com.porcuon.modulotech.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.porcuon.modulotech.R
import com.porcuon.modulotech.data.mapper.NO_POSTAL_CODE
import com.porcuon.modulotech.presentation.core.BaseFragment
import com.porcuon.modulotech.databinding.FragmentProfileEditBinding
import com.porcuon.modulotech.domain.model.Address
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.presentation.utils.format
import com.porcuon.modulotech.presentation.utils.getDateFromFormattedDate
import com.porcuon.modulotech.presentation.utils.toStringOrEmpty
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

private const val DATE_FORMAT = "dd/mm/yyyy"

class ProfileEditFragment : BaseFragment<FragmentProfileEditBinding>(FragmentProfileEditBinding::inflate) {

    private val profileEditViewModel: ProfileEditViewModel by viewModel()
    private val profileViewModel: ProfileViewModel by sharedViewModel()
    private val user: User by lazy {
        ProfileEditFragmentArgs.fromBundle(requireArguments()).user
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViews()
        observeViewModels()
    }

    private fun setupToolbar() {
        binding?.toolbar?.apply {
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_submit -> validateFields()
                }
                true
            }
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupViews() {
        binding?.firstNameEditText?.setText(user.firstName)
        binding?.lastNameEditText?.setText(user.lastName)
        binding?.dateOfBirthEditText?.setText(user.dateOfBirth.format(DATE_FORMAT))
        binding?.countryEditText?.setText(user.address.country)
        binding?.cityEditText?.setText(user.address.city)
        binding?.streetEditText?.setText(user.address.street)
        binding?.streetCodeEditText?.setText(user.address.streetCode)
        binding?.postalCodeEditText?.setText(user.address.postalCode.toString())
    }

    private fun observeViewModels() {
        with(profileEditViewModel) {
            getUpdatedUserLiveData().observe(viewLifecycleOwner, ::onUserUpdated)
        }
    }

    private fun onUserUpdated(updatedUser: User) {
        profileViewModel.onUserUpdated(updatedUser)
        findNavController().popBackStack()
    }

    private fun validateFields() {

    }

    private fun updateUser() {
        val dateOfBirth: Date = getDateFromFormattedDate(
            text = binding?.dateOfBirthEditText?.text.toStringOrEmpty(),
            pattern = DATE_FORMAT
        )
        val updatedAddress = Address(
            city = binding?.cityEditText?.text.toStringOrEmpty(),
            postalCode = binding?.postalCodeEditText?.text.toString().toIntOrNull() ?: NO_POSTAL_CODE,
            street = binding?.streetEditText?.text.toStringOrEmpty(),
            streetCode = binding?.streetCodeEditText?.text.toStringOrEmpty(),
            country = binding?.countryEditText?.text.toStringOrEmpty()
        )
        val updatedUser = User(
            id = user.id,
            firstName = binding?.firstNameEditText?.text.toStringOrEmpty(),
            lastName = binding?.lastNameEditText?.text.toStringOrEmpty(),
            address = updatedAddress,
            dateOfBirth = dateOfBirth
        )
        profileEditViewModel.onUserValidated(updatedUser)
    }
}