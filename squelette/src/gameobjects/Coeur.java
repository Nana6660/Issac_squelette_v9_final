package gameobjects;

import libraries.Vector2;

/**
 *
 */
public class Coeur extends Objet implements Consommable{

    /**
     * @param position
     * @param size
     * @param imagePath
     */
    public Coeur(Vector2 position, Vector2 size, String imagePath) {
        super(position, size, imagePath);
        this.type = TypeCoeur.DEMI;
    }

    /**
     * @param position
     * @param size
     * @param imagePath
     */
    public Coeur(Vector2 position, Vector2 size, String imagePath, int typeCoeur) {
        super(position, size, imagePath);
        this.type = typeCoeur;
    }

}
