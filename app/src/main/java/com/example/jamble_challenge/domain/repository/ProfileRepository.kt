package com.example.jamble_challenge.domain.repository

import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.model.dataclass.User

interface ProfileRepository {
    suspend fun getUser(): User
    suspend fun updateBio(newBio: String)
    suspend fun getLives(): List<Live>
    suspend fun getReviews(): List<Review>
    suspend fun getBookmarks(): List<Live>
}
