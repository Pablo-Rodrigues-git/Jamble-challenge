package com.example.jamble_challenge.fakerepository

import com.example.jamble_challenge.domain.common.Result
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics
import com.example.jamble_challenge.domain.repository.ProfileRepository

class FakeProfileRepository : ProfileRepository {

    var user = User(
        id = "1",
        name = "John",
        username = "@john",
        avatarRes = 0,
        isLiveSeller = false,
        joinedAt = "2024",
        bio = null,
        metrics = UserMetrics(
            deliveryDays = 0.0,
            rating = 0.0,
            reviewsCount = 0,
            followers = "0",
            rewards = "0"
        )
    )

    var lives = emptyList<Live>()
    var reviews = emptyList<Review>()
    var bookmarks = emptyList<Live>()

    var shouldReturnError = false
    var errorMessage = "Test error"

    override suspend fun getUser(): Result<User> {
        if (shouldReturnError) return Result.Error(message = errorMessage)
        return Result.Success(user)
    }

    override suspend fun getLives(page: Int): Result<List<Live>> {
        if (shouldReturnError) return Result.Error(message = errorMessage)
        return Result.Success(lives)
    }

    override suspend fun getReviews(page: Int): Result<List<Review>> {
        if (shouldReturnError) return Result.Error(message = errorMessage)
        return Result.Success(reviews)
    }

    override suspend fun getBookmarks(page: Int): Result<List<Live>> {
        if (shouldReturnError) return Result.Error(message = errorMessage)
        return Result.Success(bookmarks)
    }

    override suspend fun updateBio(newBio: String): Result<Unit> {
        if (shouldReturnError) return Result.Error(message = errorMessage)
        user = user.copy(bio = newBio)
        return Result.Success(Unit)
    }
}
