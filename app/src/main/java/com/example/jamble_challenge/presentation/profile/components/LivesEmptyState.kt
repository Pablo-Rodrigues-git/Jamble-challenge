package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.BrandPrimary
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.TextSecondary

@Composable
fun LivesEmptyState(
    onScheduleClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(110.dp))

        Text(
            text = "There is nothing here yet",
            style = AppTypography.titleLarge,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Schedule a show and go Live!",
            style = AppTypography.bodyMedium,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            BrandPrimary,
                            BrandPrimary.copy(alpha = 0.85f)
                        )
                    )
                )
                .padding(horizontal = 32.dp)
                .clickable { onScheduleClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Schedule a Live",
                color = Color.White,
                style = AppTypography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 393)
@Composable
private fun LivesEmptyStatePreview() {
    JambleTheme {
        LivesEmptyState()
    }
}