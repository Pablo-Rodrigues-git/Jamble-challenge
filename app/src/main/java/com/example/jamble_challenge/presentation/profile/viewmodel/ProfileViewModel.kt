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
            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = user
                )
            }
        }
    }

    fun refreshAll() {
        refreshLives()
        refreshReviews()
        refreshBookmarks()
    }

    private fun refreshLives() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshingLives = true) }

            val lives = repository.getLives()

            _uiState.update {
                it.copy(
                    lives = lives,
                    isRefreshingLives = false
                )
            }
        }
    }

    private fun refreshReviews() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshingReviews = true) }

            val reviews = repository.getReviews()

            _uiState.update {
                it.copy(
                    reviews = reviews,
                    isRefreshingReviews = false
                )
            }
        }
    }

    private fun refreshBookmarks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshingBookmarks = true) }

            val bookmarks = repository.getBookmarks()

            _uiState.update {
                it.copy(
                    bookmarks = bookmarks,
                    isRefreshingBookmarks = false
                )
            }
        }
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

