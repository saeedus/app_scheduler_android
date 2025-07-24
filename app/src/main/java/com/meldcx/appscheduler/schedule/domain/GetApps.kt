/*
 * Created by Saeedus Salehin on 23/7/25, 11:13 PM.
 */

package com.meldcx.appscheduler.schedule.domain

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.meldcx.appscheduler.schedule.domain.model.AppInfo

class GetApps(private val context: Context) {

    operator fun invoke(): List<AppInfo> {
        val packageManager: PackageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfoList = packageManager.queryIntentActivities(intent, 0)
        return resolveInfoList.map {
            AppInfo(
                appName = it.loadLabel(packageManager).toString(),
                packageName = it.activityInfo.packageName,
                icon = it.loadIcon(packageManager)
            )
        }
    }
}