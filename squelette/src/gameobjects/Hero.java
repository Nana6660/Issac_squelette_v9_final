package gameobjects;

import gameWorld.GameWorld;
import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

import java.util.List;

/**
 *
 */
public class Hero extends Personnage {

    /**
     *
     */
    private int nombrePiece;

    /**
     *
     */
    private GameWorld gameWorld;

    private double pointVieMax;

    /**
     * @param vie
     * @param armes
     * @param position
     * @param size
     * @param speed
     * @param imagePath
     */
    public Hero(double vie, List<Arme> armes, Vector2 position, Vector2 size, double speed, String imagePath, boolean invincible) {
        super(vie, armes, position, size, speed, imagePath, invincible);
        this.nombrePiece = 0;
        this.pointVieMax = HeroInfos.ISAAC_VIE;
    }

    /**
     * @return
     */
    public int getNombrePiece() {
        return nombrePiece;
    }

    /**
     * @param nombrePiece
     */
    public void setNombrePiece(int nombrePiece) {
        this.nombrePiece = nombrePiece;
    }

    /**
     * @return
     */
    public double getPointVieMax() {
        return pointVieMax;
    }

    /**
     * @param pointVieMax
     */
    public void setPointVieMax(double pointVieMax) {
        this.pointVieMax = pointVieMax;
    }

    /**
     * @return
     */
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    /**
     * @param gameWorld
     */
    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     *
     */
    public void updateGameObject() {
        move();
        this.currentArme.move(this.gameWorld.getCurrentRoom());// Pour déplacer l'arme
        this.gameWorld.getCurrentRoom().deplacerMonstreAleatoirement();// Pour le déplacement des monstres de façon aléatoire
        this.gameWorld.getCurrentRoom().tirerMonstreAleatoirement();
    }

    /**
     *
     */
    private void move() {
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);

        int[] index = Room.getIndexX_Y(positionAfterMoving.getX(), positionAfterMoving.getY());
        int indexX = index[0];
        int indexY = index[1];

