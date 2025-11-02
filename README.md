# 📱 TidyText – Intelligent SMS Summarizer

### 🧠 Overview

**TidyText** is an **AI-powered Android application** that declutters your SMS inbox by automatically categorizing and summarizing messages.
Instead of scrolling through dozens of texts, users get a **single, easy-to-read daily summary**, such as:

> “3 Recharge offers · 2 Travel updates · 1 Bank alert”

The app intelligently filters promotional, transactional, and government messages — providing clarity, organization, and control in your inbox.

---

## 🚀 Features

✅ **Smart SMS Categorization**

* Automatically groups messages into tabs:
  `All`, `Personal`, `Govt`, `Bookings`, `Recharge`, `Transactions`, `Spam`, and `Website Alerts`.
* Add your own custom tabs with rules in **Settings**.

✅ **Daily Summary View**

* Generates short summaries like “2 new recharge offers and 1 cashback reward today.”
* Displays total message counts per category.

✅ **Spending Dashboard**

* Shows interactive charts using **MPAndroidChart**.
* Lets users set spending limits for **daily, weekly**, or **monthly** budgets.

✅ **Website Alerts Integration**

* Includes government exam and alert-based messages from official websites under **Website Alerts**.
* Summarized automatically in the daily overview.

✅ **Light Mode Optimized 🌞**

* The app looks and performs best in **Light Mode** on Android phones for clear readability.

✅ **Downloadable APK**

* The repository includes a **prebuilt `.apk` file** in the `/app/release` folder for direct installation and testing.

---

## 🧩 Tech Stack

| Layer           | Technology                     |
| --------------- | ------------------------------ |
| Frontend        | Android (Java + XML)           |
| Data Handling   | Rule-based NLP Text Matching   |
| Visualization   | MPAndroidChart                 |
| Storage         | SharedPreferences / Local JSON |
| Version Control | Git + GitHub                   |
| IDE             | Android Studio                 |

---

## ⚙️ Installation & Setup

### 1️⃣ Prerequisites

* Android Studio (latest version)
* Git installed on your system
* Android SDK 33+
* Minimum Android version: **8.0 (Oreo)**

---

### 2️⃣ Option A: Run via Android Studio

```bash
git clone https://github.com/NehaSharma1607/TidyText.git
```

* Open **Android Studio → File → Open → Select project folder**
* Wait for **Gradle sync** to finish
* Click **▶️ Run** to launch on an emulator or connected device

---

### 3️⃣ Option B: Install APK Directly

* Go to the GitHub repo → `app/release/TidyText.apk`
* Download the `.apk` file
* Transfer it to your Android device
* Enable “Install from Unknown Sources” (if prompted)
* Tap to install and open the app

---

## 📲 How to Use

1. **Launch TidyText** — the app loads categorized SMS automatically.
2. Navigate via tabs:

   * *Personal*, *Govt*, *Recharge*, *Transactions*, etc.
3. Use the **Settings ⚙️ icon** to:

   * Create new tabs
   * Define keywords (e.g., “exam”, “IRCTC”, “offer”) for automatic categorization
4. Click **“Set Limits”** to define spending caps.
5. Tap **Summarize** to view your daily message digest.
6. View analytics under the **Insights** tab.

---

## 📈 Impact Metrics

| Metric                     | Description                                    |
| -------------------------- | ---------------------------------------------- |
| 📉 Inbox Clutter Reduction | % reduction in unread promotional messages     |
| ⏱️ Time Saved              | Average user time saved per day                |
| 💬 Categorization Accuracy | % correctly grouped messages                   |
| 💰 Spending Awareness      | % of users tracking their financial SMS alerts |

---

## 🧩 Architecture

```
        ┌──────────────────────────┐
        │ Incoming SMS Parser       │
        └────────────┬─────────────┘
                     │
          ┌──────────▼──────────┐
          │ Categorization Engine│
          │ (Keyword Matching)   │
          └──────────┬──────────┘
                     │
     ┌───────────────▼────────────────┐
     │ Local Storage (SharedPrefs)    │
     │ Tabs, Limits, Rules, Messages  │
     └───────────────┬────────────────┘
                     │
        ┌────────────▼────────────┐
        │   UI Layer (RecyclerView│
        │   Tabs + Charts + Alerts│
        └────────────┬────────────┘
                     │
          ┌──────────▼──────────┐
          │ Summary Generator   │
          │ (Daily/Weekly View) │
          └─────────────────────┘
```

---

## 💡 Design Decisions

| Choice                | Reason                                                  |
| --------------------- | ------------------------------------------------------- |
| **Java**              | Reliable and widely supported for Android apps          |
| **Rule-based engine** | Fast and explainable text classification                |
| **Local Storage**     | Offline-first design with no cloud dependency           |
| **Light mode UI**     | Higher readability and consistent with Android defaults |
| **MPAndroidChart**    | Simple and efficient data visualization                 |

---

## 🔮 Future Enhancements

* ML-based text classification for more accurate categorization
* Push notifications for new messages per category
* Cloud backup and cross-device sync
* Multi-language SMS support
---
