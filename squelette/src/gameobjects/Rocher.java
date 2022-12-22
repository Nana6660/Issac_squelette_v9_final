package gameobjects;

import libraries.Vector2;

/**
 *
 */
public class Rocher extends Obstacle implements Stop {

    /**
     * @param position
     * @param size
     * @param imagePath
     */
    public Rocher(Vector2 position, Vector2 size, String imagePath) {
        super(position, size, imagePath);
    }
}
