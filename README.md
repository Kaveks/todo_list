# To-Do List App

A simple To-Do List app built with Jetpack Compose for managing daily tasks efficiently.

## Features

- Add new tasks
- Mark tasks as completed
- Remove tasks
- Persistent storage using DataStore
- Smooth UI animations

## Technologies Used

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit for Android
- **Android DataStore**: For saving tasks persistently
- **Material3 Components**: Enhanced UI design

## Installation

1. Clone the repository:
   ```sh
   git@github.com:Kaveks/todo_list.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle files.
4. Run the app on an emulator or physical device.

## How to Use

1. Enter a task in the input field and press **Add Task**.
2. Mark tasks as completed using the checkbox.
3. Remove tasks using the **Remove** button.

## Project Structure

```
app/
├── java/com/example/todolist/
│   ├── MainActivity.kt
│   ├── TaskApp.kt
│   ├── TaskItem.kt
│   ├── data/TaskDataStore.kt
├── res/
│   ├── layout/
│   ├── values/
├── AndroidManifest.xml
```

## Contributing

Feel free to submit pull requests to improve the app!

## License

This project is licensed under the MIT License.

