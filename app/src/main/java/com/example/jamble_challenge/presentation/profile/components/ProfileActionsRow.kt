package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import com.example.jamble_challenge.core.ui.theme.BrandPrimary
import com.example.jamble_challenge.core.ui.theme.DarkButton
import com.example.jamble_challenge.core.ui.theme.Dimens
import com.example.jamble_challenge.core.ui.theme.JambleTheme

@Composable
fun ProfileActionsRow(
    onInviteClick: () -> Unit = {},
    onReferClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Space16)
            .padding(top = Dimens.Space16, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.height(44.dp),
            shape = RoundedCornerShape(22.dp),
            color = BrandPrimary,
            onClick = onInviteClick
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Invite · R$15",
                    color = BgPrimary
                )
            }
        }
        Surface(
            modifier = Modifier.height(44.dp),
            shape = RoundedCornerShape(22.dp),
            color = DarkButton,
            onClick = onReferClick
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Refer Sellers · R$500",
                    color = BgPrimary
                )
            }
        }
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(DarkButton, CircleShape)
                .clickable { onShareClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_share),
                contentDescription = null,
                tint = BgPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
@Preview(showBackground = true, widthDp = 393)
@Composable
private fun ProfileActionsRowPreview() {
    JambleTheme {
        ProfileActionsRow()
    }
}