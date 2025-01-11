# ExpenseApp

ExpenseApp is a Kotlin Multiplatform project targeting Android and iOS. It uses Jetpack Compose for UI and SQLDelight for database management.

## Project Structure

- **`/composeApp`**: Contains shared code for Compose Multiplatform applications.
  - **`commonMain`**: Common code for all targets.
  - **`androidMain`**: Code specific to the Android platform.
  - **`iosMain`**: Code specific to the iOS platform.

- **`/iosApp`**: Contains the iOS application entry point and any SwiftUI code.

## Dependencies

- **Kotlin**: 2.1.0
- **Jetpack Compose**: 1.7.3
- **SQLDelight**: 2.0.2
- **Koin**: 4.0.1
- **Voyager**: 1.1.0-beta03
- **Moko Resources**: 0.24.4
