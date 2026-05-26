# MyOrders – OceanX Agency Android Assignment

A pixel-accurate implementation of the "My Orders" screen for the OceanX Agency Mobile Developer Internship assignment.

---

## Features Implemented

- ✅ Native Android (Kotlin + XML) — no Flutter/React Native
- ✅ RecyclerView for order listing with CardView items
- ✅ Four filter tabs: All Orders, Completed, Cancelled, Booked Again
- ✅ Live search by Order ID or Location
- ✅ Info banner with dismiss button
- ✅ Bottom Navigation (Home, Orders, Payments, Account)
- ✅ Invoice & Book Again buttons with click effects
- ✅ Color-coded status badges (red = Cancelled, green = Completed, yellow = Booked Again)
- ✅ Yellow header matching reference design
- ✅ Clean, well-structured code

---

## Project Structure

```
app/src/main/
├── java/com/oceanx/myorders/
│   ├── model/
│   │   └── Order.kt              ← Data model + OrderStatus enum
│   ├── adapter/
│   │   └── OrderAdapter.kt       ← RecyclerView adapter
│   └── ui/
│       └── MainActivity.kt       ← Main screen logic
├── res/
│   ├── layout/
│   │   ├── activity_main.xml     ← Full screen layout
│   │   └── item_order.xml        ← Single order card layout
│   ├── drawable/                 ← All vector icons + backgrounds
│   ├── menu/
│   │   └── bottom_nav_menu.xml   ← Bottom nav items
│   ├── values/
│   │   ├── colors.xml
│   │   ├── strings.xml
│   │   └── themes.xml
│   └── color/
│       └── bottom_nav_tint.xml   ← Icon tint selector
└── AndroidManifest.xml
```

---

## Setup Steps

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 8 or higher
- Android SDK 34
- Kotlin plugin installed

### Steps to Run

1. **Open Android Studio**

2. **Open the project:**
   - File → Open → Select the `MyOrdersApp` folder

3. **Wait for Gradle sync** to complete (it downloads dependencies automatically)

4. **Add a launcher icon** (Android Studio requires this):
   - Right-click `res` → New → Image Asset
   - Choose any icon or use the default

5. **Run the app:**
   - Connect an Android device (Android 7.0+) or start an emulator
   - Press the ▶ Run button or `Shift+F10`

---

## Dependencies (auto-downloaded by Gradle)

```gradle
implementation 'androidx.core:core-ktx:1.12.0'
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.11.0'
implementation 'androidx.recyclerview:recyclerview:1.3.2'
implementation 'androidx.cardview:cardview:1.0.0'
implementation 'androidx.coordinatorlayout:coordinatorlayout:1.2.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
```

---
