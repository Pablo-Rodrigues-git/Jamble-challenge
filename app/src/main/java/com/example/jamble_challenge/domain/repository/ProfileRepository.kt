package com.example.jamble_challenge.domain.repository

import com.example.jamble_challenge.domain.model.dataclass.User

interface ProfileRepository {
    suspend fun getUser(): User
        suspend fun updateBio(newBio: String)
    }
