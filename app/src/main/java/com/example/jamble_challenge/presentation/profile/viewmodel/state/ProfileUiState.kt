package com.example.jamble_challenge.presentation.profile.viewmodel.state

import com.example.jamble_challenge.domain.model.dataclass.Live
import com.example.jamble_challenge.domain.model.dataclass.Review
import com.example.jamble_challenge.domain.model.dataclass.User

data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val lives: List<Live> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val bookmarks: List<Live> = emptyList()
)