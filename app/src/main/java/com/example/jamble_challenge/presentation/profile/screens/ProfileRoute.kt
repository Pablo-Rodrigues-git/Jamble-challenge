package com.example.jamble_challenge.presentation.profile.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jamble_challenge.presentation.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    ProfileScreen(
        uiState = uiState,
        onRefreshAll = viewModel::refreshAll,
        onSaveBio = viewModel::updateBio,
        onLoadMoreLives = viewModel::loadMoreLives,
        onLoadMoreReviews = viewModel::loadMoreReviews,
        onLoadMoreBookmarks = viewModel::loadMoreBookmarks
    )
}