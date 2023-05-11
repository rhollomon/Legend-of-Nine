# Legend of Nine

A game about a mouse. Team project page for CS-371.

[Link to project repo.](https://github.com/rhollomon/Legend-of-Nine)

[Link to project site.](https://rhollomon.github.io/Legend-of-Nine/)

# How to Run

This project contains support to be compiled and run using the Gradle tool. To do this, you will need to be sure you have Gradle installed on your machine. You can do this by [downloading the zip file from this link](https://gradle.org/install/) and following the instructions on the page.

After you have Gradle installed, open your computer's terminal or command prompt. Navigate to where you have the project folder saved, then navigate inside the /Legend-of-Nine/LegendOfNine directories. You should then be able to compile the project using the `gradle` command.

After compilation is finished, the game window can be launched using the command `gradle run`.

_Example of building and running Legend of Nine with Gradle:_
![image](https://user-images.githubusercontent.com/123597389/233237618-375a9e8c-c8d5-4bc8-ac98-d675d8e07d15.png)

Advice for common issues with Gradle:

- Ensure tools.jar is visible in your Java installation folder. This may require installing JDK8 and moving the tools.jar file from the lib directory into your JRE's lib directory.
- Ensure Gradle is visible on your build path, and its file location is set in your computer's environmental variables.

# Repository Navigation 
- .gradle
    Build file

- GameMap
    Picture of desired game map.

- LegendOfNine
    - Build: Includes class files for all of the java files contained within each package.

    - src: encludes Java source files for the following packages:
        1. edu/nmsu.cs/edu: Main driver classes for implementing different instructions defined in other packages.
        2. entity/entity: Instantiates entities.
        3. environment: Handles environmental lighting.
        4. object: Instantiates objects.
        5. res: Holds object sprites and other graphics.
        6. Tile: Manages tiles and initializes the game map.

- gradle/wrapper 
    Initializes wrapper for gradle

- pages
    - Includes the following documentation for the project:
        1. architecure.md
        2. design.md
        3. problem.md
        4. requirements.md
        5. testreport.md 
        6. userstories.md 



    

