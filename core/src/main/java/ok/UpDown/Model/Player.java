package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.PrimitiveIterator;

public class Player {
    private String userName;
    private String password;
    private final String securityQuest;
    private final String securityAns;
    private Texture avatar;
    private Texture playerTexture = new Texture(GameAssetManager.getGameAssetManager().getCharacter1_idle0());
    private Sprite playerSprite = new Sprite(playerTexture);
    private float posX = 0;
    private float posY = 0;
    private float playerHealth ;
    private int kill=0;
    private float lastDamageTime= 0f;
    private float timeAlive=0;
    private HashMap<String, Integer> abilities=new HashMap<>();
    private CollisionRect rect ;
    private float time = 0;
    private float speed = 5;
    private Weapon weapon;
    private String hero;
    private int xp=0;
    private int level=1;

    public float getSpeed() {
        return speed;
    }

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(String userName, String password, float playerHealth, float speed, String securityQuest, String securityAns, Texture avatar){
        this.userName=userName;
        this.password=password;
        abilities.put("vitality", 0);
        abilities.put("procrease", 0);
        abilities.put("ammocrease", 0);
        this.playerHealth=playerHealth;
        this.speed=speed;
        this.securityAns=securityAns;
        this.securityQuest=securityQuest;
        this.avatar = avatar;
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight(), playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }


    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }


    public void setHero(String hero) {
        this.hero = hero;
    }

    public void setWeapon(Weapon  weapon) {
        this.weapon = weapon;
    }

    public String getHero() {
        return hero;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getSecurityQuest() {
        return securityQuest;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public Texture getAvatar() {
        return avatar;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public float getLastDamageTime() {
        return lastDamageTime;
    }

    public void setLastDamageTime(float lastDamageTime) {
        this.lastDamageTime = lastDamageTime;
    }

    public HashMap<String, Integer> getAbilities() {
        return abilities;
    }

    public void setAbilities(HashMap<String, Integer> abilities) {
        this.abilities = abilities;
    }

    public float getTimeAlive() {
        return timeAlive;
    }

    public void setTimeAlive(float timeAlive) {
        this.timeAlive = timeAlive;
    }
}
