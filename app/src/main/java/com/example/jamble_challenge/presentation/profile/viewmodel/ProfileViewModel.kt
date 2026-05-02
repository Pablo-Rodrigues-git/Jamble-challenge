package com.example.jamble_challenge.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jamble_challenge.core.dispatcher.AppDispatcher
import com.example.jamble_challenge.domain.common.Result
import com.example.jamble_challenge.domain.usecase.GetBookmarksUseCase
import com.example.jamble_challenge.domain.usecase.GetLivesUseCase
import com.example.jamble_challenge.domain.usecase.GetReviewsUseCase
import com.example.jamble_challenge.domain.usecase.GetUserProfileUseCase
import com.example.jamble_challenge.domain.usecase.UpdateBioUseCase
import com.example.jamble_challenge.presentation.profile.viewmodel.state.ErrorState
import com.example.jamble_challenge.presentation.profile.viewmodel.state.ProfileUiState
import com.example.jamble_challenge.presentation.profile.viewmodel.state.RetryAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserProfile: GetUserProfileUseCase,
    private val getLives: GetLivesUseCase,
    private val getReviews: GetReviewsUseCase,
    private val getBookmarks: GetBookmarksUseCase,
    private val updateBioUseCase: UpdateBioUseCase,
    private val dispatcher: AppDispatcher
) : ViewModel() {

    private var livesPage = 0
    private var reviewsPage = 0
    private var bookmarksPage = 0

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
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = ProfileUiState(isLoading = true)
            )

    private fun loadUserData() {
        viewModelScope.launch(dispatcher.io) {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getUserProfile()) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, user = result.data)
                    }
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = ErrorState(
                                message = result.message ?: "Failed to load profile",
                                retryAction = RetryAction.LOAD_PROFILE
                            )
                        )
                    }
                }
            }
        }
    }

    fun refreshAll() {
        viewModelScope.launch(dispatcher.io) {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val userResult = getUserProfile()

            livesPage = 0
            reviewsPage = 0
            bookmarksPage = 0

            val livesResult = getLives(livesPage)
            val reviewsResult = getReviews(reviewsPage)
            val bookmarksResult = getBookmarks(bookmarksPage)

            if (userResult is Result.Error) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = ErrorState(
                            message = userResult.message ?: "Failed to refresh",
                            retryAction = RetryAction.REFRESH_ALL
                        )
                    )
                }
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = false,
                    user = (userResult as Result.Success).data,
                    lives = (livesResult as? Result.Success)?.data ?: it.lives,
                    reviews = (reviewsResult as? Result.Success)?.data ?: it.reviews,
                    bookmarks = (bookmarksResult as? Result.Success)?.data ?: it.bookmarks
                )
            }
        }
    }

    fun loadMoreLives() {
        if (_uiState.value.isLoadingMoreLives) return

        viewModelScope.launch(dispatcher.io) {
            _uiState.update { it.copy(isLoadingMoreLives = true, error = null) }

            livesPage++
            when (val result = getLives(livesPage)) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            lives = it.lives + result.data,
                            isLoadingMoreLives = false
                        )
                    }
                }
                is Result.Error -> {
                    livesPage--
                    _uiState.update {
                        it.copy(
                            isLoadingMoreLives = false,
                            error = ErrorState(
                                message = result.message ?: "Failed to load more lives",
                                retryAction = RetryAction.LOAD_LIVES
                            )
                        )
                    }
                }
            }
        }
    }

    fun loadMoreReviews() {
        if (_uiState.value.isLoadingMoreReviews) return

        viewModelScope.launch(dispatcher.io) {
            _uiState.update { it.copy(isLoadingMoreReviews = true, error = null) }

            reviewsPage++
            when (val result = getReviews(reviewsPage)) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            reviews = it.reviews + result.data,
                            isLoadingMoreReviews = false
                        )
                    }
                }
                is Result.Error -> {
                    reviewsPage--
                    _uiState.update {
                        it.copy(
                            isLoadingMoreReviews = false,
                            error = ErrorState(
                                message = result.message ?: "Failed to load more reviews",
                                retryAction = RetryAction.LOAD_REVIEWS
                            )
                        )
                    }
                }
            }
        }
    }

    fun loadMoreBookmarks() {
        if (_uiState.value.isLoadingMoreBookmarks) return

        viewModelScope.launch(dispatcher.io) {
            _uiState.update { it.copy(isLoadingMoreBookmarks = true, error = null) }

            bookmarksPage++
            when (val result = getBookmarks(bookmarksPage)) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            bookmarks = it.bookmarks + result.data,
                            isLoadingMoreBookmarks = false
                        )
                    }
                }
                is Result.Error -> {
                    bookmarksPage--
                    _uiState.update {
                        it.copy(
                            isLoadingMoreBookmarks = false,
                            error = ErrorState(
                                message = result.message ?: "Failed to load more bookmarks",
                                retryAction = RetryAction.LOAD_BOOKMARKS
                            )
                        )
                    }
                }
            }
        }
    }

    fun updateBio(newBio: String) {
        viewModelScope.launch(dispatcher.io) {
            when (updateBioUseCase(newBio)) {
                is Result.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            user = currentState.user?.copy(bio = newBio)
                        )
                    }
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            error = ErrorState(message = "Failed to update bio")
                        )
                    }
                }
            }
        }
    }

    fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }

    fun retry() {
        val action = _uiState.value.error?.retryAction ?: return
        dismissError()
        when (action) {
            RetryAction.LOAD_PROFILE -> loadUserData()
            RetryAction.LOAD_LIVES -> loadMoreLives()
            RetryAction.LOAD_REVIEWS -> loadMoreReviews()
            RetryAction.LOAD_BOOKMARKS -> loadMoreBookmarks()
            RetryAction.REFRESH_ALL -> refreshAll()
        }
    }
}
