package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.jamble_challenge.R
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.LiveRed
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.domain.model.dataclass.Live

@Composable
fun LiveCard(
    live: Live,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .aspectRatio(0.746f)
                .clip(RoundedCornerShape(8.dp))
        ) {

            Image(
                painter = painterResource(live.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Transparent
                            )
                        )
                    )
            )

            if (live.isLive) {
                LiveBadge(live.viewers)
            } else {
                ScheduledTopRow(
                    time = live.scheduledTime.toString(),
                    likes = live.likes
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = live.title,
            style = AppTypography.bodyLarge,
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun LiveBadge(viewers: Int) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(
                LiveRed,
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Live · $viewers",
            color = Color.White,
            style = AppTypography.bodySmall
        )
    }
}

@Composable
private fun ScheduledTopRow(
    time: String,
    likes: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .background(
                    Color.Black.copy(alpha = 0.3f),
                    RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = time,
                color = Color.White,
                style = AppTypography.bodySmall
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    Color.Black.copy(alpha = 0.3f),
                    RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.icon_heart_white),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = likes.toString(),
                color = Color.White,
                style = AppTypography.bodySmall
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 180, heightDp = 180)
@Composable
private fun LiveCardPreviewLive() {
    JambleTheme {
        LiveCard(
            live = Live(
                id = "1",
                title = "Pokemon cards opening",
                imageRes = R.drawable.live_cover_01,
                isLive = true,
                viewers = 96,
                likes = 46,
                scheduledTime = null
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 180, heightDp = 180)
@Composable
private fun LiveCardPreviewScheduled() {
    JambleTheme {
        LiveCard(
            live = Live(
                id = "2",
                title = "Collection tour",
                imageRes = R.drawable.live_cover_02,
                isLive = false,
                viewers = 0,
                likes = 46,
                scheduledTime = "6:00 PM"
            )
        )
    }
}