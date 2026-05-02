package com.example.jamble_challenge.viewmodel

import com.example.jamble_challenge.dispatcher.MainDispatcherRule
import com.example.jamble_challenge.dispatcher.TestAppDispatcher
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.usecase.GetBookmarksUseCase
import com.example.jamble_challenge.domain.usecase.GetLivesUseCase
import com.example.jamble_challenge.domain.usecase.GetReviewsUseCase
import com.example.jamble_challenge.domain.usecase.GetUserProfileUseCase
import com.example.jamble_challenge.domain.usecase.UpdateBioUseCase
import com.example.jamble_challenge.fakerepository.FakeProfileRepository
import com.example.jamble_challenge.presentation.profile.viewmodel.ProfileViewModel
import com.example.jamble_challenge.presentation.profile.viewmodel.state.RetryAction
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
        viewModel = createViewModel()
    }

    private fun createViewModel(): ProfileViewModel {
        return ProfileViewModel(
            getUserProfile = GetUserProfileUseCase(repository),
            getLives = GetLivesUseCase(repository),
            getReviews = GetReviewsUseCase(repository),
            getBookmarks = GetBookmarksUseCase(repository),
            updateBioUseCase = UpdateBioUseCase(repository),
            dispatcher = TestAppDispatcher()
        )
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

        val finalState = viewModel.uiState.first { !it.isLoading }

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
        val vm = createViewModel()

        vm.updateBio("Test")

        advanceUntilIdle()

        val state = vm.uiState.value

        assertEquals(null, state.user)
    }

    @Test
    fun `loadMoreLives appends items to existing list`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        val firstPage = listOf(
            Live(id = "first", title = "First", imageRes = 0, isLive = false, viewers = 0, likes = 10, scheduledTime = null)
        )
        repository.lives = firstPage

        viewModel.uiState.first { !it.isLoading }

        viewModel.loadMoreLives()
        advanceUntilIdle()

        assertEquals(1, viewModel.uiState.value.lives.size)

        val secondPage = listOf(
            Live(id = "second", title = "Second", imageRes = 0, isLive = true, viewers = 100, likes = 50, scheduledTime = null)
        )
        repository.lives = secondPage

        viewModel.loadMoreLives()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(2, state.lives.size)
        assertEquals("first", state.lives[0].id)
        assertEquals("second", state.lives[1].id)
    }

    @Test
    fun `loadMoreReviews appends items to existing list`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        val firstPage = listOf(
            Review(id = "first", username = "user1", message = "Good", timeAgo = "1m", avatarRes = 0, rating = 5)
        )
        repository.reviews = firstPage

        viewModel.uiState.first { !it.isLoading }

        viewModel.loadMoreReviews()
        advanceUntilIdle()

        assertEquals(1, viewModel.uiState.value.reviews.size)

        val secondPage = listOf(
            Review(id = "second", username = "user2", message = "Great", timeAgo = "2m", avatarRes = 0, rating = 4)
        )
        repository.reviews = secondPage

        viewModel.loadMoreReviews()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(2, state.reviews.size)
        assertEquals("first", state.reviews[0].id)
        assertEquals("second", state.reviews[1].id)
    }

    @Test
    fun `loadMoreBookmarks appends items to existing list`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        val firstPage = listOf(
            Live(id = "first", title = "First", imageRes = 0, isLive = false, viewers = 0, likes = 10, scheduledTime = null)
        )
        repository.bookmarks = firstPage

        viewModel.uiState.first { !it.isLoading }

        viewModel.loadMoreBookmarks()
        advanceUntilIdle()

        assertEquals(1, viewModel.uiState.value.bookmarks.size)

        val secondPage = listOf(
            Live(id = "second", title = "Second", imageRes = 0, isLive = false, viewers = 0, likes = 20, scheduledTime = "Tomorrow")
        )
        repository.bookmarks = secondPage

        viewModel.loadMoreBookmarks()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(2, state.bookmarks.size)
        assertEquals("first", state.bookmarks[0].id)
        assertEquals("second", state.bookmarks[1].id)
    }

    @Test
    fun `error state is set when getUser fails`() = runTest {
        repository.shouldReturnError = true
        repository.errorMessage = "Network error"

        val vm = createViewModel()

        val state = vm.uiState.first { !it.isLoading }

        assertNotNull(state.error)
        assertEquals("Network error", state.error?.message)
        assertEquals(RetryAction.LOAD_PROFILE, state.error?.retryAction)
    }

    @Test
    fun `dismissError clears error state`() = runTest {
        repository.shouldReturnError = true

        val vm = createViewModel()
        vm.uiState.first { !it.isLoading }

        vm.dismissError()
        advanceUntilIdle()

        assertNull(vm.uiState.value.error)
    }

    @Test
    fun `retry triggers correct action`() = runTest {
        repository.shouldReturnError = true

        val vm = createViewModel()
        vm.uiState.first { !it.isLoading }

        repository.shouldReturnError = false

        vm.retry()
        advanceUntilIdle()

        val state = vm.uiState.first { !it.isLoading }
        assertNotNull(state.user)
        assertNull(state.error)
    }
}
