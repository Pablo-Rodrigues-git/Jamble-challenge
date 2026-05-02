package com.example.jamble_challenge.domain.repository

import com.example.jamble_challenge.domain.common.Result
import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.model.dataclass.User

interface ProfileRepository {
    suspend fun getUser(): Result<User>
    suspend fun updateBio(newBio: String): Result<Unit>
    suspend fun getLives(page: Int): Result<List<Live>>
    suspend fun getReviews(page: Int): Result<List<Review>>
    suspend fun getBookmarks(page: Int): Result<List<Live>>
}
