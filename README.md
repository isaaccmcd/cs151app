## cs151 Application Build: Recall, Personal Planning Desktop App

### Description

- Project developed for CS151 (Object-Oriented Design) at SJSU.
- Built with JavaFX

### Instalation (using VSCode)

- Ensure up-to-date installation of VSCode
- Ensure Java jdk-16 (or greater) download and environemnt variables configured
- Download JavaFX: https://gluonhq.com/products/javafx/
- Clone this repo
- Add JavaFX jars to "Referenced Libraries" under "JAVA PROJECTS" within the VSCode Explorer
- Go to ".vscode/launch.json" and edit the current launch configuration
    - ensure that the path to the JavaFX library is updated
- Run "Main.java" under src to view/interact with application

### Folder Structure

- `appImages`: all relevant application images/graphics
- `eventsDb`: file system for data store
- `src`: relevant application source code
- `src/events`: relevant application events handling source code

### Dependencies

- Java jdk-16
- javafx-sdk-18.0.1
    - javafx.base
    - javafx.controls
    - javafx.fxml
    - javafx.graphics
    - javafx.media
    - javafx.swing
    - javafx.web
    - javafx-swt
