package gameobjects;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

import java.util.List;

/**
 *
 */
public abstract class Personnage implements ElementSalle {

    /**
     *
     */
    protected double vie;

    /**
     *
     */
    protected List<Arme> armes;

    /**
     *
     */
    protected Arme currentArme;

    /**
     *
     */
    protected Vector2 position;

    /**
     *
     */
    protected Vector2 size;

    /**
     *
     */
    protected String imagePath;

    /**
     *
     */
    protected double speed;

    /**
     *
     */
    protected Vector2 direction;

    /**
     *
     */
    private boolean invincible;

    /**
     * @param vie
     * @param armes
     * @param position
     * @param size
     * @param speed
     * @param imagePath
     */
    public Personnage(double vie, List<Arme> armes, Vector2 position, Vector2 size, double speed, String imagePath, boolean invincible) {
        this.vie = vie;
        this.armes = armes;
        this.position = position;
        this.size = size;
        this.speed = speed;
        this.imagePath = imagePath;
        this.direction = new Vector2();
        this.invincible = invincible;
        this.currentArme = armes != null ? armes.get(0) : null; //On initiale l'arme courant avec le premier élément de la liste, si pas de liste on met null
    }

    /**
     * @return
     */
    public double getVie() {
        return vie;
    }

    /**
     * @param vie
     */
    public void setVie(double vie) {
        this.vie = vie;
    }

    /**
     * @return
     */
    public List<Arme> getArmes() {
        return armes;
    }

    /**
     * @param armes
     */
    public void setArmes(List<Arme> armes) {
        this.armes = armes;
    }

    /**
     * @return
     */
    public Arme getCurrentArme() {
        return currentArme;
    }

    /**
     * @param currentArme
     */
    public void setCurrentArme(Arme currentArme) {
        this.currentArme = currentArme;
    }

    /**
     * @param arme
     * @param personnage
     */
    public void infligerDegat(Arme arme, Personnage personnage) {
        personnage.setVie(personnage.getVie() - arme.getDegat());
    }

    /**
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * @return
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * @param size
     */
    public void setSize(Vector2 size) {
        this.size = size;
    }

    /**
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return
     */
    public Vector2 getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    /**
     * @return
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * @param invincible
     */
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * @return
     */
    public Vector2 getNormalizedDirection() {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(speed);
        return normalizedVector;
    }

    /**
     *
     */
    public abstract void updateGameObject();

    /**
     *
     */
    public abstract void drawGameObject();

    /**
     * @param indiceArme
     */
    public void rechargeArme(int indiceArme) {

    }

    /**
     *
     */
    public void drawPersonnage() {
        Vector2 position = this.getPosition();
        this.currentArme.drawArme();
        StdDraw.picture(position.getX(), position.getY(), this.getImagePath(), RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
    }

    /**
     * @param room
     */
    public void move(Room room) {
        
    }
}
