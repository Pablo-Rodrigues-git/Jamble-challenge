package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.AppTypography
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.TextPrimary
import com.example.jamble_challenge.core.ui.theme.TextSecondary
import com.example.jamble_challenge.domain.model.enums.BottomNavItem

@Composable
fun JambleBottomBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    Surface(
        tonalElevation = 0.dp,
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        color = BgPrimary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarItem(
                icon = R.drawable.icon_home,
                label = "Live",
                selected = selectedItem == BottomNavItem.LIVE,
                onClick = { onItemSelected(BottomNavItem.LIVE) }
            )

            BottomBarItem(
                icon = R.drawable.icon_rewards,
                label = "Rewards",
                selected = selectedItem == BottomNavItem.REWARDS,
                onClick = { onItemSelected(BottomNavItem.REWARDS) }
            )

            BottomBarItem(
                icon = R.drawable.icon_activity,
                label = "Activity",
                selected = selectedItem == BottomNavItem.ACTIVITY,
                onClick = { onItemSelected(BottomNavItem.ACTIVITY) }
            )

            ProfileBottomItem(
                selected = selectedItem == BottomNavItem.PROFILE,
                onClick = { onItemSelected(BottomNavItem.PROFILE) }
            )
        }
    }
}


@Composable
private fun BottomBarItem(
    icon: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color = if (selected) TextPrimary else TextSecondary

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = AppTypography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun ProfileBottomItem(
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.avatar_02),
            contentDescription = "Profile",
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .border(
                    width = if (selected) 2.dp else 0.dp,
                    color = if (selected) TextPrimary else Color.Transparent,
                    shape =
                        CircleShape
                )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Profile",
            style = AppTypography.labelSmall,
            color = if (selected) TextPrimary else TextSecondary
        )
    }
}

@Preview(showBackground = true, widthDp = 393)
@Composable
private fun BottomBarPreview() {
    JambleTheme {
        JambleBottomBar(
            selectedItem = BottomNavItem.PROFILE,
            onItemSelected = {}
        )
    }
}