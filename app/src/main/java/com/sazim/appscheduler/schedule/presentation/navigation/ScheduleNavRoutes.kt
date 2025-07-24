/*
 * Created by Saeedus Salehin on 24/7/25, 6:46 PM.
 */

package com.sazim.appscheduler.schedule.presentation.navigation

sealed class ScheduleNavRoutes(val route: String) {
    data object AppsScreen : ScheduleNavRoutes("app_screen")
    data object UpcomingScheduleScreen : ScheduleNavRoutes("upcoming_schedule_screen")
    data object ScheduleHistoryScreen : ScheduleNavRoutes("schedule_history_screen")
}