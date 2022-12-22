package gameobjects;

import libraries.Vector2;

/**
 *
 */
public class Piece extends Objet implements Consommable {

    /**
     * @param position
     * @param size
     * @param imagePath
     */
    public Piece(Vector2 position, Vector2 size, String imagePath) {
        super(position, size, imagePath);
        this.type = TypePiece.NICKEL;
    }

    /**
     * @param position
     * @param size
     * @param imagePath
     */
    public Piece(Vector2 position, Vector2 size, String imagePath, int typePiece) {
        super(position, size, imagePath);
        this.type = typePiece;
    }

}
