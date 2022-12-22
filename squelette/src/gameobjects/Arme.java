package gameobjects;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

/**
 *
 */
public abstract class Arme {

    /**
     *
     */
    protected double degat;

    /**
     *
     */
    protected double portee;

    /**
     *
     */
    protected double vitesse;


    /**
     *
     */
    protected Vector2 position;

    /**
     *
     */
    protected Vector2 direction;

    /**
     *
     */
    protected Vector2 size;

    /**
     *
     */
    protected String imagePath;


    /**
     * @param degat
     * @param portee
     * @param vitesse
     */
    public Arme(double degat, double portee, double vitesse) {
        this.degat = degat;
        this.portee = portee;
        this.vitesse = vitesse;
    }

    /**
     * @param degat
     * @param portee
     * @param vitesse
     */
    public Arme(double degat, double portee, double vitesse, Vector2 position, Vector2 size, String imagePath) {
        this.degat = degat;
        this.portee = portee;
        this.vitesse = vitesse;
        this.position = position;
        this.direction = new Vector2();
        this.size = size;
        this.imagePath = imagePath;
    }

    /**
     * @return
     */
    public double getDegat() {
        return degat;
    }

    /**
     * @param degat
     */
    public void setDegat(double degat) {
        this.degat = degat;
    }

    /**
     * @return
     */
    public double getPortee() {
        return portee;
    }

    /**
     * @param portee
     */
    public void setPortee(double portee) {
        this.portee = portee;
    }

    /**
     * @return
     */
    public double getVitesse() {
        return vitesse;
    }

    /**
     * @param vitesse
     */
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
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
     *
     */
    public void drawArme() {
        Vector2 position = this.getPosition();
        StdDraw.picture(position.getX(), position.getY(), this.getImagePath(), RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
    }

    /**
     * @return
     */
    public Vector2 getNormalizedDirection() {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(portee);
        return normalizedVector;
    }

    /**
     * Déplacement de l'arme dans la salle
     */
    public void move(Room room) {
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);

        int[] index = Room.getIndexX_Y(positionAfterMoving.getX(), positionAfterMoving.getY());
        int indexX = index[0];
        int indexY = index[1];

        /**
         * Lorsqu'on tombe sur un monstre on le détruit
         */
        if (indexX >= 0 && indexX <= 8 && indexY >= 0 && indexY <= 8) {

            if (room.getCarreaux()[indexX][indexY] instanceof Monstre
                    && room.getCarreaux()[indexX][indexY] instanceof Personnage) {

                ((Personnage) room.getCarreaux()[indexX][indexY]).vie -= this.degat;

                //S'il n'a plus de vie on le supprime (Simulation de sa mort)
                if (((Personnage) room.getCarreaux()[indexX][indexY]).vie <= 0) {
                    room.getMonstres().remove(room.getCarreaux()[indexX][indexY]);
                    room.getCarreaux()[indexX][indexY] = null;
                }
            }
        }
        setPosition(positionAfterMoving);
        direction = new Vector2();
    }
}
