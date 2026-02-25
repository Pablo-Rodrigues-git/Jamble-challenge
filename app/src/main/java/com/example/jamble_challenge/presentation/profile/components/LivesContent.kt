package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import com.example.jamble_challenge.domain.model.dataclass.Live

@Composable
fun LivesContent(
    lives: List<Live>,
    scrollEnabled: Boolean,
    gridState: LazyGridState,
    isLoadingMore: Boolean = false,
    onLoadMore: () -> Unit = {}
) {
    if (lives.isEmpty()) {
        LivesEmptyState()
    } else {
        LivesGrid(
            lives = lives,
            scrollEnabled = scrollEnabled,
            gridState = gridState,
            isLoadingMore = isLoadingMore,
            onLoadMore = onLoadMore
        )
    }
}