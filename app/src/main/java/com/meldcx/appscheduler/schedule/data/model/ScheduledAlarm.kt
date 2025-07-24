package com.meldcx.appscheduler.schedule.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduled_alarms")
data class ScheduledAlarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val packageName: String,
    val timeInMillis: Long
)
