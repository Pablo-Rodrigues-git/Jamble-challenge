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
                    loadUserData()
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = ProfileUiState(isLoading = true)
            )

    private fun loadUserData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

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
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            delay(1000)

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

    fun loadMoreLives() {
        if (_uiState.value.isLoadingMoreLives) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMoreLives = true) }
            val newLives = repository.getLives()
            _uiState.update {
                it.copy(
                    lives = newLives,
                    isLoadingMoreLives = false
                )
            }
        }
    }

    fun loadMoreReviews() {
        if (_uiState.value.isLoadingMoreReviews) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMoreReviews = true) }
            val newReviews = repository.getReviews()
            _uiState.update {
                it.copy(
                    reviews = newReviews,
                    isLoadingMoreReviews = false
                )
            }
        }
    }

    fun loadMoreBookmarks() {
        if (_uiState.value.isLoadingMoreBookmarks) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMoreBookmarks = true) }
            val newBookmarks = repository.getBookmarks()
            _uiState.update {
                it.copy(
                    bookmarks = newBookmarks,
                    isLoadingMoreBookmarks = false
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