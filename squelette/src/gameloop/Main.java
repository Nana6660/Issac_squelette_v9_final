package gameloop;

import gameWorld.GameWorld;
import gameWorld.Room;
import gameobjects.*;
import libraries.StdDraw;
import libraries.Timer;
import libraries.Vector2;
import resources.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Hero, world and display initialisation.
        List<Arme> armes = new ArrayList<>();
        armes.add(new Larme(2.2, 0.2, 0.2, RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.TEAR));


        List<Arme> armes1 = new ArrayList<>();
        armes1.add(new Larme(2.2, 0.2, 0.1, RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.TEAR));


        List<Arme> armes2 = new ArrayList<>();
        armes2.add(new Larme(2.2, 0.2, 0.1, RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.TEAR));


        List<Arme> armes3 = new ArrayList<>();
        armes3.add(new Larme(2.2, 0.2, 0.1, RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.TEAR));

        List<Arme> armes4 = new ArrayList<>();
        armes4.add(new Larme(2.2, 0.2, 0.1, RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.TEAR));

        List<Arme> armes5 = new ArrayList<>();
        armes5.add(new Larme(2.2, 0.2, 0.1, RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.TEAR));

        Hero isaac = new Hero(HeroInfos.ISAAC_VIE, armes, RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, ImagePaths.ISAAC, false);

        /**
         * Configuration Salle Spawn
         */
        //Initialisation des portes
        HashMap<Direction, Porte> portesSalleSpawn = new HashMap<>();
        Porte salleSpawnPorteDroite = new Porte(true, positionFromTileIndex((RoomInfos.NB_TILES - 1), (RoomInfos.NB_TILES - 1) / 2), Direction.DROITE);

        portesSalleSpawn.put(Direction.DROITE, salleSpawnPorteDroite);

        //Initialisation des murs
        List<Mur> murs = new ArrayList<>();
        for (int i = 0; i < RoomInfos.NB_TILES; i++) {
            for (int j = 0; j < RoomInfos.NB_TILES; j++) {
                if (i == 0 || j == 0 || i == RoomInfos.NB_TILES - 1 || j == RoomInfos.NB_TILES - 1) {
                    Mur mur = new Mur();
                    mur.setPosition(positionFromTileIndex(i, j));
                    murs.add(mur);
                }
            }
        }

        Room salleSpawn = new Room(isaac, portesSalleSpawn, null, null, null, murs);

        /**
         * Configuration Salle Monstre 1
         */
        //Initialisation des portes
        HashMap<Direction, Porte> portesSalleMonstre = new HashMap<>();
        Porte MonstrePorteHaut = new Porte(true, positionFromTileIndex((RoomInfos.NB_TILES - 1) / 2, (RoomInfos.NB_TILES - 1)), Direction.HAUT);
        Porte MonstrePorteGauche = new Porte(true, positionFromTileIndex(0, (RoomInfos.NB_TILES - 1) / 2), Direction.GAUCHE);

        portesSalleMonstre.put(Direction.HAUT, MonstrePorteHaut);
        portesSalleMonstre.put(Direction.GAUCHE, MonstrePorteGauche);

        //Initialisation des obstacles
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Pique(positionFromTileIndex(5, 3), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.STIGMATA));
        obstacles.add(new Rocher(positionFromTileIndex(7, 4), RoomInfos.TILE_SIZE.scalarMultiplication(1), ImagePaths.ROCK));

        //Initialisation des monstres
        List<Personnage> monstres = new ArrayList<>();
        monstres.add(new Fly(MonstresInfos.FLY_VIE, armes1, positionFromTileIndex(3, 3), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.FLY_SPEED, ImagePaths.FLY, false));
        monstres.add(new Spider(MonstresInfos.SPIDER_VIE, armes2, positionFromTileIndex(2, 1), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.SPIDER_SPEED, ImagePaths.SPIDER, false));

        //Initialisation des équipements
        List<Equipement> equipements = new ArrayList<>();
        equipements.add(new BloodOfTheMartyr(positionFromTileIndex(3, 6), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.BLOOD_OF_THE_MARTYR));
        equipements.add(new Inferieur3(positionFromTileIndex(2, 5), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.HP_UP));

        //Création Salle Monstre
        Room salleMonstre = new Room(isaac, portesSalleMonstre, obstacles, monstres, null, murs,equipements);

        /**
         * Configuration Salle Monstre 2
         */
        //Initialisation des portes
        HashMap<Direction, Porte> portesSalleMonstre2 = new HashMap<>();
        Porte MonstrePorteBas2 = new Porte(true, positionFromTileIndex((RoomInfos.NB_TILES - 1) / 2, 0), Direction.BAS);
        Porte MonstrePorteGauche2 = new Porte(true, positionFromTileIndex(0, (RoomInfos.NB_TILES - 1) / 2), Direction.GAUCHE);

        portesSalleMonstre2.put(Direction.HAUT, MonstrePorteBas2);
        portesSalleMonstre2.put(Direction.GAUCHE, MonstrePorteGauche2);

        //Initialisation des obstacles
        List<Obstacle> obstacles2 = new ArrayList<>();
        obstacles2.add(new Pique(positionFromTileIndex(1, 2), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.STIGMATA));
        obstacles2.add(new Rocher(positionFromTileIndex(5, 6), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.ROCK));

        //Initialisation des monstres
        List<Personnage> monstres2 = new ArrayList<>();
        monstres2.add(new Fly(MonstresInfos.FLY_VIE, armes3, positionFromTileIndex(3, 3), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.FLY_SPEED, ImagePaths.FLY, false));
        monstres2.add(new Spider(MonstresInfos.SPIDER_VIE, armes4, positionFromTileIndex(2, 1), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.SPIDER_SPEED, ImagePaths.SPIDER, false));
