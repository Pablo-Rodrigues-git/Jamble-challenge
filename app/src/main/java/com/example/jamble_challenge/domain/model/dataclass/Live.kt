package com.example.jamble_challenge.domain.model.dataclass

data class Live(
    val id: String,
    val title: String,
    val imageRes: Int,
    val isLive: Boolean,
    val viewers: Int = 0,
    val scheduledTime: String? = null,
    val likes: Int
)
