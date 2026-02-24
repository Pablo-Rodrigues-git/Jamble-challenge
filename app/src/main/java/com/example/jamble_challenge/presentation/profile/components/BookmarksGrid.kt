package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
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
    gridState: LazyGridState
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        userScrollEnabled = scrollEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 600.dp),
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 12.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bookmarks) { live ->
            LiveCard(live = live)
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