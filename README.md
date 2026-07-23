# JobCare Voice

India's first AI voice job portal for blue-collar workers — employer dashboard.

## Project structure

```
jobcare/
├── app/           — Android employer dashboard (Jetpack Compose, Material3)
├── backend/       — Spring Boot REST API
├── android-sdk/   — local Android SDK (not committed)
├── build.gradle.kts
└── settings.gradle.kts
```

## Screens

| Screen | Route | Content |
|---|---|---|
| Dashboard | `/dashboard` | Stats (employers, placements, calls), retention, recent workers, navigation tiles |
| Workers | `/workers` | Matched workers list with skill, location, wage, match score |
| Worker Detail | `/workers/{id}` | Full profile, Aadhaar verified badge, contact button |
| Jobs | `/jobs` | Active/filled job postings + create new posting |
| Analytics | `/analytics` | Weekly call volume chart (wk1–wk8), key metrics |

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
   ./gradlew :backend:bootRun
   ```
   API: `http://localhost:8080/api/stats`

2. Install APK on emulator:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

3. The app connects to `http://10.0.2.2:8080` (emulator → host loopback).

## API Endpoints

| Method | Path | Description |
|---|---|---|
| POST | `/api/login` | Employer sign-in (mocked) |
| GET | `/api/stats` | Dashboard stats |
| GET | `/api/workers` | All matched workers |
| GET | `/api/workers/{id}` | Worker detail |
| GET | `/api/jobs` | Job postings list |
| POST | `/api/jobs` | Create job posting |
| GET | `/api/analytics/calls` | Weekly call volume (wk1–wk8) |

## Requirements

- JDK 21+ (system default may be Java 25, JDK 21 configured in `gradle.properties`)
- Android SDK (auto-installed to `android-sdk/` on build)
