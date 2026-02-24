package com.example.jamble_challenge.domain.model.dataclass

data class User(
    val id: String,
    val name: String,
    val username: String,
    val avatarRes: Int,
    val isLiveSeller: Boolean,
    val joinedAt: String,
    val metrics: UserMetrics,
    val bio: String?
)

