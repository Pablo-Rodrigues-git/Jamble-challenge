package com.example.jamble_challenge.presentation.profile.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.data.mock.mockLives
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics
import com.example.jamble_challenge.domain.model.enums.BottomNavItem
import com.example.jamble_challenge.domain.model.enums.ProfileTab
import com.example.jamble_challenge.presentation.profile.components.BookmarksContent
import com.example.jamble_challenge.presentation.profile.components.BookmarksSkeleton
import com.example.jamble_challenge.presentation.profile.components.ContentReview
import com.example.jamble_challenge.presentation.profile.components.JambleBottomBar
import com.example.jamble_challenge.presentation.profile.components.LivesContent
import com.example.jamble_challenge.presentation.profile.components.LivesSkeleton
import com.example.jamble_challenge.presentation.profile.components.ProfileHeader
import com.example.jamble_challenge.presentation.profile.components.ProfileHeaderSkeleton
import com.example.jamble_challenge.presentation.profile.components.ProfileTabs
import com.example.jamble_challenge.presentation.profile.components.ReviewsSkeleton
import com.example.jamble_challenge.presentation.profile.viewmodel.state.ProfileUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    initialPage: Int = 0,
    onRefreshAll: () -> Unit,
    onSaveBio: (String) -> Unit
) {
    ProfileContent(
        uiState = uiState,
        initialPage = initialPage,
        onRefreshAll = onRefreshAll,
        onSaveBio = onSaveBio
    )
}

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
private fun ProfileContent(
    uiState: ProfileUiState,
    initialPage: Int = 0,
    onRefreshAll: () -> Unit,
    onSaveBio: (String) -> Unit
) {

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

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = onRefreshAll
    )

    Scaffold(
        containerColor = BgPrimary,
        bottomBar = {
            JambleBottomBar(
                selectedItem = BottomNavItem.PROFILE,
                onItemSelected = { /* navigate later */ }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(
                state = mainListState,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    // Show skeleton if loading, otherwise content
                    if (uiState.isLoading) {
                        ProfileHeaderSkeleton()
                    } else if (uiState.user != null) {
                        ProfileHeader(
                            user = uiState.user,
                            onSaveBio = onSaveBio
                        )
                    }
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight()
                    ) { page ->

                        when (tabs[page]) {

                            ProfileTab.LIVES -> {
                                if (uiState.isLoading) {
                                    LivesSkeleton()
                                } else {
                                    LivesContent(
                                        lives = uiState.lives,
                                        scrollEnabled = true,
                                        gridState = livesGridState
                                    )
                                }
                            }

                            ProfileTab.REVIEWS -> {
                                if (uiState.isLoading) {
                                    ReviewsSkeleton()
                                } else if (uiState.user != null) {
                                    val reviews = uiState.reviews

                                    val averageRating = if (reviews.isNotEmpty()) {
                                        reviews.map { it.rating }.average()
                                    } else 0.0

                                    val totalReviews = reviews.size

                                    ContentReview(
                                        reviews = uiState.reviews,
                                        rating = averageRating,
                                        totalReviews = totalReviews,
                                        scrollEnabled = true,
                                        listState = reviewsListState
                                    )
                                } else {
                                    ReviewsSkeleton()
                                }
                            }

                            ProfileTab.BOOKMARKS -> {
                                if (uiState.isLoading) {
                                    BookmarksSkeleton()
                                } else {
                                    BookmarksContent(
                                        bookmarks = uiState.bookmarks,
                                        scrollEnabled = true,
                                        gridState = bookmarksGridState
                                    )
                                }
                            }
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = uiState.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
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
            5.2,
            4.8,
            112,
            "1.6K",
            "+99"
        )
    )
    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = false,
                user = previewUser,
                lives = mockLives
            ),
            onRefreshAll = {},
            onSaveBio = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 800)
@Composable
private fun ProfileScreenLoadingPreview() {
    JambleTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                isLoading = true,
                user = null,
                lives = emptyList()
            ),
            onRefreshAll = {},
            onSaveBio = {}
        )
    }
}