        /**
         * On se déplace uniquement si :
         * - On est pas sur un element qui nous stop
         * - Si la porte est ouverte
         */
        if (!(this.getGameWorld().getCurrentRoom().getCarreaux()[(indexX)][indexY] instanceof Stop)
                && !(this.getGameWorld().getCurrentRoom().getCarreaux()[(indexX)][indexY] instanceof Porte && ((Porte) this.getGameWorld().getCurrentRoom().getCarreaux()[(indexX)][indexY]).isClose())) {
            setPosition(positionAfterMoving);
            this.currentArme.setPosition(positionAfterMoving);
        }
        direction = new Vector2();
    }

    /**
     *
     */
    public void drawGameObject() {
        StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
                0);
    }

    /**
     * Dans la salle 'room', Isaac tire dans la direction 'direction'
     *
     * @param direction
     */
    public void tire(Direction direction) {
        switch (direction) {
            case BAS:
                this.currentArme.getDirection().addY(-1); // Pour le déplacement de l'arme
                break;
            case HAUT:
                this.currentArme.getDirection().addY(1); // Pour le déplacement de l'arme
                break;
            case DROITE:
                this.currentArme.getDirection().addX(1); // Pour le déplacement de l'arme
                break;
            case GAUCHE:
                this.currentArme.getDirection().addX(-1); // Pour le déplacement de l'arme
        }
    }

    /**
     * Le hero ramasse un objet dans une salle, à une position donnée
     *
     * @param room
     * @param indexX
     * @param indexY
     */
    public void ramasserObjet(Room room, int indexX, int indexY) {
        if (room.getCarreaux()[indexX][indexY] != null && room.getCarreaux()[indexX][indexY] instanceof Objet) {

            //On ajoute des pièces si notre objets est une pièces et qu'isaac n'a pas dépassé le solde maximal
            if (room.getCarreaux()[indexX][indexY] instanceof Piece && this.nombrePiece < HeroInfos.ISAAC_PIECE_MAX) {
                this.nombrePiece = this.nombrePiece + ((Piece) room.getCarreaux()[indexX][indexY]).getType();
            } else if (room.getCarreaux()[indexX][indexY] instanceof Coeur && this.vie < HeroInfos.ISAAC_VIE) {
                this.vie = this.vie + ((Coeur) room.getCarreaux()[indexX][indexY]).getType();
            }

            room.getObjets().remove(room.getCarreaux()[indexX][indexY]);//On Supprime l'objet de la salle
            room.getCarreaux()[indexX][indexY] = null; //On supprimer la reference crée lors de l'affichage de l'élement dans la salle
        }
    }

    /**
     * Le hero ramasse un équipement dans une salle, à une position donnée
     *
     * @param room
     * @param indexX
     * @param indexY
     */
    public void ramasserEquipement(Room room, int indexX, int indexY) {
        if (room.getCarreaux()[indexX][indexY] != null && room.getCarreaux()[indexX][indexY] instanceof Equipement) {

            if (room.getCarreaux()[indexX][indexY] instanceof Inferieur3) {
                this.pointVieMax += 2;
                this.vie = pointVieMax;
            } else if (room.getCarreaux()[indexX][indexY] instanceof BloodOfTheMartyr) {
                //– Le Blood Of The Martyr permet d’augmenter les dégâts qu’inflige Isaac de 1.
                // On affect ainsi ce dégât à l'arme courente d'isaac
                this.currentArme.setDegat(this.currentArme.getDegat() + 1);
            }

            room.getEquipements().remove(room.getCarreaux()[indexX][indexY]);//On Supprime l'équipement de la salle
            room.getCarreaux()[indexX][indexY] = null; //On supprimer la reference crée lors de l'affichage de l'élement dans la salle
        }
    }

    /**
     * Le hero ramasse un objet dans une salle, à une position donnée
     *
     * @param room
     * @param indexX
     * @param indexY
     */
    public void rencontreMonstre(Room room, int indexX, int indexY) {
        if (room.getCarreaux()[indexX][indexY] != null && room.getCarreaux()[indexX][indexY] instanceof Monstre && !this.isInvincible()) {

            this.imagePath = ImagePaths.GAPER; //Change l'image du personnage, pour montre qu'il perd des vies

            //On ajoute des pièces si notre objets est une pièces et qu'isaac n'a pas dépassé le solde maximal
            if (room.getCarreaux()[indexX][indexY] instanceof Fly) {
                this.vie = (int) (this.vie - ((Fly) room.getCarreaux()[indexX][indexY]).currentArme.getDegat());
            } else if (room.getCarreaux()[indexX][indexY] instanceof Spider) {
                this.vie = (int) (this.vie - ((Spider) room.getCarreaux()[indexX][indexY]).currentArme.getDegat());
            }else if (room.getCarreaux()[indexX][indexY] instanceof Boss) {
                this.vie = (int) (this.vie - ((Boss) room.getCarreaux()[indexX][indexY]).currentArme.getDegat());
            }
        }
    }

    /**
     * On change de salle si la porte n'est pas fermée
     *
     * @param room
     * @param indexX
     * @param indexY
     */
    public void goNextRoom(Room room, int indexX, int indexY) {
        if (room.getCarreaux()[indexX][indexY] != null &&
                room.getCarreaux()[indexX][indexY] instanceof Porte &&
                this.gameWorld.getCurrentRoom() != null &&
                ((Porte) room.getCarreaux()[indexX][indexY]).getSalleSuivante() != null &&
                !((Porte) room.getCarreaux()[indexX][indexY]).isClose()) {

            this.gameWorld.setCurrentRoom(((Porte) room.getCarreaux()[indexX][indexY]).getSalleSuivante()); //Changement de salle
            this.setPositionWithDirection((Porte) room.getCarreaux()[indexX][indexY], ((Porte) room.getCarreaux()[indexX][indexY]).getDirection()); // Pour positionner le joueur devant la porte par laquelle il est rentré dans la salle
        }
    }

    /**
     * @param direction Pour positionner le joueur devant la porte par laquelle il est rentré dans la salle
     */
    private void setPositionWithDirection(Porte porte, Direction direction) {
        if (direction == Direction.DROITE) {
            this.position.setX(0.2);
        } else if (direction == Direction.GAUCHE) {
            this.position.setX(0.8);
        } else if (direction == Direction.HAUT) {
            this.position.setY(0.2);
        } else if (direction == Direction.BAS) {
            this.position.setY(0.8);
        }
    }

    /**
     *
     */
    public void goUpNext() {
        getDirection().addY(1);
    }

    /**
     *
     */
    public void goDownNext() {
        getDirection().addY(-1);
    }

    /**
     *
     */
    public void goLeftNext() {
        getDirection().addX(-1);
    }

    /**
     *
     */
    public void goRightNext() {
        getDirection().addX(1);
    }

}
