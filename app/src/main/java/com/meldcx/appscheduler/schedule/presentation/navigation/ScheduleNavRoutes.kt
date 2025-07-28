/*
 * Created by Saeedus Salehin on 24/7/25, 6:46 PM.
 */

package com.meldcx.appscheduler.schedule.presentation.navigation

sealed class ScheduleNavRoutes(val route: String) {
    data object AppsScreen : ScheduleNavRoutes("app_screen")
    data object UpcomingScheduleScreen : ScheduleNavRoutes("upcoming_schedule_screen")
    data object EditScheduleScreen : ScheduleNavRoutes("edit_schedule_screen/{scheduleId}") {
        fun createRoute(scheduleId: Int) = "edit_schedule_screen/$scheduleId"
    }
    
}