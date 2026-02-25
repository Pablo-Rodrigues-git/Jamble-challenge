package com.example.jamble_challenge.viewmodel

import com.example.jamble_challenge.dispatcher.MainDispatcherRule
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.fakerepository.FakeProfileRepository
import com.example.jamble_challenge.presentation.profile.viewmodel.ProfileViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var repository: FakeProfileRepository
    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setup() {
        repository = FakeProfileRepository()
        viewModel = ProfileViewModel(repository)
    }

    @Test
    fun `state starts loading`() = runTest {
        val state = viewModel.uiState.value
        assertEquals(true, state.isLoading)
    }

    @Test
    fun `onStart does not reload when user already exists`() = runTest {

        viewModel.uiState.first { !it.isLoading }

        val firstUser = viewModel.uiState.value.user

        val secondState = viewModel.uiState.first { !it.isLoading }

        assertEquals(firstUser, secondState.user)
    }

    @Test
    fun `loadInitialData sets user and stops loading`() = runTest {

        val finalState = viewModel.uiState.first { !it.isLoading }

        assertEquals("John", finalState.user?.name)
        assertEquals(false, finalState.isLoading)
    }

    @Test
    fun `refreshAll updates all lists`() = runTest {

        repository.lives = listOf(
            Live(
                id = "1",
                title = "Test Live",
                imageRes = 0,
                isLive = false,
                viewers = 0,
                likes = 10,
                scheduledTime = null
            )
        )

        repository.reviews = listOf(
            Review(
                id = "1",
                username = "user",
                message = "Good",
                timeAgo = "now",
                avatarRes = 0,
                rating = 5
            )
        )

        repository.bookmarks = listOf(
            Live(
                id = "2",
                title = "Bookmark",
                imageRes = 0,
                isLive = false,
                viewers = 0,
                likes = 0,
                scheduledTime = null
            )
        )

        viewModel.uiState.first { !it.isLoading }

        viewModel.refreshAll()

        advanceUntilIdle()

        val finalState =
            viewModel.uiState.first { !it.isLoading }

        assertEquals(1, finalState.lives.size)
        assertEquals(1, finalState.reviews.size)
        assertEquals(1, finalState.bookmarks.size)
    }

    @Test
    fun `updateBio updates user bio`() = runTest {

        viewModel.uiState.first { !it.isLoading }

        viewModel.updateBio("New Bio")

        advanceUntilIdle()

        val finalState = viewModel.uiState.value

        assertEquals("New Bio", finalState.user?.bio)
    }

    @Test
    fun `updateBio does nothing when user is null`() = runTest {

        val vm = ProfileViewModel(repository)

        vm.updateBio("Test")

        advanceUntilIdle()

        val state = vm.uiState.value

        assertEquals(null, state.user)
    }

    @Test
    fun `loadMoreLives updates lives and loading state`() = runTest {
        val newLives = listOf(
            Live(
                id = "newLive",
                title = "New Live",
                imageRes = 0,
                isLive = true,
                viewers = 100,
                likes = 50,
                scheduledTime = null
            )
        )
        repository.lives = newLives

        viewModel.loadMoreLives()

        advanceUntilIdle()

    }

    @Test
    fun `loadMoreReviews updates reviews and loading state`() = runTest {
        val newReviews = listOf(
            Review(
                id = "newReview",
                username = "newUser",
                message = "Great!",
                timeAgo = "1m",
                avatarRes = 0,
                rating = 5
            )
        )
        repository.reviews = newReviews

        viewModel.loadMoreReviews()

        advanceUntilIdle()
    }

    @Test
    fun `loadMoreBookmarks updates bookmarks and loading state`() = runTest {
        val newBookmarks = listOf(
            Live(
                id = "newBookmark",
                title = "Future Live",
                imageRes = 0,
                isLive = false,
                viewers = 0,
                likes = 10,
                scheduledTime = "Tomorrow"
            )
        )
        repository.bookmarks = newBookmarks

        viewModel.loadMoreBookmarks()

        advanceUntilIdle()
    }
}
