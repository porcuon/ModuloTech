package com.porcuon.modulotech.presentation.profileedit

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.porcuon.modulotech.R
import com.porcuon.modulotech.data.mapper.NO_POSTAL_CODE
import com.porcuon.modulotech.presentation.core.BaseFragment
import com.porcuon.modulotech.databinding.FragmentProfileEditBinding
import com.porcuon.modulotech.domain.model.Address
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.presentation.profile.ProfileViewModel
import com.porcuon.modulotech.presentation.utils.format
import com.porcuon.modulotech.presentation.utils.getDateFromString
import com.porcuon.modulotech.presentation.utils.toStringOrEmpty
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

private const val DATE_FORMAT = "dd/mm/yyyy"
private const val DATE_FORMAT_REGEX = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$"

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
            updatedUserLiveData.observe(viewLifecycleOwner, ::onUserUpdated)
        }
    }

    private fun onUserUpdated(updatedUser: User) {
        profileViewModel.onUserUpdated(updatedUser)
        findNavController().popBackStack()
    }

    private fun validateFields() {
        var isValidationFailed = false

        binding?.editTextContainer?.children?.forEach { child ->
            if (child !is TextInputLayout) return@forEach

            val text: String = child.editText?.text.toStringOrEmpty()
            val errorText: String? = when (child == binding?.dateOfBirthTextInputLayout) {
                true -> validateDateOfBirthFormat(text)
                else -> validateEmptyField(text)
            }

            child.error = errorText
            child.isErrorEnabled = child.error != null
            isValidationFailed = isValidationFailed || child.isErrorEnabled
        }

        if (!isValidationFailed) {
            updateUser()
        }
    }

    private fun validateEmptyField(text: String): String? {
        return when (text.isBlank()) {
            true -> getString(R.string.fragment_profile_edit_error_empty_text)
            else -> null
        }
    }

    private fun validateDateOfBirthFormat(text: String): String? {
        val regex = Regex(DATE_FORMAT_REGEX)

        return when (!regex.matches(text)) {
            true -> getString(R.string.fragment_profile_edit_error_date_format, DATE_FORMAT)
            else -> null
        }
    }

    private fun updateUser() {
        val dateOfBirth: Date = getDateFromString(
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