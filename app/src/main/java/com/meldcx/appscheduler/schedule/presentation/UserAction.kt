/*
 * Created by Saeedus Salehin on 24/7/25, 7:23 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

sealed class UserAction {
    data object LoadApps : UserAction()
}