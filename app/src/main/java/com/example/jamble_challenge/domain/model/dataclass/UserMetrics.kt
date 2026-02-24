package com.example.jamble_challenge.domain.model.dataclass

data class UserMetrics(
    val deliveryDays: Double,
    val rating: Double,
    val reviewsCount: Int,
    val followers: String,
    val rewards: String
)