//        monstres2.add(new Boss(MonstresInfos.BOSS_VIE, armes4, positionFromTileIndex(2, 1), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.BOSS_SPEED, ImagePaths.MAGDALENE, false));

        //Création Salle Monstre2
        Room salleMonstre2 = new Room(isaac, portesSalleMonstre2, obstacles2, monstres2, null, murs);

        /**
         * Configuration Salle Magasin1
         */

        //Initialisation des portes
        HashMap<Direction, Porte> portesSalleMagasin1 = new HashMap<>();
        Porte magasinPorteDroite = new Porte(true, positionFromTileIndex((RoomInfos.NB_TILES - 1), (RoomInfos.NB_TILES - 1) / 2), Direction.DROITE);
        Porte magasinPorteGauche = new Porte(true, positionFromTileIndex(0, (RoomInfos.NB_TILES - 1) / 2), Direction.GAUCHE);

        portesSalleMagasin1.put(Direction.DROITE,magasinPorteDroite);
        portesSalleMagasin1.put(Direction.GAUCHE,magasinPorteGauche);

        //Initialisation des objets
        List<Objet> objets = new ArrayList<>();
        objets.add(new Piece(positionFromTileIndex(3, 6), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.DIME, TypePiece.DIME));
        objets.add(new Piece(positionFromTileIndex(2, 5), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.NICKEL));
        objets.add(new Piece(positionFromTileIndex(4, 1), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.COIN, TypePiece.PENNY));
        objets.add(new Coeur(positionFromTileIndex(6, 2), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.HALF_HEART_HUD));
        objets.add(new Coeur(positionFromTileIndex(6, 6), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.HEART_HUD));

        //Création Salle magasin
        Room salleMagasin1 = new Room(isaac, portesSalleMagasin1, null, null, objets, murs);

        /**
         * Configuration Salle BOSS
         */
        //Initialisation des portes
        HashMap<Direction, Porte> portesSalleBoss = new HashMap<>();
        Porte bossPorteBas2 = new Porte(true, positionFromTileIndex((RoomInfos.NB_TILES - 1) / 2, 0), Direction.BAS);
        Porte bossPorteGauche2 = new Porte(true, positionFromTileIndex(0, (RoomInfos.NB_TILES - 1) / 2), Direction.GAUCHE);
        Porte bossPorteDroite = new Porte(true, positionFromTileIndex((RoomInfos.NB_TILES - 1), (RoomInfos.NB_TILES - 1) / 2), Direction.DROITE);

        portesSalleBoss.put(Direction.HAUT, bossPorteBas2);
        portesSalleBoss.put(Direction.GAUCHE, bossPorteGauche2);
        portesSalleBoss.put(Direction.DROITE, bossPorteDroite);

        //Initialisation des obstacles
        List<Obstacle> obstacles3 = new ArrayList<>();
        obstacles3.add(new Pique(positionFromTileIndex(5, 7), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.STIGMATA));
        obstacles3.add(new Pique(positionFromTileIndex(2, 7), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.STIGMATA));
        obstacles3.add(new Pique(positionFromTileIndex(5, 5), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.STIGMATA));
        obstacles3.add(new Rocher(positionFromTileIndex(3, 6), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), ImagePaths.ROCK));

        //Initialisation des monstres
        List<Personnage> monstres3 = new ArrayList<>();
        monstres3.add(new Fly(MonstresInfos.FLY_VIE, armes3, positionFromTileIndex(3, 3), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.FLY_SPEED, ImagePaths.FLY, false));
        monstres3.add(new Spider(MonstresInfos.SPIDER_VIE, armes4, positionFromTileIndex(2, 1), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.SPIDER_SPEED, ImagePaths.SPIDER, false));
        monstres3.add(new Boss(MonstresInfos.BOSS_VIE, armes4, positionFromTileIndex(2, 1), RoomInfos.TILE_SIZE.scalarMultiplication(0.5), MonstresInfos.BOSS_SPEED, ImagePaths.MAGDALENE, false));

        //Création Salle Monstre2
        Room salleBoss= new Room(isaac, portesSalleBoss, obstacles3, monstres3, null, murs);
        /**
         * NB : tant qu'une porte n'est pas liée à une salle, elle ne s'affiche pas
         * Liaison des salles par les portes
         */
        // Salle Spawn et Salle Monstre
        salleSpawnPorteDroite.setSalleSuivante(salleMonstre);
        MonstrePorteGauche.setSalleSuivante(salleSpawn);

        // Salle monstre et Salle Monstre2
        MonstrePorteHaut.setSalleSuivante(salleMonstre2);
        MonstrePorteBas2.setSalleSuivante(salleMonstre);

        // Salle monstre2 et Salle magasin1
        MonstrePorteGauche2.setSalleSuivante(salleMagasin1);
        magasinPorteDroite.setSalleSuivante(salleMonstre2);

        // Salle magasin1 et Salle Boss
        magasinPorteGauche.setSalleSuivante(salleBoss);
        bossPorteDroite.setSalleSuivante(salleMagasin1);

        //Lancement du jeu avec la salle Spawn
        GameWorld world = new GameWorld(salleSpawn);
        isaac.setGameWorld(world); // On affecte gameWorld à Isaac pour pouvoir changer de salle depuis la classe Personnage

        initializeDisplay();

        // Main loop of the game
        while (!world.gameOver()) {
            processNextStep(world);
        }

        System.out.println("Game Over");
    }


    private static void processNextStep(GameWorld world) {
        Timer.beginTimer();
        StdDraw.clear();
        world.processUserInput();
        world.updateGameObjects();
        world.drawGameObjects();
        StdDraw.show();
        Timer.waitToMaintainConstantFPS();
    }

    private static void initializeDisplay() {
        // Set the window's size, in pixels.
        // It is strongly recommended to keep a square window.
        StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
                RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

        // Enables double-buffering.
        // https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
        StdDraw.enableDoubleBuffering();
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
}
