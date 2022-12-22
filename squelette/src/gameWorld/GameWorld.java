package gameWorld;

import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.RoomInfos;

import java.util.HashMap;
import java.util.List;

public class GameWorld {
    private Room currentRoom;
    private Hero hero;

    // A world needs a hero
    public GameWorld(Hero hero) {
        this.hero = hero;
        currentRoom = new Room(hero);
    }

    /**
     * @param hero
     * @param portes
     * @param obstacles
     * @param objets
     * @param murs
     */
    public GameWorld(Hero hero, HashMap<Direction, Porte> portes, List<Obstacle> obstacles, List<Personnage> monstres, List<Objet> objets, List<Mur> murs) {
        this.hero = hero;
        currentRoom = new Room(hero, portes, obstacles, monstres, objets, murs);
    }

    /**
     * @param room
     */
    public GameWorld(Room room) {
        this.hero = room.getHero();
        currentRoom = room;
    }


    public void processUserInput() {
        if (this.currentRoom != null) {
            processKeysForMovement();
        }
    }

    /**
     * @return
     */
    public boolean gameOver() {
        return hero.getVie() <= 0;
    }

    /**
     *
     */
    public void updateGameObjects() {
        if (this.currentRoom != null) {
            currentRoom.updateRoom();
        }
    }

    /**
     *
     */
    public void drawGameObjects() {
        if (this.currentRoom != null) {
            currentRoom.drawRoom();
        }
    }

    /*
     * Keys processing
     */

    private void processKeysForMovement() {

        //Position du personnage
        Vector2 position = hero.getPosition();
        double x = position.getX();
        double y = position.getY();

        int[] index = Room.getIndexX_Y(x, y);
        int indexX = index[0];
        int indexY = index[1];


        //Deplacement
        if (StdDraw.isKeyPressed(Controls.goUp)) {
            hero.goUpNext();
        }
        if (StdDraw.isKeyPressed(Controls.goDown)) {
            hero.goDownNext();
        }
        if (StdDraw.isKeyPressed(Controls.goRight)) {
            hero.goRightNext();
        }
        if (StdDraw.isKeyPressed(Controls.goLeft)) {
            hero.goLeftNext();
        }


        //Tir
        if (StdDraw.isKeyPressed(Controls.tirUp)) {
            hero.tire(Direction.HAUT);
        }
        if (StdDraw.isKeyPressed(Controls.tirDown)) {
            hero.tire(Direction.BAS);
        }
        if (StdDraw.isKeyPressed(Controls.tirRight)) {
            hero.tire(Direction.DROITE);
        }
        if (StdDraw.isKeyPressed(Controls.tirLeft)) {
            hero.tire(Direction.GAUCHE);
        }


        // Triche
        if (StdDraw.isKeyPressed(Controls.invincible)) {
            hero.setInvincible(true);
        }
        if (StdDraw.isKeyPressed(Controls.rapide) && hero.getSpeed() < 0.02) { //On augmente la vitesse si elle est inférieure à 0.02
            hero.setSpeed(hero.getSpeed() + 0.001);
        }
        if (StdDraw.isKeyPressed(Controls.lent) && hero.getSpeed() > 0.01) { //On diminue la vitesse si elle est supérieure à 0.01
            hero.setSpeed(hero.getSpeed() - 0.001);
        }
        if (StdDraw.isKeyPressed(Controls.tuerTousLesMonstres) && this.currentRoom.getMonstres() != null) {
            this.currentRoom.getMonstres().clear();
        }
        if (StdDraw.isKeyPressed(Controls.puissanceTotal)) {

        }
        if (StdDraw.isKeyPressed(Controls.donnePiece)) { //On donne 10 pièces à Isaac
            hero.setNombrePiece(hero.getNombrePiece() + 10);
        }

        /**
         * Action dans la salle
         */
        hero.ramasserObjet(this.currentRoom, indexX, indexY);
        hero.ramasserEquipement(this.currentRoom, indexX, indexY);
        hero.rencontreMonstre(this.currentRoom, indexX, indexY);
        hero.goNextRoom(this.currentRoom, indexX, indexY);

    }

    /**
     * @return
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
