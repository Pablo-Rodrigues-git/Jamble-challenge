package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import com.example.jamble_challenge.domain.model.dataclass.Live

@Composable
fun BookmarksContent(
    bookmarks: List<Live>,
    scrollEnabled: Boolean,
    gridState: LazyGridState,
    isLoadingMore: Boolean = false,
    onLoadMore: () -> Unit = {}
) {
    if (bookmarks.isEmpty()) {
        BookmarksEmptyState()
    } else {
        BookmarksGrid(
            bookmarks = bookmarks,
            scrollEnabled = scrollEnabled,
            gridState = gridState,
            isLoadingMore = isLoadingMore,
            onLoadMore = onLoadMore
        )
    }
}