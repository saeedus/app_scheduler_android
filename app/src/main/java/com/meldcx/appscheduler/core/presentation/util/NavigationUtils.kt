/*
 * Created by Saeedus Salehin on 23/7/25, 9:50 PM.
 */

package com.meldcx.appscheduler.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun getCurrentRoute(navController: NavController): String {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""
    return currentRoute.substringBefore("?").substringBefore("/")
}