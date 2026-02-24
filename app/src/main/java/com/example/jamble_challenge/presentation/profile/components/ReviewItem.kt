package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.RatingYellow
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.TextSecondary
import com.example.jamble_challenge.domain.model.dataclass.Review

@Composable
fun ReviewItem(
    review: Review,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(TextPrimary.copy(alpha = 0.04f))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(review.avatarRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = review.username,
                    style = AppTypography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = review.timeAgo,
                    style = AppTypography.bodySmall,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row {
                repeat(5) { index ->

                    val filled = index < review.rating

                    Icon(
                        painter = painterResource(R.drawable.icon_star),
                        contentDescription = null,
                        tint = if (filled) RatingYellow else TextSecondary.copy(alpha = 0.25f),
                        modifier = Modifier.size(20.dp)
                    )

                    if (index < 4) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }

            if (review.message.isNotBlank()) {

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = review.message,
                    style = AppTypography.bodyMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    name = "Review Item"
)
@Composable
private fun ReviewItemPreview() {

    val mockReview = Review(
        id = "1",
        username = "Marie Claire",
        message = "Fast shipping and perfect packaging. Highly recommended!",
        timeAgo = "20h",
        avatarRes = R.drawable.avatar_02,
        rating = 5
    )

    JambleTheme {
        ReviewItem(review = mockReview)
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    name = "Review - 3 Stars"
)
@Composable
private fun ReviewItemThreeStarsPreview() {

    val mockReview = Review(
        id = "1",
        username = "Lucas_TCG",
        message = "Good seller but shipping took longer than expected.",
        timeAgo = "12h",
        avatarRes = R.drawable.avatar_02,
        rating = 3
    )

    JambleTheme {
        ReviewItem(review = mockReview)
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    name = "Review - 0 Stars"
)
@Composable
private fun ReviewItemZeroStarsPreview() {

    val mockReview = Review(
        id = "2",
        username = "UnhappyUser",
        message = "Item arrived damaged.",
        timeAgo = "2d",
        avatarRes = R.drawable.avatar_03,
        rating = 0
    )

    JambleTheme {
        ReviewItem(review = mockReview)
    }
}