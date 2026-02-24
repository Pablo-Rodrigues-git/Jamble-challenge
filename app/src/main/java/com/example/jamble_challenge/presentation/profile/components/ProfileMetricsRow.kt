package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.core.ui.theme.BrandPrimary
import com.example.jamble_challenge.core.ui.theme.Dimens
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.RatingYellow
import com.example.jamble_challenge.core.ui.theme.linkGreen
import com.example.jamble_challenge.R
import com.example.jamble_challenge.domain.model.dataclass.UserMetrics

@Composable
fun ProfileMetricsRow(
    metrics: UserMetrics,
    modifier: Modifier = Modifier
) {

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Space16, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        ProfileMetricPill(
            icon = painterResource(R.drawable.icon_truck_fast),
            value = metrics.deliveryDays.toString(),
            suffix = "days",
            iconTint = linkGreen
        )

        ProfileMetricPill(
            icon = painterResource(R.drawable.icon_star),
            value = metrics.rating.toString(),
            suffix = "(${metrics.reviewsCount})",
            iconTint = RatingYellow
        )

        ProfileMetricPill(
            icon = painterResource(R.drawable.icon_people),
            value = metrics.followers,
            iconTint = BrandPrimary
        )

        ProfileMetricPill(
            icon = painterResource(R.drawable.icon_chart_star),
            value = metrics.rewards,
            iconTint = BrandPrimary
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 393
)
@Composable
private fun ProfileMetricsRowPreview() {

    val previewMetrics = UserMetrics(
        deliveryDays = 5.2,
        rating = 4.8,
        reviewsCount = 112,
        followers = "1.6K",
        rewards = "+99"
    )

    JambleTheme {
        ProfileMetricsRow(metrics = previewMetrics)
    }
}