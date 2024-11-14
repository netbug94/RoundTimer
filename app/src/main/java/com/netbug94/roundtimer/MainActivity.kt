package com.netbug94.roundtimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.netbug94.roundtimer.navigation.NavigationManager
import com.netbug94.roundtimer.setting_screens.settings_screen.a_o_d_settings.AlwaysOnDisplayOptions
import com.netbug94.roundtimer.setting_screens.settings_screen.a_o_d_settings.AlwaysOnDisplayViewModel
import com.netbug94.roundtimer.ui.theme.Herrete
import com.netbug94.roundtimer.ui.theme.RoundTimerTheme
import com.netbug94.roundtimer.utils.LockScreenHelper
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var alwaysOnDisplayViewModel: AlwaysOnDisplayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoundTimerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationManager(modifier = Modifier)
                    Herrete("netBug94")
                }
            }
        }
        alwaysOnDisplayViewModel = ViewModelProvider(this)[AlwaysOnDisplayViewModel::class.java]
        lifecycleScope.launch {
            alwaysOnDisplayViewModel.selectedAlwaysOnDisplayOption.collect { option ->
                if (option == AlwaysOnDisplayOptions.AOD) {
                    LockScreenHelper.setupLockScreenVisibility(this@MainActivity)
                } else {
                    LockScreenHelper.clearLockScreenVisibility(this@MainActivity)
                }
            }
        }
    }
}