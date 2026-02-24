package com.example.jamble_challenge.presentation.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jamble_challenge.core.ui.theme.Dimens
import androidx.compose.ui.tooling.preview.Preview
import com.example.jamble_challenge.core.ui.theme.JambleTheme

@Composable
 fun PlaceholderPage(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Space24),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 600)
@Composable
private fun PlaceholderPagePreview() {
    JambleTheme {
        PlaceholderPage(text = "Lives")
    }
}