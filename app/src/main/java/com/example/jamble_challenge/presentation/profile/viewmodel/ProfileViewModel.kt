package com.example.jamble_challenge.presentation.profile.viewmodel

import com.example.jamble_challenge.presentation.profile.viewmodel.state.ProfileUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jamble_challenge.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> =
        _uiState
            .onStart {
                if (_uiState.value.user == null) {
                    loadUser()
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = ProfileUiState(isLoading = true)
            )

    private fun loadUser() {
        viewModelScope.launch {
            val user = repository.getUser()
            _uiState.value = ProfileUiState(
                isLoading = false,
                user = user
            )
        }
    }

}