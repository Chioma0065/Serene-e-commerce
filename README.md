# Serene Commerce - Mobile Retail Implementation Task

Welcome to the Serene Commerce coding challenge! This project is a foundation for a mindful, unhurried mobile shopping experience. Your task is to complete the implementation of the Home Screen and the Product Card component based on the provided design specifications.

## Design Vision: Organic Warm Minimalism

This design system prioritizes visual clarity and calm focus. It avoids aggressive sales patterns in favour of:
- **Tonal Transitions**: Soft, off-white backgrounds (Light) and deep charcoal sage (Dark).
- **Organic Shapes**: Soft contours and rounded corners (16dp for cards, pill shapes for buttons).
- **Visual Breathability**: Generous white space and clear typography.

### Visual References
You can find the target designs in the `screenshots/` folder:
- **Home Screen**: [Light Mode](screenshots/home_screen_light.png) | [Dark Mode](screenshots/home_screen_dark.png)
- **Detail Screen**: [Light Mode](screenshots/details_screen_light.png) | [Dark Mode](screenshots/details_screen_dark.png)

## Your Objectives

Your goal is to address the `TODO` markers scattered throughout the codebase to achieve a functional app that matches the design screenshots.

### 1. Fix the initial crashes that occurs when you run the application
When you try to install the application on your device or emulator, the application is expected to crash. You are required to go through the logcat, debug and fix the cause(s) of the crash(es).

### 2. Complete the `SereneProductCard` Component
Located in `app/src/main/java/com/assessment/sereneecommerce/ui/theme/Components.kt`.

- **Image Loading**: Replace the placeholder `Box` with an actual image loader (e.g., using **Coil**). Note: You may need to add the dependency to `build.gradle`.
- **Overlay Badges**: Position the `category` and `subCategory` badges on top of the product image as seen in the screenshots.
- **Product Details**: Display the `description` and `keywords` (tags) below the product title.
- **Interactions**: 
    - Add a "Favourite" heart icon button in the top-right corner of the image.
    - Integrate the existing `SereneQuantityPicker` and `SereneButton` ("Add to Cart") at the bottom of the card.

### 3. Implement the Product List in `HomeScreen`
Located in `app/src/main/java/com/assessment/sereneecommerce/ui/home/HomeScreen.kt`.

- **Vertical List**: Create a vertical list of products using a `LazyColumn` or similar approach.
- **Filtering**: Implement logic to filter the product list based on the `selectedCategory` from the category chips.
- **Integration**: Use the `SereneProductCard` to render each item in the list.

### 4. Robust State Handling
The `ProductRepository` is intentionally designed to randomly throw exceptions for "teaching purposes".

- **Loading State**: Implement a clean loading UI (e.g., a centered `CircularProgressIndicator`).
- **Error State**: Implement an error UI that displays the error message and provides a "Retry" button to re-fetch the data.

### 5. Navigation & Product Detail Screen
- **Navigation**: Implement navigation from the `HomeScreen` to the `DetailScreen` when a product card is clicked.
- **Data Passing**: Ensure that all necessary product details (title, price, image URL, description, category, rating, etc.) are passed as arguments from `HomeScreen` to `DetailScreen` to correctly populate the screen.
- **UI Implementation**: Complete the `DetailContent` in `app/src/main/java/com/assessment/sereneecommerce/ui/detail/DetailScreen.kt` based on the provided detail screen screenshots. Utilize the established theme and components to maintain visual consistency.

## Project Structure Highlights

- **Theming**: See `ui/theme/Theme.kt` and `ui/theme/Type.kt`. The project uses a custom Material 3 theme.
- **Components**: `ui/theme/Components.kt` contains pre-built minimalist components like `SereneButton`, `SereneSearchBar`, and `SereneQuantityPicker`.
- **Data Layer**: 
    - `data/remote/ProductApiService.kt`: Retrofit service for fetching products.
    - `data/repository/ProductRepository.kt`: Repository with intentional random failure logic.
- **DI**: A simple `ServiceLocator` is provided for manual dependency injection.

## Important
You are not required to make changes to the code in the data and di layers as they do not cover your task evaluation. 
Aside from the UI components that you were asked to update, all other components should remain untouched unless absolutely necessary. 
You are advised to go through the entire codebase before you start making changes. This will help you better understand what you're required to do. 
All the theme components have been implemented also. 

## Evaluation Criteria

- **UI Fidelity**: How closely does the implementation match the provided screenshots?
- **Code Quality**: Clean, idiomatic Kotlin and Compose usage.
- **State Management**: Correct handling of `Success`, `Loading`, and `Error` states.
- **Navigation**: Correct implementation of screen transitions and argument passing.
- **Problem-Solving**: Fixing runtime exceptions, and successful integration of image loading and category filtering.

Good luck! I look forward to seeing your mindful commerce implementation.
