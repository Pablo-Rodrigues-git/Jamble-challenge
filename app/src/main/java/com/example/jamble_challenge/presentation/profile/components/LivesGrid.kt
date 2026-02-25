package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.domain.model.dataclass.Live

@Composable
fun LivesGrid(
    lives: List<Live>,
    scrollEnabled: Boolean,
    gridState: LazyGridState
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        userScrollEnabled = scrollEnabled,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(lives) { live ->
            LiveCard(live = live)
        }
    }
}