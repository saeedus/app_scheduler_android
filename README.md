# Android App Scheduler

This is an Android application that allows users to schedule other installed applications to launch at a specific date and time. It provides features for scheduling, editing, and canceling app launches, along with a history of executed and upcoming schedules.

## Features

*   **Schedule App Launches**: Users can select any installed Android application and set a precise date and time for it to launch.
*   **Edit Existing Schedules**: Users can modify the date and time of previously set upcoming schedules.
*   **Cancel Schedules**: Users have the option to cancel upcoming schedules, preventing the app from launching at the set time.
*   **Multiple Schedules**: Supports scheduling multiple applications without time conflicts.
*   **Schedule History**: Keeps a record of all scheduled events, indicating whether they were executed, are upcoming, or were cancelled.
*   **Permission Handling**: Guides the user through granting necessary permissions (Exact Alarm and Accessibility Service) upon first launch.

## Technologies Used

*   **Kotlin**: The primary programming language for Android development.
*   **Jetpack Compose**: Modern toolkit for building native Android UI.
*   **Android Architecture Components**:
    *   **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
    *   **Room Persistence Library**: Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite. Used for persisting scheduled alarm data.
*   **Koin**: A pragmatic lightweight dependency injection framework for Kotlin developers.
*   **Android AlarmManager**: System service for scheduling applications to run at a future time.
*   **Android AccessibilityService**: Used to programmatically launch applications.
*   **Coil**: An image loading library for Android backed by Kotlin Coroutines.
*   **Kotlin Coroutines**: For asynchronous programming and managing background tasks.

## Setup and Installation

To set up and run this project locally, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/saeedus/app_scheduler_android.git
    cd AppScheduler
    ```
2.  **Open in Android Studio:**
    Open the project in Android Studio (newer version recommended).
3.  **Sync Gradle:**
    Android Studio should automatically prompt you to sync the project with Gradle files. If not, click "Sync Project with Gradle Files" in the toolbar.
4.  **Build the project:**
    Go to `Build` > `Make Project` or click the hammer icon in the toolbar.
5.  **Run on a device or emulator:**
    Connect an Android device or start an emulator. Select your target device from the dropdown in Android Studio and click the "Run" button (green triangle).

## Usage

1.  **Grant Permissions**: Upon the first launch, the app will guide you to grant "Exact Alarm" and "Accessibility Service" permissions. These are crucial for the app's functionality.
2.  **Schedule an App**:
    *   Navigate to the "Apps" screen (usually the first screen).
    *   Tap on any installed application from the list.
    *   A date and time picker dialog will appear. Select your desired date and time.
    *   Confirm your selection. The app will now be scheduled.
3.  **View Schedules**:
    *   Navigate to the "Schedules" screen.
    *   You will see a combined list of all scheduled apps.
    *   **CheckCircle**: Indicates an executed schedule.
    *   **AccessTime**: Indicates an upcoming schedule.
    *   **Error️**: Indicates a schedule that was not executed and its time has passed.
    *   **Close**: Indicates a cancelled schedule.
4.  **Edit a Schedule**:
    *   On the "Schedules" screen, tap on an **upcoming** schedule (indicated by ⌛).
    *   The "Edit Schedule" screen will appear, showing the current details.
    *   Tap "Change Schedule Time" to open the date and time picker and set a new time.
    *   Confirm your changes.
5.  **Cancel a Schedule**:
    *   On the "Schedules" screen, tap on an **upcoming** schedule (indicated by ⌛).
    *   On the "Edit Schedule" screen, tap the "Cancel Schedule" button.
    *   The schedule will be marked as cancelled and will no longer trigger.
