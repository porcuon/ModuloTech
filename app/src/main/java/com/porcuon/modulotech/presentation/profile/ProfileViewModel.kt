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

    private val profileNavigationLiveData = MutableLiveData<Event<ProfileNavigation>>()
    private val userLiveData = MutableLiveData<User>()

    init {
        loadUser()
    }

    fun getProfileNavigationLiveData(): LiveData<Event<ProfileNavigation>> = profileNavigationLiveData
    fun getUserLiveData(): LiveData<User> = userLiveData

    fun onUserUpdated(updatedUser: User) {
        userLiveData.value = updatedUser
    }

    fun onProfileEditButtonClicked() {
        val user: User = userLiveData.value ?: return
        profileNavigationLiveData.value = Event(ProfileNavigation.OpenProfileEdit(user))
    }

    private fun loadUser() {
        viewModelScope.launch {
            val userResult: Result<User> = userRepository.getUser()

            when (userResult) {
                is Result.Success -> userLiveData.value = userResult.result
                is Result.Error -> Unit
            }
        }
    }
}