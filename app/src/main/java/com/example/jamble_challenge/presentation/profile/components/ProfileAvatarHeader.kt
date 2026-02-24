package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.BrandPrimary
import com.example.jamble_challenge.core.ui.theme.Dimens
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.TextSecondary
import com.example.jamble_challenge.domain.model.dataclass.User
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics

@Composable
fun ProfileAvatarHeader(
    user: User,
    onEditAvatarClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Space16),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ProfileAvatar(
            avatarRes = user.avatarRes,
            onEditClick = onEditAvatarClick
        )

        Spacer(modifier = Modifier.width(Dimens.Space16))

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_link),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = TextSecondary,
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = user.username,
                    style = MaterialTheme.typography.bodyLarge,
                    color = BrandPrimary,
                    modifier = Modifier.clickable { },
                )
            }
            if (user.isLiveSeller) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_seller_badge),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color.Unspecified,
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = "Live Seller",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextPrimary
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_calendar),
                    tint = TextSecondary,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = "Joined ${user.joinedAt}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 393
)
@Composable
private fun ProfileAvatarHeaderPreview() {

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
        ProfileAvatarHeader(user = previewUser)
    }
}
