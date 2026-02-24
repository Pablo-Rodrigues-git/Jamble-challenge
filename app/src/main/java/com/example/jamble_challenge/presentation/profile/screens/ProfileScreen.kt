package com.example.jamble_challenge.presentation.profile.screens

import com.example.jamble_challenge.domain.model.enums.ProfileTab
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import com.example.jamble_challenge.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.data.mock.mockLives
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics
import com.example.jamble_challenge.domain.model.enums.BottomNavItem
import com.example.jamble_challenge.presentation.profile.components.BookmarksContent
import com.example.jamble_challenge.presentation.profile.components.ContentReview
import com.example.jamble_challenge.presentation.profile.components.JambleBottomBar
import com.example.jamble_challenge.presentation.profile.components.LivesContent
import com.example.jamble_challenge.presentation.profile.components.ProfileHeader
import com.example.jamble_challenge.presentation.profile.components.ProfileTabs
import com.example.jamble_challenge.presentation.profile.viewmodel.state.ProfileUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    initialPage: Int = 0
) {

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    ProfileContent(
        uiState = uiState,
        initialPage = initialPage
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProfileContent(
    uiState: ProfileUiState,
    initialPage: Int = 0
) {

    val user = uiState.user ?: return
    val tabs = ProfileTab.entries.toTypedArray()

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { tabs.size }
    )

    val scope = rememberCoroutineScope()

    val mainListState = rememberLazyListState()

    val livesGridState = rememberSaveable(
        saver = LazyGridState.Saver
    ) { LazyGridState() }

    val reviewsListState = rememberSaveable(
        saver = LazyListState.Saver
    ) { LazyListState() }

    val bookmarksGridState = rememberSaveable(
        saver = LazyGridState.Saver
    ) { LazyGridState() }

    Scaffold(
        containerColor = BgPrimary,
        bottomBar = {
            JambleBottomBar(
                selectedItem = BottomNavItem.PROFILE,
                onItemSelected = { /* navegar depois */ }
            )
        }
    ) { padding ->

        LazyColumn(
            state = mainListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                ProfileHeader(user = user)
            }
            stickyHeader {
                ProfileTabs(
                    selectedIndex = pagerState.currentPage,
                    onTabSelected = { index ->
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
            item {

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                        .height(500.dp)
                ) { page ->

                    when (tabs[page]) {

                        ProfileTab.LIVES -> {
                            LivesContent(
                                lives = uiState.lives,
                                scrollEnabled = false,
                                gridState = livesGridState
                            )
                        }

                        ProfileTab.REVIEWS -> {
                            ContentReview(
                                reviews = uiState.reviews,
                                rating = user.metrics.rating,
                                totalReviews = user.metrics.reviewsCount,
                                scrollEnabled = false,
                                listState = reviewsListState
                            )
                        }

                        ProfileTab.BOOKMARKS -> {
                            BookmarksContent(
                                bookmarks = uiState.bookmarks,
                                scrollEnabled = false,
                                gridState = bookmarksGridState
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 800)
@Composable
private fun ProfileScreenWithLivesPreview() {

    val previewUser = User(
        id = "1",
        name = "Felipe Sanchez",
        username = "@felipe_shop",
        avatarRes = R.drawable.avatar_02,
        isLiveSeller = true,
        joinedAt = "June 2024",
        bio = null,
        metrics = UserMetrics(
            deliveryDays = 5.2,
            rating = 4.8,
            reviewsCount = 112,
            followers = "1.6K",
            rewards = "+99"
        )
    )

    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = false,
                user = previewUser,
                lives = mockLives
            )
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    heightDp = 800,
    name = "Lives vazias"
)
@Composable
private fun ProfileScreenEmptyLivesPreview() {

    val previewUser = User(
        id = "1",
        name = "Felipe Sanchez",
        username = "@felipe_shop",
        avatarRes = R.drawable.avatar_02,
        isLiveSeller = true,
        joinedAt = "June 2024",
        bio = null,
        metrics = UserMetrics(
            deliveryDays = 5.2,
            rating = 4.8,
            reviewsCount = 112,
            followers = "1.6K",
            rewards = "+99"
        )
    )

    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = false,
                user = previewUser,
                lives = emptyList()
            )
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    heightDp = 800,
    name = "Profile - Reviews Tab"
)
@Composable
private fun ProfileScreenReviewsTabPreview() {

    val previewUser = User(
        id = "1",
        name = "Felipe Sanchez",
        username = "@felipe_shop",
        avatarRes = R.drawable.avatar_02,
        isLiveSeller = true,
        joinedAt = "June 2024",
        bio = "sum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        metrics = UserMetrics(
            deliveryDays = 5.2,
            rating = 4.9,
            reviewsCount = 3,
            followers = "1.6K",
            rewards = "+99"
        )
    )

    val mockReviews = listOf(
        Review(
            id = "1",
            username = "Lucas_TCG",
            message = "Very fast shipping!",
            timeAgo = "2h",
            avatarRes = R.drawable.avatar_03,
            rating = 5
        ),
        Review(
            id = "2",
            username = "Marie Claire",
            message = "Packaging could improve.",
            timeAgo = "1d",
            avatarRes = R.drawable.avatar_02,
            rating = 3
        ),
        Review(
            id = "3",
            username = "Marie Claire",
            message = "Packaging could improve.",
            timeAgo = "1d",
            avatarRes = R.drawable.avatar_02,
            rating = 3
        ),
        Review(
            id = "4",
            username = "Marie Claire",
            message = "Packaging could improve.",
            timeAgo = "1d",
            avatarRes = R.drawable.avatar_02,
            rating = 3
        ),
        Review(
            id = "5",
            username = "Marie Claire",
            message = "Packaging could improve.",
            timeAgo = "1d",
            avatarRes = R.drawable.avatar_02,
            rating = 3
        ),
        Review(
            id = "6",
            username = "Marie Claire",
            message = "Packaging could improve.",
            timeAgo = "1d",
            avatarRes = R.drawable.avatar_02,
            rating = 3
        )
    )

    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = false,
                user = previewUser,
                lives = emptyList(),
                reviews = mockReviews
            ),
            initialPage = 1
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    heightDp = 800,
    name = "Profile - Bookmarks Tab"
)
@Composable
private fun ProfileScreenBookmarksPreview() {

    val previewUser = User(
        id = "1",
        name = "Felipe Sanchez",
        username = "@felipe_shop",
        avatarRes = R.drawable.avatar_02,
        isLiveSeller = true,
        joinedAt = "June 2024",
        bio = "Sneaker dealer",
        metrics = UserMetrics(
            deliveryDays = 5.2,
            rating = 4.9,
            reviewsCount = 3,
            followers = "1.6K",
            rewards = "+99"
        )
    )

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

    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = false,
                user = previewUser,
                bookmarks = mockBookmarks
            ),
            initialPage = 2
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    heightDp = 800,
    name = "Profile - Bookmarks Empty"
)
@Composable
private fun ProfileScreenBookmarksEmptyPreview() {

    val previewUser = User(
        id = "1",
        name = "Felipe Sanchez",
        username = "@felipe_shop",
        avatarRes = R.drawable.avatar_02,
        isLiveSeller = true,
        joinedAt = "June 2024",
        bio = "Sneaker dealer",
        metrics = UserMetrics(
            deliveryDays = 5.2,
            rating = 4.9,
            reviewsCount = 3,
            followers = "1.6K",
            rewards = "+99"
        )
    )

    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = false,
                user = previewUser,
                bookmarks = emptyList()
            ),
            initialPage = 2
        )
    }
}