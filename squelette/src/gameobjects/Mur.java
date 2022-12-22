package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 *
 */
public class Mur implements ElementSalle, Stop {

    /**
     *
     */
    private Vector2 position;

    /**
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     *
     */
    public Mur() {

    }

    /**
     * @param positions
     */
    public Mur(Vector2 positions) {
        this.position = positions;
    }

    /**
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     *
     */
    public void drawMur() {
        StdDraw.picture(position.getX(), position.getY(), ImagePaths.WALL, 0.08, 0.08, 0);
    }

}

