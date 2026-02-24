package com.example.jamble_challenge.domain.model.dataclass

data class Review(
    val id: String,
    val username: String,
    val message: String,
    val timeAgo: String,
    val avatarRes: Int,
    val rating: Int
)