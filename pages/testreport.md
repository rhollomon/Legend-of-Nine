---
layout: page
title: "Test Report"
permalink: testreport
---

# Test Report
Development of Legend of Nine involved a lot of individual pieces put together towards realizing our intended game design. Many of these parts required tests on their own to ensure they would not negatively impact the experience of the game or further development of the project. Outlined below include details
of the various components tested, system tests to verify the pieces interacted as intended, and acceptance tests to validate if the included features improved the players experience of the game. 

## 1. Component Testing

    Collision Detection: 
    Collision Detection places a restraint on the player's ability to move around the world such as preventing the player from
    walking through walls, animate objects, and NPCS. Testing involved creating a series of entities we wanted the player to 
    walk through such as items we can pick up and entities we wanted to prevent the player from walking through and validating
    their intended functionality. 

    Camera: 
    Our game's camera wanted the player to always be the focus of attention and thus we decided to have the player remain always 
    centered on the screen as they traversed the world. Testing the camera occurred after collision detection was completed, 
    so we could utilize walls as a way to see if a boundary affected the camera's ability to remain in sync with the player's 
    movement. Creating an open area with space for the player to move around helped validate the functionality of the camera's
    center tracker of the player.

    Object Placement/Interaction:
    Items or interactable entities appear throughout the world. Some we want the player to simply walk through to trigger an event
    such as boost to stats or adding the item to the inventory while others we want to enforce collision detection on such as a 
    chest. Testing object placement and interaction required creating pickups in the world for the player to walk through then 
    disappear and trigger an effect. Chests were placed to verify a player could not walk through a chest but could interact 
    with them by pressing "Enter" to see if an event triggered such as a "Congratulations" text.

    Inventory:
    The intended design for the inventory included displaying the players currency, currently equipped weapon/shield, consumable 
    items, and a limitation of 20 items. Testing the inventory required placing pickups in the world such as potions,
    cheese(currency), and equipment, having the player walk over the item, checking if the image for item displayed in the 
    inventory menu or if a counter was increased, then validating the item could be consumed or equipped. 

    Stats/Leveling Up:
    Throughout the player's progression within the world, they will encounter enemies which will give experience points after 
    being defeated. Experience points should increase the player's stats after a sufficient amount is gained, improving the 
    player's stats including defense and health. Testing this component required creating enemies for the player to defeat,
    ensuring UI indicators showed the player's EXP increasing, stat increases, and defeating more enemies to see if the
    stats were reflected in gameplay.

## 2. System Testing 

    Combining the multiple components into the game after completing component testing required repeated repetition of 
    various interactions. 
    
    For example:
    
    The tester would see how the game would react to picking up an item such as an HP potion, opening the inventory to see if the
    icon for the image was displayed, consuming the item, then defeating enemies to see if this affected other components such as
    leveling up, or the stats UI. Exploring the area ensuring the player stayed centered on screen, object detection for walls,
    chests, and NPCS behaved as expected.

    Overall, system testing required playing through as many permutations of the game as possible, and ensuring no components
    lost functionality as a result of a prior interaction by the player.

## 3. Acceptance Testing
    Outlined below are the status of acceptance for the various components/user stories.

    Collision Detection: Accepted
    Objects with collision detection implemented such as walls, chests, and NPCS properly prevent the player from walking
    through them.

    Camera: Accepted
    Camera remains centered on the player at every point during gameplay as intended.

    Object Placement/Interaction: Accepted
    Items placed throughout the world can be walked through by the player, disappear from the world, and are added
    to the inventory.

    Inventory: Accepted
    Inventory menu properly displays the player's currently equipped weapon/shield, all consumable items they've acquired
    during play, and the player can switch equipment, consume items, or lose currency after a purchase.

    Stats/Leveling Up: Accepted
    Stats menu displays stats , experience gained through combat increases the stat line, and displayed stats accurately
    depicted in gameplay(less/more attack/defense).
