/*
 * Created by Saeedus Salehin on 24/7/25, 6:46 PM.
 */

package com.meldcx.appscheduler.schedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.meldcx.appscheduler.schedule.presentation.ScheduleState
import com.meldcx.appscheduler.schedule.presentation.ScheduleViewModel
import com.meldcx.appscheduler.schedule.presentation.screens.AppsScreen

import com.meldcx.appscheduler.schedule.presentation.screens.UpcomingScheduleScreen

@Composable
fun ScheduleNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    viewModel: ScheduleViewModel,
    state: ScheduleState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = ScheduleNavRoutes.AppsScreen.route) {
            AppsScreen(modifier = modifier, viewModel = viewModel, state = state)
        }

        composable(route = ScheduleNavRoutes.UpcomingScheduleScreen.route) {
            UpcomingScheduleScreen(modifier = modifier, viewModel = viewModel, state = state)
        }

        
    }
}