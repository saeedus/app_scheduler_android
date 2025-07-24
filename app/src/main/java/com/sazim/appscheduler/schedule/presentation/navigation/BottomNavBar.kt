/*
 * Created by Saeedus Salehin on 23/7/25, 9:45 PM.
 */

package com.sazim.appscheduler.schedule.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sazim.appscheduler.R
import com.sazim.appscheduler.core.presentation.util.getCurrentRoute
import com.sazim.appscheduler.schedule.presentation.navigation.model.BottomNavItem

@Composable
fun BottomNavBar(navController: NavController) {
    val currentRoute = getCurrentRoute(navController)

    val navItems = listOf(
        BottomNavItem(
            route = ScheduleNavRoutes.AppsScreen.route,
            iconRes = R.drawable.ic_apps,
            label = "Apps"
        ),
        BottomNavItem(
            route = ScheduleNavRoutes.UpcomingScheduleScreen.route,
            iconRes = R.drawable.ic_schedule,
            label = "Schedules"
        )
    )

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
