package com.example.jamble_challenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.presentation.profile.screens.ProfileRoute
import com.example.jamble_challenge.presentation.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JambleTheme {
                var showSplash by remember { mutableStateOf(true) }

                Crossfade(targetState = showSplash, label = "SplashTransition") { isSplash ->
                    if (isSplash) {
                        SplashScreen(
                            onSplashFinished = { showSplash = false }
                        )
                    } else {
                        ProfileRoute()
                    }
                }
            }
        }
    }
}