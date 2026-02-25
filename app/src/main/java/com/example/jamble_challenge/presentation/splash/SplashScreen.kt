package com.example.jamble_challenge.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jamble_challenge.R
import com.example.jamble_challenge.core.ui.theme.BgPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000) // Display splash for 2 seconds
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgPrimary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.jamble_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
    }
}