package edu.nmsu.cs.legendofnine;

import entity.Entity;

/**
 *  Checks movement of player entitys for collision with world tiles
 */
public class CollisionChecker {
    
    GamePanel gp;

    /**
     * Contructor for CollisionChecker
     * @param gp active GamePanel
     */
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * checkTile - checks if current tile is collidable 
     * @param entity player entity committing a movement 
     */
    public void checkTile(Entity entity) {

        /**
         * Derive solid area of player entity
         * as Col and Row #'s
         */
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.x;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
        case "up":
            // Predict which tile player is moving to by subtracting speed
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;

        } // switch
    } // checkTile

    /**
     * Check whether player is colliding with object via 'intersects'
     * 
     * @param entity entity>player
     * @param player true for player, false for non-player
     * @return index of object 
     */
    public int checkObject(Entity entity, boolean player) {

        // Check if player is colliding with object, if so, return index of object

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            
            if (gp.obj[i] != null) {

                // Get entity solid aera pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get object solid area pos
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // Check direction of moving entity
                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            // If collision of object is true, set entity collision true
                            if (gp.obj[i].collision == true) {  
                                entity.collisionOn = true;
                            }
                            // If entity is a player, they can pickup objects
                            if (player == true) { 
                                index = i;
                            }
                        }
                    break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            // If collision of object is true, set entity collision true
                            if (gp.obj[i].collision == true) {  
                                entity.collisionOn = true;
                            }
                            // If entity is a player, they can pickup objects
                            if (player == true) { 
                                index = i;
                            }
                        }    
                    break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            // If collision of object is true, set entity collision true
                            if (gp.obj[i].collision == true) {  
                                entity.collisionOn = true;
                            }
                            // If entity is a player, they can pickup objects
                            if (player == true) { 
                                index = i;
                            }
                        }
                    break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            // If collision of object is true, set entity collision true
                            if (gp.obj[i].collision == true) {  
                                entity.collisionOn = true;
                            }
                            // If entity is a player, they can pickup objects
                            if (player == true) { 
                                index = i;
                            }
                        }  
                    break;
                } // switch

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            } // if

        } // for

        return index;
    }// checkObject


} // CollisionChecker
