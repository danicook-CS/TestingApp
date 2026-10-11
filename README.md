# TrailMate Task List (Kotlin + Jetpack Compose)

A small Android sample app for the TrailMate research milestone. It is intentionally simple, but includes real interaction rather than only displaying “Hello World.”

## Features

- Starts with three sample hiking preparation tasks.
- Add a task using the text field and **Add task** button (or the keyboard's Done action).
- Mark tasks complete or incomplete.
- Hide completed tasks and show all tasks again.
- Delete tasks.
- See the completed-task count update as you interact with the checklist.
- Rejects blank task names by disabling the Add task button.

## Technology choices

- **Kotlin**: main programming language.
- **Android Studio**: IDE, emulator, and Android build tools.
- **Jetpack Compose + Material 3**: declarative Android UI.
- **Gradle**: build system and dependency manager.
- **Git/GitHub**: version control and sharing the individual research repo.

This sample keeps task data in memory, so tasks reset when the app process is restarted. That is a deliberate scope choice for a small milestone app; persistent storage could be a follow-up improvement.

## Run it

1. Install Android Studio and its recommended Android SDK components.
2. Extract this project folder.
3. In Android Studio, choose **Open** and select the `TrailMateTaskList` folder.
4. Allow Gradle to sync and download dependencies.
5. Start an Android emulator (or connect an Android device with developer options enabled).
6. Click **Run**.

Requirements used in this project:
- Android Gradle Plugin `8.7.3`
- Kotlin `2.0.21`
- Compile SDK `35`
- JDK 17 is recommended for this Android Gradle Plugin version.

If Android Studio reports that SDK 35 is missing, install Android SDK Platform 35 from **Tools → SDK Manager**.

## Learn the code

- `MainActivity.kt`: app entry point, sample tasks, add/filter/count logic.
- `TaskRow`: one row in the checklist; checkbox and delete actions are passed in as callbacks.
- `app/build.gradle.kts`: Android configuration and libraries.
- `settings.gradle.kts`: project name, app module, and dependency repositories.

Useful things to be able to explain in your demo:
1. `mutableStateListOf` stores tasks and lets Compose update the screen when the list changes.
2. `remember` keeps UI state during recomposition.
3. `onClick` and `onCheckedChange` respond to user interaction.
4. Gradle resolves the libraries declared in the app module's dependencies.

## Suggested Git workflow

Create a **personal GitHub repository** for this milestone. After extracting the project, open a terminal in the project folder and run:

```bash
git init
git add .
git commit -m "Create Jetpack Compose task list starter"
```

Then create a repository on your personal GitHub account and follow GitHub's instructions to add it as a remote and push your commit. Make additional meaningful commits as you understand and improve the app, for example:

```bash
git add .
git commit -m "Add task creation and completion"
git add .
git commit -m "Add task filtering and delete action"
```

Do not claim these commits were made unless you actually run the commands and push them.

## Short demo video outline (about 1 minute)

1. Launch the app and show the starter checklist.
2. Add a task such as “Bring a flashlight.”
3. Mark one task complete and show the count update.
4. Hide completed tasks, then show all tasks again.
5. Delete a task.
6. Briefly explain that the app uses Kotlin, Compose, and Gradle.

Record the emulator or Android Studio window using your preferred screen recorder. The video needs to be recorded by you; this project does not include a fabricated demo recording.

## Team issue checklist

In your team's repository, create an issue titled:

`Research Milestone: YOUR_GITHUB_USERNAME`

Include:
- Link to your personal research repository.
- Link to your demo video.
- Mention `@instructors` and your instructor/TAs as directed in the assignment.

Replace `YOUR_GITHUB_USERNAME` and add the real links after you create and publish them.
