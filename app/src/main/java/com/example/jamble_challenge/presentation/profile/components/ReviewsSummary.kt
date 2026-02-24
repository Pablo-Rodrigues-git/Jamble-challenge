package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.TextPrimary

@Composable
fun ReviewsSummary(
    rating: Double,
    totalReviews: Int,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = rating.toString(),
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Out of 5",
                style = AppTypography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.width(110.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = totalReviews.toString(),
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Reviews",
                style = AppTypography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                fontSize = 13.sp
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 393,
    name = "Reviews Summary"
)
@Composable
private fun ReviewsSummaryPreview() {
    JambleTheme {
        ReviewsSummary(
            rating = 4.9,
            totalReviews = 6950
        )
    }
}