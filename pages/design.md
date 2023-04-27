---
layout: page
title: "Design"
permalink: design
---

# Design Page for Legend of Nine

## Architecture Diagram 
![UML Diagram v0.2](/LegendOfNine/res//arch/uml_v0.2_apr2723.pdf)

## Description

The architecure diagram for our Java application depicts the various classes and how they interact with each other.

Throughout development, we have had a focus on modularity in order to streamline the implementation of various functionaltiies without impeding the work of other team members. As such, we utlized constructor injection in many of our class constructors in order to cleanly map our dependencies accross the project. 

The primary class of the project is 'GamePanel'. Per the architecture diagram, we can see that msot of our specific-use classes are dependent on the GamePanel class. Here is a detailed description of the role of each class, along with some their significant behaviors. For a full list of attributes and behaviors, see the architecture diagram. ![UML Diagram v0.2](/LegendOfNine/res//arch/uml_v0.2_apr2723.pdf)

    - Class Name : *GamePanel*

         - Purpose : GamePanel is responsible for initializing the game window, thread, and state. 

         - Functionalities : 
            - public GamePanel(): Constructor for game window settings.
            - public void setupGame(): Uses the AssetSetter() class to instantiate in-game objects, NPCs, and music.
            - public void startGameThread(): Instantiates and starts a new game thread.
    
    - Class Name : *Entity*
        
        - Purpose : Entity is ronsponsible for manaing the lone 'Player' entity, as well as NPC's. 

        - Functionalities :
            - public Entity(gp): Constructor for Entity
            - public void draw(Graphics2D): Draw the corresponding image for an entity on-screen.
            - public void update(): Update the location of an on-screen entity given an action.

    - Class Name : *Player* 

        - Purpose : Implement a movable character to function as the player.

        - Functionalities :
            - public Player(GamePanel, KeyHandler): Constructor for Player.
            - public void pickUpObjects(): Pick up item upon collision
            - public void update(): Update image and location of player based on key input.
    
    - Class Name : *KeyHandler*

        - Purpose: Handle keyboard input from the player.

    - Class Name : *EventHandler*

        - Purpose : Handle in-game events, such as picking up objects or attacking enemies. 

    - Class Name : *AssetSetter*

        - Purpose: Draw the assets (Entities, Objects, etc.) for each instantiated game thread. 

    - Class Name *TileManager*

        - Purpsoe : Draw the tiles for each given game map and mantain collision values for tiles.

    - Class Name : *CollisionChecker*

        - Purpose : Check for collision with each entity movement. 

    - Class Name : *UI*

        - Purpose : Draw and update the in-game user interface.
    
    - Class Name : *Sound*

        - Purpose : Manage sound, including music and event reactions. 




        