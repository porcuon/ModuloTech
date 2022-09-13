package com.porcuon.modulotech.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porcuon.modulotech.presentation.utils.Event
import com.porcuon.modulotech.domain.model.Result
import com.porcuon.modulotech.domain.model.User
import com.porcuon.modulotech.domain.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _profileNavigationLiveData = MutableLiveData<Event<ProfileNavigation>>()
    private val _userLiveData = MutableLiveData<User>()

    val profileNavigationLiveData: LiveData<Event<ProfileNavigation>> = _profileNavigationLiveData
    val userLiveData: LiveData<User> = _userLiveData

    init {
        loadUser()
    }

    fun onUserUpdated(updatedUser: User) {
        _userLiveData.value = updatedUser
    }

    fun onProfileEditButtonClicked() {
        val user: User = _userLiveData.value ?: return
        _profileNavigationLiveData.value = Event(ProfileNavigation.OpenProfileEdit(user))
    }

    private fun loadUser() {
        viewModelScope.launch {
            val userResult: Result<User> = userRepository.getUser()

            when (userResult) {
                is Result.Success -> _userLiveData.value = userResult.result
                is Result.Error -> Unit
            }
        }
    }
}