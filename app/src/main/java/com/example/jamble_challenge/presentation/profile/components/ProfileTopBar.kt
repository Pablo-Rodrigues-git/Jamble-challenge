package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.BorderTertiary
import com.example.jamble_challenge.core.ui.theme.Dimens
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.core.ui.theme.TextPrimary

@Composable
fun ProfileTopBar(
    name: String,
    onAddClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = BorderTertiary,
                    strokeWidth = Dimens.ThinBorder.toPx(),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    start = Dimens.TopBarPaddingHorizontal,
                    end = Dimens.TopBarPaddingBottom,
                    bottom = 8.dp
                )

        ) {

            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = onAddClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_plus),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_gear),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 393
)
@Composable
private fun ProfileTopBarPreview() {
    JambleTheme {
        ProfileTopBar(
            name = "Felipe Sanchez"
        )
    }
}