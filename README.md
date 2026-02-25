# 🛍️ Jamble Challenge Android App

Modern Android application developed with **Jetpack Compose**, focused on the faithful replication of a **User Profile** interface with high visual and architectural quality.

The project simulates an e-commerce/live shopping environment, displaying seller statistics, live history, reviews, and saved items, all managed with **local mock data** to simulate a real scenario.

This project demonstrates mastery of **declarative UI**, **complex state management**, and **Android design patterns**.

---

## 📱 Usage & Important Note

> **⚠️ ATTENTION:** When you launch the app for the first time, you will see **Empty States** for the lists (Lives, Reviews, Bookmarks). This is **intentional**.

To populate the lists with data, please perform a **Pull-to-Refresh** gesture (swipe down from the top of the content). This simulates the initial data fetching from a network source.

---

## 🌟 Features

- 👤 **Rich Profile Header**: Avatar, expandable bio, seller badges, and statistic pills (deliveries, rating, followers).
- 📑 **Tab Navigation (Sticky)**:
  - **Lives**: Grid of past and live broadcasts with viewer counters.
  - **Reviews**: List of reviews with ratings summary and stars.
  - **Bookmarks**: Grid of scheduled items with time badges and likes.
- 🔄 **Global Pull-to-Refresh**: Full screen refresh with pull gesture, simulating data reload.
- 💀 **Skeleton Loading & Shimmer**: Refined loading states for all components (cards, header, lists).
- 🎲 **Simulated Data**: Random generation of viewers, times, and likes to test different UI scenarios.
- 📏 **Responsive and Polished UI**: Compact number formatting (e.g., 42k, 1.2M), adaptive layouts, and consistent typography.
- ✏️ **Bio Editing**: Text field with auto-focus and save on click outside or done.

---

## 🧱 Architecture

The project follows **MVVM (Model-View-ViewModel)** with **Clean Architecture** principles, ensuring separation of concerns.

```
presentation/
├── screens/ -> Complete screens (ProfileScreen)
├── components/ -> Reusable UI components (LiveCard, ProfileHeader)
├── viewmodel/ -> State management (StateFlow) and UI logic
├── theme/ -> Design System definitions (Colors, Typography)
domain/
├── model/ -> Data classes (User, Live, Review)
├── repository/ -> Data contract interfaces
data/
├── mock/ -> Simulated data sources
└── repository/ -> Repository implementation
```

---

## 📁 Directory Structure

```
app/src/main/java/com/example/jamble_challenge/
├── core/
│   └── ui/ -> Base components (ShimmerEffect) and Theme
├── data/
│   ├── mock/ -> MockLives, MockReviews
│   └── repository/ -> ProfileRepositoryImpl
├── domain/
│   ├── model/ -> Data classes (User, Live, etc)
│   └── repository/ -> ProfileRepository (Interface)
└── presentation/
    ├── profile/
    │   ├── components/ -> LiveCard, ProfileBio, MetricsRow...
    │   ├── screens/ -> ProfileScreen, ProfileRoute
    │   └── viewmodel/ -> ProfileViewModel, ProfileUiState
    └── MainActivity.kt
```

## 📄 Main Classes Summary

- **ProfileScreen**: Main screen orchestrating `Scaffold`, `LazyColumn`, and `HorizontalPager`.
- **ProfileViewModel**: Manages `ProfileUiState`, controlling loading (`isLoading`), user data, and refresh logic.
- **LiveCard**: Versatile component to display lives and bookmarks, supporting "Live", "Time", and "Likes" badges.
- **ProfileBio**: Expandable text component with built-in editing mode.
- **SkeletonComponents**: Loading versions (shimmer) for header, cards, and list items.
- **MockLives**: Random data generator to ensure visual variety in tests.

---

## 🧪 Technologies Used

| Category | Technology / Library | Documentation / Link |
|---|---|---|
| Language | Kotlin | [Docs](https://kotlinlang.org/) |
| UI | Jetpack Compose (Material3 & Material) | [Docs](https://developer.android.com/jetpack/compose) |
| Dep. Injection | Koin (Android & Compose) | [Docs](https://insert-koin.io/) |
| Architecture | AndroidX Lifecycle & ViewModel | [Docs](https://developer.android.com/topic/libraries/architecture/viewmodel)|
| Asynchrony | Kotlin Coroutines & Flow | [Docs](https://kotlinlang.org/docs/coroutines-overview.html) |
| Lists | LazyColumn & LazyVerticalGrid | [Docs](https://developer.android.com/jetpack/compose/lists) |
| Paging/Tabs | Accompanist / Compose Foundation Pager | [Docs](https://developer.android.com/jetpack/compose/layouts/pager) |
| Gestures | PullRefresh (Material) | [Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/pullrefresh/package-summary) |

---

## 🎯 Implementation Highlights

- **Efficient State Management**: Use of `StateFlow` and `copy()` for immutable UI updates.
- **Realistic Simulation**: The app starts with an "Empty State" in tabs and loads full data after the first Pull-to-Refresh, simulating network behavior.
- **Complex Layouts**: Use of nested `Box`, `Row`, `Column` and alignment modifiers to achieve the exact design (e.g., badges overlaying images).
- **Focus Handling**: Custom logic to clear focus and save bio when clicking outside the text field.

---

## 🔧 Installation and Execution

Prerequisites:
- Android Studio Iguana or higher
- JDK 17
- Android SDK 33+

```bash
# Clone the repository
git clone https://github.com/your-username/jamble-challenge.git

# Open in Android Studio
# Wait for Gradle synchronization

# Run on emulator or physical device
Run ▶️
```

---

## 📸 Screenshots

> *The app has loading states (Skeleton), populated data, and empty states.*

<p align="center">
  <video src="videos/Screen_recording_20260225_022415.mp4" width="300" controls title="App Demo"></video>
</p>

---

## 🧑‍💻 Author

Developed as part of the Jamble technical challenge.
Focused on **Clean Code**, **UI/UX**, and **Performance**.

---

## 📝 License

```
The MIT License (MIT)

Copyright (c) 2024 Jamble Challenge

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```