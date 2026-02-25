package com.example.jamble_challenge.fakerepository

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

    override suspend fun getUser(): User = user
    override suspend fun getLives(): List<Live> = lives
    override suspend fun getReviews(): List<Review> = reviews
    override suspend fun getBookmarks(): List<Live> = bookmarks

    override suspend fun updateBio(newBio: String) {
        user = user.copy(bio = newBio)
    }
}