package com.example.jamble_challenge.data.repository

import com.example.jamble_challenge.R
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
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

    override suspend fun getLives(): List<Live> {
        delay(800)
        return listOf(
            Live("1", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("2", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("13", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("99", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("3", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("98", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("4", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("97", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("5", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("96", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("6", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("95", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("7", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("94", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("8", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("93", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("9", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("92", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("10", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("91", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("11", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("90", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("12", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("89", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
        )
    }

    override suspend fun getReviews(): List<Review> {
        delay(800)
        return listOf(
            Review("1", "@maria", "Produto incrível!", "2h atrás", R.drawable.avatar_03, 5),
            Review("2", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("3", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("4", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("5", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("6", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("7", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("8", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("9", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("10", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("11", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("12", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("13", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("14", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("15", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
            Review("16", "@joao", "Entrega rápida!", "1 dia atrás", R.drawable.avatar_04, 4),
        )
    }

    override suspend fun getBookmarks(): List<Live> {
        delay(800)
        return listOf(
            Live("1", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("2", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("13", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("99", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("3", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("98", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("4", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("97", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("5", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("96", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("6", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("95", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("7", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("94", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("8", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("93", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("9", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("92", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("10", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("91", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("11", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("90", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
            Live("12", "Relógios Premium", R.drawable.live_cover_02, false, 0, "Hoje 20:00", 320),
            Live("89", "Sneakers Drop", R.drawable.live_cover_03, true, 120, null, 540),
        )
    }
}
