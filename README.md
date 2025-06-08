# Yahewa
Yahewa is a mobile weather application built for Android. It provides users with real-time weather updates, forecasts, and other
weather-related information. This app is developed using Kotlin and Android SDK, ensuring a smooth, modern, and efficient user
experience.

---
## Motivation
- I wanted to build a mobile app.
- A weather application is interesting.
- Learn Kotlin.

---
## Features
- **Real-Time Weather Updates**: Get the latest weather conditions for your location.
- **Forecast Data**: View hourly and daily forecasts.
- **City Search**: Search weather updates for cities around the globe.
- **User-Friendly Interface**: Minimalistic and easy to navigate UI.

---
## Technology Stack
- **Programming Language**: Kotlin
- **User Interface**: Jetpack Compose
- **Android SDK Version**: Target SDK 35
- **Minimum SDK Version**: 21
- **Build System**: Gradle
- **Architecture**: MVVM (Model-View-ViewModel)

---
## Screenshots
<p align="center">
  <img src="path/to/screenshot1.png" alt="Screenshot1" width="240"/>
  <img src="path/to/screenshot2.png" alt="Screenshot2" width="240"/>
</p>
---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/yahewa.git
   cd yahewa
   ```

2. Open the project in **Android Studio**.

3. Sync the project with Gradle files.

4. Build and run the project by selecting a connected device or emulator.

---

## Usage
### API Key Configuration
This app requires an API key from a weather service provider (e.g., OpenWeatherMap).
1. Register for an account and get your API key from the provider.
2. Add your API key in the `api_key` field within the `res/values/strings.xml` file:

   ```xml
   <string name="api_key">YOUR_API_KEY_HERE</string>
   ```

### Running the App
- After configuring the API key, simply build and run the application.
- Allow location permissions (optional) to get weather updates for your current location.

---

## Development
### Dependencies
Key dependencies used in this project include:
- **Retrofit**: For API communication.
- **Glide**: For image loading.
- **Coroutines**: For asynchronous programming.
- **Jetpack Components**: ViewModel, LiveData, Navigation, etc.

Refer to `build.gradle` files for a complete list of dependencies.