
# Space Reserve

## Project Overview

The **Space Reserve** was developed as part of the Mobile Integration module, focusing on modern Android app development practices. This project demonstrates advanced mobile development techniques, including the use of Jetpack Compose, MVVM architecture, navigation components, and Room Database for user management. It emphasizes modularity and scalability.
## Features

### Core Architecture and Components

-   **Jetpack Compose**: Used for building a responsive and modern UI.
    
-   **MVVM Architecture**: Ensures a clean separation of concerns with ViewModel, Repository, and UI layers.
    
-   **Navigation Component**: Implements a navigation stack for seamless screen transitions.
    
-   **Room Database**: Manages local data storage for user details (registration, updates, and deletion).
    
-   **DataStore**: Efficiently stores lightweight user preferences.
    

### User Functionality

-   **User Registration and Management**:
    
    -   Users can register with details stored in the Room Database.
        
    -   Users can update their profiles.
        
    -   Admins can delete user accounts.
        
-   **Dynamic Room Availability**:
    
    -   Fetches real-time room availability for specific dates from a Flask-based API.
        
    -   Displays updated room data dynamically.
        
-   **Room Booking**:
    
    -   Allows users to book rooms and fetches their bookings based on student ID.
        

### User Interface

-   **Composable Functions**: Modular and reusable components for building the UI.
    
-   **Scrollable Lists**:
    
    -   Utilizes LazyColumn for smooth, efficient scrolling.
        
    -   Displays room cards with detailed information and dynamic images.
        
-   **Material Design**: Adheres to Material Design principles for consistency and aesthetics.
    
-   **Animations**: Enhances user interaction with smooth animations.
    
-   **Screen Adaptability**: Responsive layouts for compact (vertical scrolling) and expanded (horizontal scrolling) views.
    

### Data and Network Management

-   **Retrofit**: Fetches data from the Flask backend for room availability and booking information.
    
-   **Coil**: Loads and displays images efficiently with fallback handling for invalid URLs.
    
-   **State Management**:
    
    -   Utilizes **MutableStateFlow** and **collectAsState** to handle UI updates and data transitions seamlessly.
        

## Technologies and Libraries

-   **Programming Language**: Kotlin
    
-   **Frameworks and Libraries**:
    
    -   Jetpack Compose
        
    -   Room Database (with KSP for schema generation)
        
    -   DataStore
        
    -   Navigation Component
        
    -   Retrofit
        
    -   Coil (image loading)
        
    -   Material Design Components
        

## Project Structure

The project follows a modular and organized package structure:

```
com.example.roombooking
│
├── dao          # Data Access Objects for Room Database
├── database     # Room Database and related setup
├── model        # Data models
├── navigation   # Navigation setup and routes
├── network      # Retrofit API client and services
├── ui.theme     # Theming and styles
├── view         # UI screens and composables
├── viewmodel    # ViewModel for state management
```

## How It Works

1.  **User Registration**:
    
    -   Users register via the app, storing details in the Room Database.
        
    -   Admins can view and delete user profiles.
        
2.  **Room Availability**:
    
    -   Rooms are fetched dynamically from the Flask API based on the selected date.
        
    -   Images and statuses are displayed using Coil and LazyColumn.
        
3.  **Room Booking**:
    
    -   Users book available rooms by providing the required details.
        
    -   Bookings are validated and stored via the API.
        
4.  **User Profile Management**:
    
    -   Users can update their details at any time.

## Installation and Usage

### Prerequisites

-   **Android Studio** for running the app.
    
-   Internet access for API integration.
    

### Steps

1.  Clone the repository:
    
    ```
    git clone https://github.com/Harjappan-Singh/Room_Booking_Android
    ```
    
        
2.  Build and run the Android app on your device/emulator via Android Studio.
    

## Screenshots

<div align="center"> | | | | | |-----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------| | [login screen](https://github.com/user-attachments/assets/ead32c31-b325-4be1-a697-8e3e6adbca54) | ![Screenshot_20241210_192528](https://github.com/user-attachments/assets/8595fce3-f5c0-49d8-922b-9e6209bd8770) | ![Screenshot_20241210_192535](https://github.com/user-attachments/assets/a743543d-5cfc-43eb-bc81-f83acaae494c) | ![Screenshot_20241210_192541](https://github.com/user-attachments/assets/f8c96547-336f-4cd9-8e7c-55061cd48b6d) | | ![Screenshot_20241210_192554](https://github.com/user-attachments/assets/d0003c0e-01e4-4b89-a18d-488a0290627e) | ![Screenshot_20241210_192605](https://github.com/user-attachments/assets/1ecc1c79-f6ef-4cbe-a52d-be28832be65e) | ![Screenshot_20241210_192620](https://github.com/user-attachments/assets/1b79749b-f8fe-457e-ad7a-a82c3f52248f) | ![Screenshot_20241210_192625](https://github.com/user-attachments/assets/c48b653f-915c-4f2a-8eae-a6a7e9714fe2) | | ![Screenshot_20241210_192632](https://github.com/user-attachments/assets/bfa70c78-ddb0-4ef2-b45a-ceab4c1190cf) | ![Screenshot_20241210_192640](https://github.com/user-attachments/assets/22e6919e-995e-41c0-9500-f053a4026270) | | | </div>


## Demo

Watch the app in action on YouTube [here](https://youtube.com/shorts/9Tm7U-xgYYQ?feature=shared)

## Collaborators

-   **Harjappan Singh**
    
-   **Yee Chen**
    

## API Repository

The Flask API code can be found [here](https://github.com/LeafMonarch/reserve_space_api).

----------

Thank you for checking out our project! Feel free to reach out for any queries or collaboration opportunities.
