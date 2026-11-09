# Diet Reminder (Android)

Simple app: shows the 30-day South Indian diet plan and sends 7 daily notifications
(6:30, 8:00, 11:00, 1:30, 4:30, 7:30, 9:30) with that day's dish.

## Get the APK — Option A: GitHub (no software needed)
1. Create a free account at github.com → **New repository** (e.g. `diet-reminder`).
2. Click **uploading an existing file**, drag in ALL files/folders from this zip
   (including the `.github` folder), then **Commit changes**.
3. Open the **Actions** tab → wait ~5 min for "Build APK" to turn green.
4. Click the run → download **DietReminder-apk** → unzip → `app-debug.apk`.
   (Tip: if the `.github` folder doesn't upload, use **Add file → Create new file**, name it
   `.github/workflows/build-apk.yml` and paste that file's contents.)
5. Copy to phone, open it, allow "Install unknown apps".

## Option B: Android Studio
Open this folder in Android Studio → Build → Build APK(s).

## After installing
- Open the app once and tap **Allow** for notifications.
- Settings → Apps → Diet Reminder → Battery → **Unrestricted** (so reminders aren't blocked).
- Tap **Send test notification** to check.
- Tap **Restart plan from Day 1 today** whenever you want to start over.

## Customize
- Dishes and times: `app/src/main/java/com/thanu/dietreminder/MealPlan.java`
