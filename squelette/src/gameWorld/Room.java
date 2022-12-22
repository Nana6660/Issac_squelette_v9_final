package gameWorld;

import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class Room {

    /**
     *
     */
    private Hero hero;

    /**
     *
     */
    private HashMap<Direction, Porte> portes;

    /**
     *
     */
    private List<Obstacle> obstacles;

    /**
     *
     */
    private List<Personnage> monstres;

    /**
     *
     */
    private List<Objet> objets;

    /**
     *
     */
    private List<Equipement> equipements;

    /**
     *
     */
    private List<Mur> murs;

    /**
     *
     */
    private ElementSalle[][] carreaux;

    /**
     * @param hero
     */
    public Room(Hero hero) {
        this.hero = hero;
        this.carreaux = new ElementSalle[RoomInfos.NB_TILES][RoomInfos.NB_TILES];
    }

    /**
     * @param hero
     * @param portes
     * @param obstacles
     * @param monstres
     * @param objets
     * @param murs
     */
    public Room(Hero hero, HashMap<Direction, Porte> portes, List<Obstacle> obstacles, List<Personnage> monstres, List<Objet> objets, List<Mur> murs) {
        this.hero = hero;
        this.portes = portes;
        this.obstacles = obstacles;
        this.monstres = monstres;
        this.objets = objets;
        this.murs = murs;
        this.carreaux = new ElementSalle[RoomInfos.NB_TILES][RoomInfos.NB_TILES];
    }

    /**
     * @param hero
     * @param portes
     * @param obstacles
     * @param monstres
     * @param objets
     * @param murs
     */
    public Room(Hero hero, HashMap<Direction, Porte> portes, List<Obstacle> obstacles, List<Personnage> monstres, List<Objet> objets, List<Mur> murs, List<Equipement> equipements) {
        this.hero = hero;
        this.portes = portes;
        this.obstacles = obstacles;
        this.monstres = monstres;
        this.objets = objets;
        this.murs = murs;
        this.equipements = equipements;
        this.carreaux = new ElementSalle[RoomInfos.NB_TILES][RoomInfos.NB_TILES];
    }

    /*
     * Make every entity that compose a room process one step
     */
    public void updateRoom() {
        makeHeroPlay();
    }


    private void makeHeroPlay() {
        hero.updateGameObject();
    }

    /*
     * Drawing
     */
    public void drawRoom() {
        // For every tile, set background color.
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < RoomInfos.NB_TILES; i++) {
            for (int j = 0; j < RoomInfos.NB_TILES; j++) {
                Vector2 position = positionFromTileIndex(i, j);
                StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY());

                this.carreaux[i][j] = null; //Remplissage des zones de la salle pour connaître les éléments qui s'y trouvent
            }
        }

        /*
         * Affichage des obstables
         */
        if (this.obstacles != null) {
            for (Obstacle obstacle : this.obstacles) {
                Vector2 position = obstacle.getPosition();
                int[] index = this.getIndexX_Y(position.getX(), position.getY());
                this.carreaux[index[0]][index[1]] = obstacle;
                obstacle.drawObstacle();
            }
        }

        /*
         * Affichage des monstres
         */
        if (this.monstres != null) {
            for (Personnage monstre : this.monstres) {
                Vector2 position = monstre.getPosition();
                int[] index = this.getIndexX_Y(position.getX(), position.getY());
                this.carreaux[index[0]][index[1]] = monstre;
                monstre.drawPersonnage();
            }
        }

        /*
         * Affichage des objets
         */
        if (this.objets != null) {
            for (Objet objet : this.objets) {
                Vector2 position = objet.getPosition();
                int[] index = this.getIndexX_Y(position.getX(), position.getY());
                this.carreaux[index[0]][index[1]] = objet;
                objet.drawObjet();
            }
        }

        /*
         * Affichage des équipements
         */
        if (this.equipements != null) {
            for (Equipement equipement : this.equipements) {
                Vector2 position = equipement.getPosition();
                int[] index = this.getIndexX_Y(position.getX(), position.getY());
                this.carreaux[index[0]][index[1]] = equipement;
                equipement.drawObjet();
            }
        }

        /*
         * Affichage des murs
         */
        if (this.murs != null) {
            for (Mur mur : this.murs) {
                Vector2 position = mur.getPosition();
                int[] index = this.getIndexX_Y(position.getX(), position.getY());
                this.carreaux[index[0]][index[1]] = mur;
                mur.drawMur();
            }
        }


        /**
         * Affichage de la vie
         */
        Vector2 position1 = positionFromTileIndex(1, 8);

        int nombreCoeurPlein = ((int) this.hero.getVie()) / 2;
        int nombreDemiCoeur = ((int) this.hero.getVie()) % 2;
        int nombreCoeurVide = ((int) this.hero.getPointVieMax()) / 2 - (nombreCoeurPlein + nombreDemiCoeur);

        for (int i = 0; i < nombreCoeurPlein; i++) {
            StdDraw.picture(position1.getX() + i * RoomInfos.DIFF_POSITION_NB_HEART, position1.getY(), ImagePaths.HEART_HUD, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
        }

        for (int i = 0; i < nombreDemiCoeur; i++) {
            StdDraw.picture(position1.getX() + (nombreCoeurPlein + i) * RoomInfos.DIFF_POSITION_NB_HEART, position1.getY(), ImagePaths.HALF_HEART_HUD, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
        }

        for (int i = 0; i < nombreCoeurVide; i++) {
            StdDraw.picture(position1.getX() + (nombreCoeurPlein + nombreDemiCoeur + i) * RoomInfos.DIFF_POSITION_NB_HEART, position1.getY(), ImagePaths.EMPTY_HEART_HUD, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
        }


        /**
         * Affichage du nombre d'objet
         */
        Vector2 positionObjet1 = positionFromTileIndex(0, 7);
        Vector2 positionObjet2 = positionFromTileIndex(0, 6);
        Vector2 positionObjet3 = positionFromTileIndex(0, 5);

        Vector2 positionObjetT1 = positionFromTileIndex(1, 7);
        Vector2 positionObjetT2 = positionFromTileIndex(1, 6);
        Vector2 positionObjetT3 = positionFromTileIndex(1, 5);

        StdDraw.picture(positionObjet1.getX() - RoomInfos.DIFF_POSITION_NB_OBJET, positionObjet1.getY(), ImagePaths.COIN, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
        StdDraw.text(positionObjetT1.getX() - RoomInfos.DIFF_POSITION_NB_OBJET_TEXT, positionObjetT1.getY(), String.valueOf(this.hero.getNombrePiece()));

        StdDraw.picture(positionObjet2.getX() - RoomInfos.DIFF_POSITION_NB_OBJET, positionObjet2.getY(), ImagePaths.BOMB, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
        StdDraw.text(positionObjetT2.getX() - RoomInfos.DIFF_POSITION_NB_OBJET_TEXT, positionObjetT2.getY(), "50");

        StdDraw.picture(positionObjet3.getX() - RoomInfos.DIFF_POSITION_NB_OBJET, positionObjet3.getY(), ImagePaths.KEY, RoomInfos.HALF_TILE_SIZE.getX(), RoomInfos.HALF_TILE_SIZE.getY(), 0);
        StdDraw.text(positionObjetT3.getX() - RoomInfos.DIFF_POSITION_NB_OBJET_TEXT, positionObjetT3.getY(), "40");


        /*
         * Affichage des portes
         */
        if (this.portes != null) {
            for (HashMap.Entry<Direction, Porte> porte : this.portes.entrySet()) {
                if (porte.getValue().getSalleSuivante() != null) { // On affiche une porte uniquement si elle donne vers une autre salle
                    Vector2 position = porte.getValue().getPosition();
                    int[] index = this.getIndexX_Y(position.getX(), position.getY());
                    this.carreaux[index[0]][index[1]] = porte.getValue();

                    //S'il n'y a plus de monstre on ouvre les portes.
                    if (this.monstres == null || this.monstres.isEmpty()) {
                        porte.getValue().setClose(false);
                    }

                    porte.getValue().drawPorte();
                }
            }
        }

        hero.getCurrentArme().drawArme();

        hero.drawGameObject();
    }

    /**
     * Déplacement des monstres
     */
    public void deplacerMonstreAleatoirement() {
        if (this.monstres != null) {
            for (Personnage monstre : this.monstres) {
                Vector2 direction = monstre.getDirection();

                switch (getRandomNumber(0,3)){
                    case 0 :
                        direction.addY(1);
                        monstre.getCurrentArme().getDirection().addY(-1); // Pour le déplacement de l'arme
                        break;
                    case 1:
                        direction.addX(1);
                    case 2 :
                        direction.addY(-1);
                        break;
                    case 3:
                        direction.addX(-1);
                }

                monstre.move(this);
            }
        }
    }

    /**
     * Déplacement des monstres
     */
    public void tirerMonstreAleatoirement() {
        if (this.monstres != null) {
            for (Personnage monstre : this.monstres) {
                Vector2 direction = monstre.getDirection();

                switch (getRandomNumber(0,3)){
                    case 0 :
                        monstre.getCurrentArme().getDirection().addY(1);
                        break;
                    case 1:
                        monstre.getCurrentArme().getDirection().addX(1);
                    case 2 :
                        monstre.getCurrentArme().getDirection().addY(-1);
                        break;
                    case 3:
                        monstre.getCurrentArme().getDirection().addX(-1);
                }
            }
        }
    }

    /**
     * Générateur de nombre aléatoire entre min et max
     * @param min
     * @param max
     * @return
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Convert a tile index to a 0-1 position.
     *
     * @param indexX
     * @param indexY
     * @return
     */
    private static Vector2 positionFromTileIndex(int indexX, int indexY) {
        return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
                indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
    }

    /**
     * @return
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * @param hero
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * @return
     */
    public HashMap<Direction, Porte> getPortes() {
        return portes;
    }

    /**
     * @param portes
     */
    public void setPortes(HashMap<Direction, Porte> portes) {
        this.portes = portes;
    }

    /**
     * @param directionPorte
     * @return
     */
    public Porte getPorte(Direction directionPorte) {
        return portes.get(directionPorte);
    }


    /**
     * @return
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * @param obstacles
     */
    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }


    /**
     * @return
     */
    public List<Personnage> getMonstres() {
        return monstres;
    }

    /**
     * @param monstres
     */
    public void setMonstres(List<Personnage> monstres) {
        this.monstres = monstres;
    }

    /**
     * @return
     */
    public List<Objet> getObjets() {
        return objets;
    }

    /**
     * @param objets
     */
    public void setObjets(List<Objet> objets) {
        this.objets = objets;
    }

    /**
     * @return
     */
    public List<Mur> getMurs() {
        return murs;
    }

    /**
     * @param murs
     */
    public void setMurs(List<Mur> murs) {
        this.murs = murs;
    }

    /**
     * @return
     */
    public List<Equipement> getEquipements() {
        return equipements;
    }

    /**
     * @param equipements
     */
    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    /**
     * @return
     */
    public ElementSalle[][] getCarreaux() {
        return carreaux;
    }

    /**
     * @param carreaux
     */
    public void setCarreaux(ElementSalle[][] carreaux) {
        this.carreaux = carreaux;
    }

    /**
     * Vérifie si le déplacement est possible dans la salle,
     * Si oui, le personnage est déplacé, sinon il reste à sa position
     *
     * @param x
     * @param y
     * @return
     */
    public boolean controleDeplacement(double x, double y, Direction direction) {

        /**
         * En utilisant la méthode positionFromTileIndex, on position les objets dans la salle à partir d'un index entier,
         * en utilisant la formule indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX()
         *
         * Pour retrouver cet indexX et indexY, on utilse donc la formule si dessous
         *
         * Technique de résolution d'équation
         */

        int[] index = getIndexX_Y(x, y);
        int indexX = index[0];
        int indexY = index[1];
        boolean condition = false;

        try {
            if (direction == Direction.GAUCHE) {
                condition = (indexX - 1) >= 0 && !(this.getCarreaux()[(indexX - 1)][indexY] instanceof Stop);
            } else if (direction == Direction.DROITE) {
                condition = (indexX + 1) <= (RoomInfos.NB_TILES - 1) && !(this.getCarreaux()[(indexX + 1)][indexY] instanceof Stop);
            } else if (direction == Direction.HAUT) {
                condition = (indexY + 1) <= (RoomInfos.NB_TILES - 1) && !(this.getCarreaux()[indexX][(indexY + 1)] instanceof Stop);
            } else if (direction == Direction.BAS) {
                condition = (indexY - 1) >= 0 && !(this.getCarreaux()[indexX][(indexY - 1)] instanceof Stop);
            }
        } catch (Exception e) {
            System.out.println("Débordement");
        }

        return condition;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    public static int[] getIndexX_Y(double x, double y) {
        double X = ((x - RoomInfos.HALF_TILE_SIZE.getX()) / RoomInfos.TILE_WIDTH);
        double Y = ((y - RoomInfos.HALF_TILE_SIZE.getY()) / RoomInfos.TILE_HEIGHT);

        DecimalFormat df = new DecimalFormat("#.#");

        int indexX = (int) (Double.parseDouble(df.format(X).replace(",", ".")));
        int indexY = (int) (Double.parseDouble(df.format(Y).replace(",", ".")));


        return new int[]{indexX, indexY};
    }
}
