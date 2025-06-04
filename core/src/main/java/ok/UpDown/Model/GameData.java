package ok.UpDown.Model;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GameData {
    public static Array<Player> allPlayers = new Array<>();
    private static Player loggedInPlayer;
    private static float time;
    private static boolean isFinished = false;
    private static boolean isPaused=false;
    private static Stage popupStage = null;
    private static float passedTime = 0f;
    private static float lastSpawn = 0;
    private static float lastSpawn2 = 0;
    private static ArrayList<Tree> allEnemies = new ArrayList<>();
    private static ArrayList<Enemy> allTentacles = new ArrayList<>();
    private static ArrayList<Enemy> allEyeBat = new ArrayList<>();
    private static ArrayList<Seed> allSeeds = new ArrayList<>();
    private static boolean autoReload=true;
    private static float reloadWeaponTime=0;

    public GameData() {

    }

    public static Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public static void setLoggedInPlayer(Player loggedInPlayer) {
        GameData.loggedInPlayer = loggedInPlayer;
    }

    public static float getTime() {
        return time;
    }

    public static void setTime(float time) {
        GameData.time = time;
    }

    public static float getPassedTime() {
        return passedTime;
    }

    public static void setPassedTime(float passedTime) {
        GameData.passedTime = passedTime;
    }

    public static boolean isIsFinished() {
        return isFinished;
    }

    public static void setIsFinished(boolean isFinished) {
        GameData.isFinished = isFinished;
    }

    public static ArrayList<Tree> getAllEnemies() {
        return allEnemies;
    }

    public static float getLastSpawn() {
        return lastSpawn;
    }

    public static void setLastSpawn(float lastSpawn) {
        GameData.lastSpawn = lastSpawn;
    }

    public static ArrayList<Enemy> getAllTentacles() {
        return allTentacles;
    }

    public static float getLastSpawn2() {
        return lastSpawn2;
    }

    public static void setLastSpawn2(float lastSpawn2) {
        GameData.lastSpawn2 = lastSpawn2;
    }

    public static ArrayList<Enemy> getAllEyeBat() {
        return allEyeBat;
    }

    public static void setAllEyeBat(ArrayList<Enemy> allEyeBat) {
        GameData.allEyeBat = allEyeBat;
    }

    public static ArrayList<Seed> getAllSeeds() {
        return allSeeds;
    }

    public static float getReloadWeaponTime() {
        return reloadWeaponTime;
    }

    public static void setReloadWeaponTime(float reloadWeaponTime) {
        GameData.reloadWeaponTime = reloadWeaponTime;
    }

    public static boolean isAutoReload() {
        return autoReload;
    }

    public static void setAutoReload(boolean autoReload) {
        GameData.autoReload = autoReload;
    }

    public static boolean isIsPaused() {
        return isPaused;
    }

    public static void setIsPaused(boolean isPaused) {
        GameData.isPaused = isPaused;
    }

    public static Stage getPopupStage() {
        return popupStage;
    }

    public static void setPopupStage(Stage popupStage) {
        GameData.popupStage = popupStage;
    }
}
