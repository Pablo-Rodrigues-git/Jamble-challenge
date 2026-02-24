package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.core.ui.theme.BorderTertiary
import com.example.jamble_challenge.core.ui.theme.DarkButton
import com.example.jamble_challenge.core.ui.theme.Dimens
import com.example.jamble_challenge.core.ui.theme.LinkBlue
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.JambleTheme

@Composable
fun ProfileBio(
    bio: String?,
    onAddBioClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val isEmpty = bio.isNullOrBlank()
    if (isEmpty) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Space16)
                .border(
                    width = 0.5.dp,
                    color = BorderTertiary,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = BgPrimary,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(50.dp)
                .clickable { onAddBioClick() }
                .padding(start = 8.dp, top = 4.dp),
            contentAlignment = Alignment.TopStart
        ) {

            Text(
                text = "Add a bio...",
                color = LinkBlue,
                style = AppTypography.bodyLarge
            )
        }
    } else {

        var expanded by remember { mutableStateOf(false) }
        var isOverflowing by remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Space16)
                .padding(vertical = Dimens.Space16)
        ) {

            Text(
                text = bio,
                color = TextPrimary,
                style = AppTypography.bodyLarge,
                maxLines = if (expanded) 5 else 2,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    if (!expanded) {
                        isOverflowing = textLayoutResult.hasVisualOverflow
                    }
                }
            )

            if (isOverflowing) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (expanded) "Read less" else "Read more",
                    color = DarkButton,
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393)
@Composable
private fun ProfileBioFilledPreview() {
    JambleTheme {
        ProfileBio(
            bio = "Dealer de sneakers. Drops hebdomadaires, contact en DM pour toute question."
        )
    }
}

@Preview(showBackground = true, widthDp = 393)
@Composable
private fun ProfileBioEmptyPreview() {
    JambleTheme {
        ProfileBio(
            bio = null
        )
    }
}