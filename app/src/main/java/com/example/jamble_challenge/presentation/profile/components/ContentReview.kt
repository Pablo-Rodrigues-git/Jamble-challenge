package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.domain.model.dataclass.Review
import androidx.compose.foundation.lazy.items

@Composable
fun ContentReview(
    reviews: List<Review>,
    rating: Double,
    totalReviews: Int,
    scrollEnabled: Boolean,
    listState: LazyListState
) {

    LazyColumn(
        state = listState,
        userScrollEnabled = scrollEnabled,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            ReviewsSummary(
                rating = rating,
                totalReviews = totalReviews
            )
        }

        items(reviews) { review ->
            ReviewItem(review = review)
        }
    }
}