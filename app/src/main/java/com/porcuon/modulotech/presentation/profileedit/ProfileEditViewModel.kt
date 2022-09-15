package com.porcuon.modulotech.presentation.profileedit

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.domain.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileEditViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _updatedUserLiveData = MutableLiveData<User>()

    val updatedUserLiveData: LiveData<User> = _updatedUserLiveData

    fun onUserValidated(user: User) {
        viewModelScope.launch {
            val updatedUserResult: Result<User> = userRepository.updateUser(user)

            @SuppressLint("NullSafeMutableLiveData")
            when (updatedUserResult) {
                is Result.Success -> _updatedUserLiveData.value = updatedUserResult.result
                is Result.Error -> Unit
            }
        }
    }
}