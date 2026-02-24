package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.domain.model.enums.ProfileTab
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.BorderSecondary
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.TextSecondary
import com.example.jamble_challenge.core.ui.theme.JambleTheme

@Composable
fun ProfileTabs(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = ProfileTab.entries.toTypedArray()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val tabWidth = screenWidth / tabs.size

    val animatedOffset by animateDpAsState(
        targetValue = tabWidth * selectedIndex,
        label = "TabIndicatorAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            tabs.forEachIndexed { index, tab ->

                val isSelected = index == selectedIndex

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onTabSelected(index) },
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(
                                when (tab) {
                                    ProfileTab.LIVES -> R.drawable.icon_play
                                    ProfileTab.REVIEWS -> R.drawable.icon_star
                                    ProfileTab.BOOKMARKS -> R.drawable.icon_heart_dark
                                }
                            ),
                            contentDescription = null,
                            tint = if (isSelected) TextPrimary else TextSecondary
                        )

                        if (isSelected) {
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = when (tab) {
                                    ProfileTab.LIVES -> "Lives"
                                    ProfileTab.REVIEWS -> "Reviews"
                                    ProfileTab.BOOKMARKS -> "Bookmarks"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                color = TextPrimary
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(BorderSecondary)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(tabWidth)
                    .height(2.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .offset(x = animatedOffset)
                    .background(TextPrimary)
            )
        }
    }
}

@Preview(
    name = "Lives Selected",
    showBackground = true,
    widthDp = 393
)
@Composable
private fun ProfileTabsPreviewLives() {
    JambleTheme {
        ProfileTabs(
            selectedIndex = 0,
            onTabSelected = {}
        )
    }
}

@Preview(
    name = "Reviews Selected",
    showBackground = true,
    widthDp = 393
)
@Composable
private fun ProfileTabsPreviewReviews() {
    JambleTheme {
        ProfileTabs(
            selectedIndex = 1,
            onTabSelected = {}
        )
    }
}

@Preview(
    name = "Bookmarks Selected",
    showBackground = true,
    widthDp = 393
)
@Composable
private fun ProfileTabsPreviewBookmarks() {
    JambleTheme {
        ProfileTabs(
            selectedIndex = 2,
            onTabSelected = {}
        )
    }
}