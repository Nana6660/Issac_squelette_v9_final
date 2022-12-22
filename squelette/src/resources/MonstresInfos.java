package resources;

import libraries.Vector2;

public class MonstresInfos {

    public static Vector2 SPIDER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
    public static final double SPIDER_SPEED = 0.02;
    public static final double SPIDER_VIE = 5.0;

    public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
    public static final double FLY_SPEED = (1 / 8) * HeroInfos.ISAAC_SPEED;
    public static final double FLY_VIE = 3.0;

    public static Vector2 BOSS_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(3.0);
    public static final double BOSS_SPEED = 0.01;
    public static final double BOSS_VIE = 6.0;
}
