
# Pomodoro Android App

This is a simple Pomodoro timer application for Android, designed to help you manage your work sessions and breaks using the popular Pomodoro Technique.

## Features

- **Start Timer:** Tap the start button to begin a 25-minute focus session.
- **Progress Bar:** A circular progress bar visually tracks your remaining time.
- **Reset Timer:** Easily reset the timer at any point.
- **State Persistence:** The timer's state is saved and restored automatically when you rotate your phone or leave the app, ensuring you don't lose your progress.
- **Simple UI:** A clean and straightforward user interface makes the app easy to use.

## Screenshots

<div align="center">
  <img src="https://github.com/user-attachments/assets/ae45d8bd-8d50-4892-b7c9-0e407fd0d5d6" alt="App Screenshot 1" width="250"/>
  <img src="https://github.com/user-attachments/assets/d53bf83b-ffac-4e41-8084-832827fcf861" alt="App Screenshot 2" width="250"/>
  <img src="https://github.com/user-attachments/assets/c12c19e2-2aff-420d-b6ba-bf856b974ac7" alt="App Screenshot 3" width="250"/>
  <img src="https://github.com/user-attachments/assets/1ff79c9b-9942-494c-a191-0fa4563f9222" alt="App Screenshot 4" width="250"/>
  <img src="https://github.com/user-attachments/assets/843083d7-7796-4fa1-8ed6-35bf0a612b90" alt="App Screenshot 5" width="250"/>
</div>


## How It Works

The app extends `CountDownTimer` class to manage the timer logic. The `onSaveInstanceState` and `onRestoreInstanceState` methods are implemented to handle configuration changes (like screen rotation) and app interruptions. This ensures a seamless user experience, as the timer picks up exactly where you left off.

## Technical Details

- **Language:** Java
- **UI:** XML Layouts and view binding
- **Architecture:** Android's built-in lifecycle management for state preservation.

## Getting Started

To run this project:
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or a physical device.

