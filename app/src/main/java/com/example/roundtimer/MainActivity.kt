package com.example.roundtimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.roundtimer.navigation.NavigationManager
import com.example.roundtimer.ui.theme.Herrete
import com.example.roundtimer.ui.theme.RoundTimerTheme
import com.example.roundtimer.utils.LockScreenHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LockScreenHelper.setupLockScreenVisibility(this)
        setContent {
            RoundTimerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationManager(modifier = Modifier)
                    Herrete("netBug94")
                }
            }
        }
    }
}
