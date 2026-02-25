package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics

@Composable
fun ProfileHeader(
    user: User,
    modifier: Modifier = Modifier,
    onEditAvatarClick: () -> Unit = {},
    onInviteClick: () -> Unit = {},
    onReferClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onSaveBio: (String) -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        ProfileTopBar(
            name = user.name,
            onAddClick = {},
            onSettingsClick = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProfileAvatarHeader(
            user = user,
            onEditAvatarClick = onEditAvatarClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileMetricsRow(
            metrics = user.metrics
        )

        ProfileBio(
            bio = user.bio,
            onSaveBio = onSaveBio,

        )

        ProfileActionsRow(
            onInviteClick = onInviteClick,
            onReferClick = onReferClick,
            onShareClick = onShareClick
        )
    }
}

@Preview(showBackground = true, widthDp = 393)
@Composable
private fun ProfileHeaderPreview() {

    val previewUser = User(
        id = "1",
        name = "Felipe Sanchez",
        username = "@felipe_shop",
        avatarRes = R.drawable.avatar_02,
        isLiveSeller = true,
        joinedAt = "June 2024",
        bio = "Dealer de sneakers. Drops hebdomadaires, contact en DM pour toute question.",
        metrics = UserMetrics(
            deliveryDays = 5.2,
            rating = 4.8,
            reviewsCount = 112,
            followers = "1.6K",
            rewards = "+99"
        )
    )

    JambleTheme {
        ProfileHeader(user = previewUser)
    }
}

@Preview(showBackground = true, widthDp = 393, name = "Bio vazia")
@Composable
private fun ProfileScreenWithoutBioPreview() {

    val previewUser = User(
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

    JambleTheme {
        ProfileHeader(user = previewUser)
    }
}

