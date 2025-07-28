/*
 * Created by Saeedus Salehin on 24/7/25, 6:50 PM.
 */

package com.meldcx.appscheduler.schedule.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.meldcx.appscheduler.schedule.domain.AlarmScheduler
import com.meldcx.appscheduler.schedule.presentation.ScheduleState
import com.meldcx.appscheduler.schedule.presentation.ScheduleViewModel
import com.meldcx.appscheduler.schedule.presentation.UserAction
import com.meldcx.appscheduler.schedule.presentation.components.DateTimePickerDialog
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppsScreen(modifier: Modifier = Modifier, viewModel: ScheduleViewModel = koinViewModel(), state: ScheduleState) {

    var showTimePickerDialog by remember {
        mutableStateOf(false)
    }
    


    LaunchedEffect(key1 = Unit) {
        viewModel.onAction(UserAction.LoadApps)
    }

    if (showTimePickerDialog) {
        DateTimePickerDialog(
            onCancel = { showTimePickerDialog = false },
            onConfirm = {
                showTimePickerDialog = false
                viewModel.onAction(UserAction.Schedule(it))
            }
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Installed Apps", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(20.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.apps) { appInfo ->
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable(
                            indication = ripple(),
                            interactionSource = remember { MutableInteractionSource() }) {
                            viewModel.onAction(UserAction.AppSelected(appInfo))
                            showTimePickerDialog = true
                        }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = appInfo.icon,
                            contentDescription = appInfo.appName,
                            modifier = Modifier.size(56.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = appInfo.appName)
                    }
                }
            }
        }
    }
}