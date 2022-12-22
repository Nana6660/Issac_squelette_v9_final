package gameobjects;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

/**
 *
 */
public class Porte implements ElementSalle {

    /**
     *
     */
    private boolean close;

    /**
     *
     */
    private Vector2 position;

    /**
     *
     */
    private Room salleSuivante;

    /**
     *
     */
    private Direction direction;

    /**
     *
     */
    public Porte() {
        this.close = true;
        this.position = new Vector2(0, 0);
    }

    /**
     * @param close
     * @param position
     */
    public Porte(boolean close, Vector2 position, Direction direction) {
        this.close = close;
        this.position = position;
        this.direction = direction;
    }

    /**
     * @param close
     * @param position
     */
    public Porte(boolean close, Vector2 position, Room salleSuivante) {
        this.close = close;
        this.position = position;
        this.salleSuivante = salleSuivante;
    }

    /**
     * @return
     */
    public boolean isClose() {
        return close;
    }

    /**
     * @param close
     */
    public void setClose(boolean close) {
        this.close = close;
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
    public Room getSalleSuivante() {
        return salleSuivante;
    }

    /**
     * @param salleSuivante
     */
    public void setSalleSuivante(Room salleSuivante) {
        this.salleSuivante = salleSuivante;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     *
     */
    public void drawPorte() {
        Vector2 position = this.getPosition();
        String porteImagePath = this.isClose() ? ImagePaths.CLOSED_DOOR : ImagePaths.OPENED_DOOR;
        StdDraw.picture(position.getX(), position.getY(), porteImagePath, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
    }
}
