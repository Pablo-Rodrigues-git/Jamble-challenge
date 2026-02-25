package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.domain.model.dataclass.Review

@Composable
fun ContentReview(
    reviews: List<Review>,
    rating: Double,
    totalReviews: Int,
    scrollEnabled: Boolean,
    listState: LazyListState,
    isLoadingMore: Boolean = false,
    onLoadMore: () -> Unit = {}
) {

    LazyColumn(
        state = listState,
        userScrollEnabled = scrollEnabled,
        modifier = Modifier.fillMaxSize(),
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

        itemsIndexed(reviews) { index, review ->
            if (index >= reviews.size - 2 && !isLoadingMore) {
                LaunchedEffect(Unit) {
                    onLoadMore()
                }
            }
            ReviewItem(review = review)
        }

        if (isLoadingMore) {
            item {
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