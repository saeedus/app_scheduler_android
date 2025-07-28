package com.meldcx.appscheduler.schedule.presentation

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.meldcx.appscheduler.schedule.presentation.navigation.BottomNavBar
import com.meldcx.appscheduler.schedule.presentation.navigation.ScheduleNavGraph
import com.meldcx.appscheduler.schedule.presentation.navigation.ScheduleNavRoutes
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleActivity : ComponentActivity() {

    private val viewModel: ScheduleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by viewModel.state.collectAsState()
            val navController = rememberNavController()

            if (!hasExactAlarmPermission()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
            }

            LaunchedEffect(Unit) {
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        ScheduleEvents.Pop -> {
                            navController.popBackStack()
                        }
                    }
                }
            }

            Scaffold(bottomBar = {
                BottomNavBar(navController = navController)
            }) { innerPadding ->
                ScheduleNavGraph(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    startDestination = ScheduleNavRoutes.AppsScreen.route,
                    viewModel = viewModel,
                    state = state
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onAction(UserAction.LoadScheduleAlarms)
    }

    private fun hasExactAlarmPermission(): Boolean {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }
}