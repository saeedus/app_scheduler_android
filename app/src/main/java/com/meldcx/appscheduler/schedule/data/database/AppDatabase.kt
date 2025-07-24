package com.meldcx.appscheduler.schedule.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.meldcx.appscheduler.schedule.data.dao.ScheduledAlarmDao
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm

@Database(entities = [ScheduledAlarm::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduledAlarmDao(): ScheduledAlarmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
