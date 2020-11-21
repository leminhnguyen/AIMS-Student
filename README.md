#  An Internet Media Store
<p align="center">
  <img src="assets/images/aims_cover_image.png" />
</p>

## Getting Started

Welcome to the AIMS project. Here is a guideline to help you get started.

## Folder Structure

The workspace contains the following folders, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `assets`: the folder to maintain static resources
- `test`: the folder for testing purpose

## Dependency Management
### Working with Eclipse
Import the root directory of this repository after cloning under `Eclipse` -> `Open Projects from File System...` or by using EGit.

### SQLite
Import `sqlite-jdbc-3.7.2.jar` in `lib` under `Eclipse` -> `Project` -> `Properties` -> `Java Build Path` -> `Classpath` -> `Add JARs...`.


### JUnit
Import `JUnit5` library under `Eclipse` -> `Project` -> `Properties` -> `Java Build Path` -> `Modulepath` -> `Add Library...` -> `JUnit` -> `Next`.

### JavaFX
**Note:** At first, please try to run the project once, and then follow these steps.
1. Create a new `User Library` under `Eclipse` -> `Window` -> `Preferences` -> `Java` -> `Build Path` -> `User Libraries` -> `New`
2. Name it anything you want, e.g., `JavaFX15`, and include the ***jars*** under either the `lib/linux/javafx-sdk-15` directory for Linux distro or the `lib/win/javafx-sdk-15` directory for Windows in the project.
3. Include the library, e.g., `JavaFX15`, into the classpath.

### Add VM arguments
Click on `Run` -> `Run Configurations...`  -> `Java Application`, create a new launch configuration for your project and add these VM arguments:
- For Linux distro: 
> `--module-path lib/linux/javafx-sdk-15 --add-modules javafx.controls,javafx.fxml`
- For Windows:
> `--module-path lib/win/javafx-sdk-15 --add-modules javafx.controls,javafx.fxml`

## Author
- nguyenlm - Software Engeneering Student - k61
- manhvd   - Software Engeneering Student - k61
- hieudm   - ICT - k61