package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.domain.model.dataclass.Live

@Composable
fun BookmarksGrid(
    bookmarks: List<Live>,
    scrollEnabled: Boolean,
    gridState: LazyGridState,
    isLoadingMore: Boolean = false,
    onLoadMore: () -> Unit = {}
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        userScrollEnabled = scrollEnabled,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 12.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bookmarks.size) { index ->
            if (index >= bookmarks.size - 2 && !isLoadingMore) {
                LaunchedEffect(Unit) {
                    onLoadMore()
                }
            }
            LiveCard(live = bookmarks[index])
        }

        if (isLoadingMore) {
            item(span = { GridItemSpan(2) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    heightDp = 600,
    name = "Bookmarks - Grid"
)
@Composable
private fun BookmarksGridPreview() {

    val mockBookmarks = listOf(
        Live(
            id = "1",
            title = "Pokemon cards opening",
            imageRes = R.drawable.live_cover_01,
            isLive = false,
            viewers = 0,
            likes = 46,
            scheduledTime = "6:00 PM"
        ),
        Live(
            id = "2",
            title = "Collection tour",
            imageRes = R.drawable.live_cover_02,
            isLive = false,
            viewers = 0,
            likes = 46,
            scheduledTime = "9:00 PM"
        )
    )

    val gridState = rememberLazyGridState()

    JambleTheme {
        BookmarksGrid(
            bookmarks = mockBookmarks,
            scrollEnabled = true,
            gridState = gridState
        )
    }
}