package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

/**
 *
 */
public abstract class Objet implements ElementSalle {

    /**
     *
     */
    protected int type;

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
    public Objet(Vector2 position, Vector2 size, String imagePath) {
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
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     *
     */
    public void drawObjet(){
        Vector2 position = this.getPosition();
        StdDraw.picture(position.getX(), position.getY(), this.getImagePath(), RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
    }
}
