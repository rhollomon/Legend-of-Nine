---
layout: page
title: "User Stories"
permalink: userstories
---

# User Stories

## Ruby Hollomon

### User Story #1: World and Map

As a game developer, I would like to design and implement an overworld map to give users a meaningful exploration experience.

Elaboration: Implementation of the primary overworld map and "zones" the player character moves around in. Primarily visual, but may also have gameplay elements integrated, such as puzzles. Generates from text file.

Constraints: Most gameplay mechanics will need to be implemented beforehand for optimal gameplay and map integration. Any specific puzzles or other features heavily utilizing the map will also need to be determined in advance and given a planned implementation approach.

Effort Estimation: 5 Human Hours

Acceptance Test: Direction, visuals, and setting are approved by other team members. Any map-specific puzzles and gameplay are tested to ensure integration is smooth and works as planned.

### User Story #2: Graphics and UI

As a designer, I would like to create high-quality pixel spritework graphics and a clean, appealing UI to ensure a professional and memorable look for our game.

Elaboration: Graphical implementation for map tiles, items, and player/enemy entities. Interface for menus, item useage, etc.

Constraints: None.

Effort Estimation: 4 Human Hours

Acceptance Test: Style, quality, and visual approach is approved by all team members.

## User Story #3: Animation

As an artist, I would like to create custom animations for the player character, monsters, and items in the game world to make our game more visually interesting.

Elaboration: Animation loops on sprites allow for a far more vibrant look and feel of gameplay.

Constraints: Animation loops for non-player sprites will need to be programmed. Animations must not cause memory leaks or lag on a typical machine.

Effort Estimation: 4 Human Hours

Acceptance Test: Animation is visually acceptable and does not lead to lag or memory leaks on a typical machine.

## Jared Kaiser

### User Story #1: Enemy Movement

As a devoloper I want to get enemy movement worked out

Elaboration: I would like to figure out how we will get enemies to move and attack, i.e. a.i., set patterns, etc...

Constraints: None

Effort Estimation: 1/2 Human Hours

Acceptance Test: Share ideas with team and make a cohesive desicion on how enimies will work

### User Story #2: Music

As a musically inclined individual, I would like to make the music for our game

Elaboration: I would like to come up with a loopable soundtrack for our game so it is more immersive

Constraints: None

Effort Estimation: 4 Human Hours

Acceptance Test: Play the music I have made for my group and see if it is good enough to be put into the game

### User Story #3: Goal

As a developer I would like to make a functional end for our game

Elaboration: I would like to make an end window that will show a player when they finish the game

Constraints: Need a functionalend to the game

Effort Estimation: 1 Human Hour

Acceptance Test: Will have a functional end game window

## Austin Wilson

### User Story #1: Camera

As a developer, I want to implement our games camera system so important elements in a room or scene are highlighted/emphasized.

Elaboration: Players having a camera that highlights a critical element such as a boss when entering a boss room adds some charm.

Constraints: Dependent on the area the player travels into as the camera may center on other aspects in the context of entering a boss room compared to reaching a puzzle room. May require a function to call when entering a significant room.

Effort Estimation: 1 Human Hour

Acceptance Test: Show examples of how the camera pans to the player, other characters, or significant terrain when entering a new part of the world where this emphasis is needed/desired.

### User Story #2: Saving progress

As a developer, I want to implement our games save system so players can continue their progress through the game from where they left off.

Elaboration: Players have a part of the menu they can select to save their progress OR save their progress automatically based on checkpoints throughout the game.
Constraints: Depending on the direction our team decides towards saving progress, leaving it up to the player when to save would require us to ensure the player cannot lock themselves out from progressing through the game on accident or implementing checkpoints would require us to strategically place these points throughout the game in sensible places.

Effort Estimation: 2 Human Hours

Acceptance Test: Have the character model start in one region of the world. If we chose to implement checkpoints, move the character past a checkpoint, reload the game, and ensure our new starting point is at the checkpoint. If we choose to leave it up to the player, move the character to a different position on the map, save the game from the menu, reload, and again ensure the player starts at the location they chose to save the game before exiting.

### User Story #3: Puzzle Design

As a developer, I want to implement some of our games puzzle rooms so players can have a variety of challenges as they progress through the game.

Elaboration: Players would be required to traverse certain areas containing puzzles which may take the form as platforming, combination puzzles, etc.

Constraints: The constraints involved when implementing these ideas into the game will strongly depend on the puzzle our team chooses to incorporate. Jumping puzzles would need pixel art for platforms or objects to grab onto, animations for these actions, and a camera able to cleanly transition between these actions. Combination puzzles would need thoughtful design where we will need to balance difficulty and the clarity of the task the player when we deliver this to the player.

Effort Estimation: 4 Human Hours

Acceptance Test: Basic sketches of the terrain for a jumping/combination puzzle and to expect from the player to solve such a puzzle to show off to the team and seek approval. Then, implementing these ideas into the game or a test branch and checking if the puzzles can be solved using those expectations during the sketching phase.

## Brock Middleton

As a player, I would like to be able to walk into walls without clipping through them and crashing the game thread. (i.e. Collision Detection)

Elaboration: Users should not be able to walk through walls and other solid objects. This is not conducive to a successful game structure, and it also crashes the active game thread when a player walks out of the defined tile-space. Once collision is established, values must be tweaked to account for game models.

Constraints: None

Est. Hours: 2-3 human hrs.

Acceptance Test: Successful collision on all wall structures in the game environment. No clipping of the sprite model or wall model present.

## Jason Weston

### User Story #1: Object Creation

As a developer, I want to implement the creation of objects in our game where each object has its own unique sprite and is visible to the player.

Elaboration: In creating objects, we must have an object that is an individual entity and the player can see the object in hopes of interacting with it.

Constraints: Only 10 objects are currently allowed, and it will be difficult to figure out how to handle objects individually.

Est. Hours: 1-2 human hrs. 

Acceptance Test: Some objects are finalized, while we have room to include other objects.

### User Story #2: Object Interaction

As a developer, I want to allow players to interact with each object in a unique way.

Elaboration: Each object should have it's own traits, where some will be picked up and some will just be altered. 

Constraints: Again, 10 objects can only be accessed in each instance so we must minimize the amount of objects in a single game instance.

Est. Hours: 2 human hrs.

Acceptance Test: Pieces of cheese can be picked up, doors and chests can be opened.

### User Story #3: Object Placement

As a developer, I want to place objects throughout the world of our game in a meaningful way. 

Elaboration: With our team we need to decide where objects should be placed in the final product. 

Constraints: Depending on how we create rooms and allow for different game states some items will have to be placed further away from others to ensure that only 10 items exist.

Est. Hours: 1 human hr.

Acceptance Test: All objects are meaningfully placed and allow for a linear gameplay. 