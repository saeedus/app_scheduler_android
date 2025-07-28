package com.meldcx.appscheduler.schedule.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm

@Dao
interface ScheduledAlarmDao {
    @Insert
    suspend fun insert(scheduledAlarm: ScheduledAlarm)

    @Query("SELECT * FROM scheduled_alarms")
    suspend fun getAll(): List<ScheduledAlarm>

    

    @androidx.room.Update
    suspend fun update(scheduledAlarm: ScheduledAlarm)
}
