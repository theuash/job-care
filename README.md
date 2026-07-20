# JobCare

Basic Android app (Jetpack Compose) + Spring Boot backend.

## Project structure

```
jobcare/
├── app/           — Android app (Jetpack Compose, Material3)
├── backend/       — Spring Boot REST API
├── android-sdk/   — local Android SDK (not committed)
├── build.gradle.kts
└── settings.gradle.kts
```

## Build

**APK:**
```bash
./gradlew :app:assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

**Backend JAR:**
```bash
./gradlew :backend:bootJar
```
Output: `backend/build/libs/backend-0.0.1-SNAPSHOT.jar`

## Run

1. Start backend:
   ```bash
   java -jar backend/build/libs/backend-0.0.1-SNAPSHOT.jar
   ```
   API: `GET http://localhost:8080/api/hello`

2. Install APK on emulator/device:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

3. Open the app and tap "Call Backend". The Android emulator reaches
   `http://10.0.2.2:8080/api/hello` (host loopback).

## Requirements

- JDK 17+
- Android SDK (auto-installed to `android-sdk/` on build)
