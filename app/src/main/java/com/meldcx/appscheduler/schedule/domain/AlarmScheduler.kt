/*
 * Created by Saeedus Salehin on 24/7/25, 8:25 PM.
 */

package com.meldcx.appscheduler.schedule.domain

interface AlarmScheduler {
    fun schedule(packageName: String, timeInMillis: Long)
}