package com.sazim.appscheduler.schedule.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sazim.appscheduler.schedule.presentation.navigation.ScheduleNavGraph
import com.sazim.appscheduler.schedule.presentation.navigation.ScheduleNavRoutes
import org.koin.androidx.compose.koinViewModel

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = koinViewModel<ScheduleViewModel>()
            val state by viewModel.state.collectAsState()


            Scaffold { innerPadding ->
                ScheduleNavGraph(
                    modifier = Modifier.padding(innerPadding),
                    navController = rememberNavController(),
                    startDestination = ScheduleNavRoutes.AppsScreen.route,
                    viewModel = viewModel,
                    state = state
                )
            }
        }
    }
}