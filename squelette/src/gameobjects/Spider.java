package gameobjects;

import gameWorld.Room;
import libraries.Vector2;

import java.util.List;

/**
 *
 */
public class Spider extends Personnage implements Monstre {

    /**
     * @param vie
     * @param armes
     * @param position
     * @param size
     * @param speed
     * @param imagePath
     */
    public Spider(double vie, List<Arme> armes, Vector2 position, Vector2 size, double speed, String imagePath, boolean invincible) {
        super(vie, armes, position, size, speed, imagePath, invincible);
    }

    /**
     *
     */
    @Override
    public void updateGameObject() {

    }

    /**
     *
     */
    @Override
    public void drawGameObject() {

    }

    /**
     * @param room
     */
    public void move(Room room) {
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);

        int[] index = Room.getIndexX_Y(positionAfterMoving.getX(), positionAfterMoving.getY());
        int indexX = index[0];
        int indexY = index[1];

        if (indexX >= 0 && indexX <= 8 && indexY >= 0 && indexY <= 8) {
            if (!(room.getCarreaux()[(indexX)][indexY] instanceof Stop)) {
                setPosition(positionAfterMoving);
                this.currentArme.setPosition(positionAfterMoving);
            }
        }
        direction = new Vector2();
    }
}
