package com.example.jamble_challenge.data.repository

import com.example.jamble_challenge.R
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics
import com.example.jamble_challenge.domain.repository.ProfileRepository
import kotlinx.coroutines.delay


class FakeProfileRepository : ProfileRepository {

    private var currentUser: User = User(
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

    override suspend fun getUser(): User {
        delay(500)
        return currentUser
    }

    override suspend fun updateBio(newBio: String) {
        delay(300)
        currentUser = currentUser.copy(
            bio = newBio
        )
    }
}