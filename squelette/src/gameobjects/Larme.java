package gameobjects;

import libraries.Vector2;

public class Larme extends Arme{

    /**
     * @param degat
     * @param portee
     * @param vitesse
     */
    public Larme(double degat, double portee, double vitesse) {
        super(degat, portee, vitesse);
    }


    /**
     * @param degat
     * @param portee
     * @param vitesse
     */
    public Larme(double degat, double portee, double vitesse, Vector2 position, Vector2 size, String imagePath) {
        super(degat, portee, vitesse,position,size,imagePath);
    }
}
