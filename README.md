# Currency App - README

## **Features**

### First Screen: Currency Conversion
1. **From/To Dropdown Lists**:
   - Displays all available currencies.
   - Users can select currencies to convert between.
2. **Amount Input Field**:
   - Accepts numeric values for the amount to convert (default is 1).
   - Automatically updates the converted value when the amount changes.
3. **Converted Value Field**:
   - Displays the converted currency value in real-time.
4. **Swap Button**:
   - Allows users to swap the "From" and "To" currencies.

### Second Screen: Conversion History
1. **Historical Data**:
   - Displays currency conversion history for the last 4 days.
2. **Day-by-Day List**:
   - Shows detailed historical rates for the selected "From" and "To" currencies.

---

## **Technical Stack**
The project is built using the following technologies and principles:
- **MVVM Architecture**
- **KTX Extensions**
- **Data Binding**
- **Navigation Component**
- **Hilt for Dependency Injection**
- **Coroutines for Asynchronous Operations**
- **Retrofit for Network Calls**

---

## **Error Handling**
The application includes precise error handling to manage expected errors:
- Differentiates between API errors and internet connection errors.
- Displays meaningful messages to users in case of errors.

---

## **Prerequisites**
Before building and running the app, ensure you have the following installed:

1. **Android Studio:** [Download](https://developer.android.com/studio)
2. **Java Development Kit (JDK):** Install the latest version from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK.
3. **Android SDK:** Install necessary components using the Android Studio SDK Manager.
4. **Android Device or Emulator:** Use a physical Android device or set up an emulator in Android Studio.

---

## **Setup Instructions**

### 1. Clone the Repository:
```bash
   git clone <repository-url>
```

### 2. Open Project in Android Studio:
- Open Android Studio.
- Select "Open an existing Android Studio project."
- Navigate to the cloned repository and open the `build.gradle` file of the app module.

### 3. Sync Gradle Dependencies:
- Gradle will sync automatically. If not, manually sync by clicking **File > Sync Project with Gradle Files**.

### 4. Configure Emulator (Optional):
- Set up an emulator via **Tools > AVD Manager** or connect a physical device.

---

## **Building and Running the App**

1. Click the green **Run** button in the Android Studio toolbar.
2. Select a target device (emulator or connected device).
3. Wait for the build to complete and the app to launch automatically on the device.

---

## **Testing the App**

### Unit Tests:

### Manual Testing:
- Verify the conversion feature by selecting different currencies and inputting various amounts.
- Check the swap functionality.
- Review the historical data on the second screen for accuracy.

---

## **Building a Release Version**
1. Navigate to **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
2. Locate the generated APK in the `build/outputs/apk` directory.

---

## **Publishing the App**
Follow these steps to publish the app:
1. Sign the APK via **Build > Generate Signed Bundle / APK**.
2. Upload the signed APK to the [Google Play Console](https://play.google.com/console/about/).

---

## **Notes**
- This project uses the [Fixer API](https://fixer.io/) for fetching foreign exchange rates.

---

## **Contact**
For any questions or issues related to the app, feel free to reach out.

