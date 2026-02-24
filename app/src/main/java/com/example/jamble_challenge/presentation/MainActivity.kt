package com.example.jamble_challenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jamble_challenge.core.ui.theme.JambleTheme
import com.example.jamble_challenge.presentation.profile.screens.ProfileRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JambleTheme {
                ProfileRoute()
            }
        }
    }
}

