package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.DarkButton
import com.example.jamble_challenge.core.ui.theme.Dimens.AvatarEditSize
import com.example.jamble_challenge.core.ui.theme.Dimens.AvatarSize
import com.example.jamble_challenge.core.ui.theme.JambleTheme

@Composable
fun ProfileAvatar(
    avatarRes: Int,
    onEditClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.size(AvatarSize)
    ) {

        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )

        Box(
            modifier = Modifier
                .size(AvatarEditSize)
                .align(Alignment.BottomEnd)
                .offset(x = 2.dp, y = 2.dp)
                .clip(CircleShape)
                .background(DarkButton)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 120,
    heightDp = 120
)
@Composable
private fun ProfileAvatarPreview() {
    JambleTheme {
        ProfileAvatar(
            avatarRes = R.drawable.avatar_02
        )
    }
}