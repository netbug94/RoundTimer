package com.example.roundtimer.utils

import android.os.Build
import android.view.WindowManager
import androidx.activity.ComponentActivity

object LockScreenHelper {
    fun setupLockScreenVisibility(activity: ComponentActivity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            activity.setShowWhenLocked(true)
            activity.setTurnScreenOn(true)
        } else {
            @Suppress("DEPRECATION")
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        }
    }

    fun clearLockScreenVisibility(activity: ComponentActivity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            activity.setShowWhenLocked(false)
            activity.setTurnScreenOn(false)
        } else {
            @Suppress("DEPRECATION")
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        }
    }
}
