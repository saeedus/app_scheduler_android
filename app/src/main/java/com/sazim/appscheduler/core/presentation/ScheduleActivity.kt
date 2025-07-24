/*
 * Created by Saeedus Salehin on 24/7/25, 1:26 PM.
 */

package com.sazim.appscheduler.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Text("Hello meldCX")
        }

    }
}