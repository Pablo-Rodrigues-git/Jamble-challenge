package com.example.jamble_challenge.presentation.profile.viewmodel

import com.example.jamble_challenge.presentation.profile.viewmodel.state.ProfileUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jamble_challenge.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> =
        _uiState
            .onStart {
                if (_uiState.value.user == null) {
                    loadInitialData()
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = ProfileUiState(isLoading = true)
            )

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            delay(2000)

            val user = repository.getUser()
            val lives = repository.getLives()
            val reviews = repository.getReviews()
            val bookmarks = repository.getBookmarks()

            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = user,
                    lives = lives,
                    reviews = reviews,
                    bookmarks = bookmarks
                )
            }
        }
    }

    fun refreshAll() {
        loadInitialData()
    }

    fun updateBio(newBio: String) {
        viewModelScope.launch {
            repository.updateBio(newBio)

            _uiState.update { currentState ->
                currentState.copy(
                    user = currentState.user?.copy(
                        bio = newBio
                    )
                )
            }
        }
    }
}