package com.example.roundtimer.utils

import android.os.Build
import android.view.WindowManager
import androidx.activity.ComponentActivity


object LockScreenHelper {
    /**
     * @param activity The activity for which to set lock screen visibility.
     */
    fun setupLockScreenVisibility(activity: ComponentActivity) {
        // Keep the screen on when the activity is visible
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Show the activity when the device is locked
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            activity.setShowWhenLocked(true)
            activity.setTurnScreenOn(true)
        } else {
            @Suppress("DEPRECATION")
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        }
    }
}