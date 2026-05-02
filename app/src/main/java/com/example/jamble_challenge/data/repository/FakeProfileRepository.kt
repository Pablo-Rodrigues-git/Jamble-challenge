package com.example.jamble_challenge.data.repository

import com.example.jamble_challenge.R
import com.example.jamble_challenge.domain.common.Result
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics
import com.example.jamble_challenge.domain.repository.ProfileRepository
import kotlinx.coroutines.delay

class FakeProfileRepository : ProfileRepository {

    private val pageSize = 4

    private val liveTitles = listOf(
        "Sneaker Drop",
        "Luxury Watches Showcase",
        "Streetwear Collection",
        "Rare Pokemon Cards",
        "Vintage Sneakers",
        "Exclusive Hoodies",
        "Limited Edition Caps",
        "Collector's Items",
        "Gaming Merch Live",
        "Designer Bags Review"
    )

    private val usernames = listOf(
        "john_doe",
        "sarah_smith",
        "alex_turner",
        "emma_wilson",
        "michael_brown",
        "olivia_jones",
        "david_miller",
        "chris_evans",
        "jessica_white",
        "daniel_clark"
    )

    private val reviewMessages = listOf(
        "Amazing quality!",
        "Fast shipping, highly recommend.",
        "Product exactly as described.",
        "Great seller, will buy again.",
        "Very professional service.",
        "Item arrived in perfect condition.",
        "Communication was excellent.",
        "Super happy with my purchase!"
    )

    private val liveImages = listOf(
        R.drawable.live_cover_01,
        R.drawable.live_cover_02,
        R.drawable.live_cover_03,
        R.drawable.live_cover_04,
        R.drawable.live_cover_05,
        R.drawable.live_cover_06,
        R.drawable.live_cover_07,
    )

    private val avatars = listOf(
        R.drawable.avatar_02,
        R.drawable.avatar_03,
        R.drawable.avatar_04,
    )

    private val scheduledTimes = listOf(
        "6:00 PM",
        "7:00 AM",
        "8:30 PM",
        "10:00 AM",
        "12:00 PM",
        "4:15 PM",
        "9:00 AM",
        "5:30 PM"
    )

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

    override suspend fun getUser(): Result<User> {
        delay(500)
        return Result.Success(currentUser)
    }

    override suspend fun updateBio(newBio: String): Result<Unit> {
        delay(300)
        currentUser = currentUser.copy(bio = newBio)
        return Result.Success(Unit)
    }

    override suspend fun getLives(page: Int): Result<List<Live>> {
        delay(800)
        val items = List(pageSize) { index ->
            Live(
                id = "live_${page}_$index",
                title = liveTitles.random(),
                imageRes = liveImages.random(),
                isLive = listOf(true, false).random(),
                viewers = (50..2000).random(),
                likes = (100..5000).random(),
                scheduledTime = scheduledTimes.random()
            )
        }
        return Result.Success(items)
    }

    override suspend fun getReviews(page: Int): Result<List<Review>> {
        delay(800)
        val items = List(3) { index ->
            Review(
                id = "review_${page}_$index",
                username = usernames.random(),
                message = reviewMessages.random(),
                timeAgo = "Just now",
                avatarRes = avatars.random(),
                rating = (3..5).random()
            )
        }
        return Result.Success(items)
    }

    override suspend fun getBookmarks(page: Int): Result<List<Live>> {
        delay(800)
        val items = List(pageSize) { index ->
            Live(
                id = "bookmark_${page}_$index",
                title = liveTitles.random(),
                imageRes = liveImages.random(),
                isLive = false,
                viewers = 0,
                likes = (100..2000).random(),
                scheduledTime = scheduledTimes.random()
            )
        }
        return Result.Success(items)
    }
}
