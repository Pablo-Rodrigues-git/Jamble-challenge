package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import androidx.compose.ui.graphics.Color
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.core.ui.theme.BorderTertiary
import com.example.jamble_challenge.core.ui.theme.BrandPrimary
import com.example.jamble_challenge.core.ui.theme.Dimens
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.RatingYellow
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.TextSecondary

@Composable
fun ProfileMetricPill(
    modifier: Modifier = Modifier,
    icon: Painter?,
    value: String,
    suffix: String? = null,
    iconTint: Color = TextSecondary,
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(Dimens.PillRadius),
        border = BorderStroke(1.dp, BorderTertiary),
        color = BgPrimary
    ) {

        Row(
            modifier = Modifier
                .padding(
                    horizontal = Dimens.Space8,
                    vertical = Dimens.Space8
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            icon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = value,
                    style = AppTypography.bodyLarge,
                    color = TextPrimary
                )

                suffix?.let {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = it,
                        style = AppTypography.bodyMedium,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileMetricPillPreview() {

    JambleTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {

            ProfileMetricPill(
                icon = painterResource(R.drawable.icon_truck_fast),
                value = "5.2",
                suffix = "days",
                iconTint = Color(0xFF10B981)
            )

            ProfileMetricPill(
                icon = painterResource(R.drawable.icon_star),
                value = "4.8",
                suffix = "(112)",
                iconTint = RatingYellow
            )

            ProfileMetricPill(
                icon = painterResource(R.drawable.icon_people),
                value = "1.6K",
                iconTint = BrandPrimary
            )

            ProfileMetricPill(
                icon = painterResource(R.drawable.icon_chart_star),
                value = "+99",
                iconTint = BrandPrimary
            )
        }
    }
}