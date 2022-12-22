package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

/**
 *
 */
public abstract class Equipement implements ElementSalle {

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
     * @param position
     * @param size
     * @param imagePath
     */
    public Equipement(Vector2 position, Vector2 size, String imagePath) {
        this.position = position;
        this.size = size;
        this.imagePath = imagePath;
    }

    /**
     *
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     *
     * @return
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(Vector2 size) {
        this.size = size;
    }

    /**
     *
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     *
     * @param imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     *
     */
    public void drawObjet(){
        Vector2 position = this.getPosition();
        StdDraw.picture(position.getX(), position.getY(), this.getImagePath(), RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
    }
}
