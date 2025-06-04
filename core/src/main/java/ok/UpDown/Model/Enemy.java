package ok.UpDown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {

    private Texture enemyTex;
    private Sprite sprite;
    private CollisionRect rect;
    private float posX ;
    private float posY ;
    private float time = 0;
    private float enemyHealth;

    public Enemy(float posX, float posY,Texture texture, float enemyHealth) {
        this.enemyTex = texture;
        this.sprite=new Sprite(enemyTex);
        this.rect = new CollisionRect(posX,posY,enemyTex.getWidth()*3, enemyTex.getHeight()*3);
        this.posX=posX;
        this.posY=posY;
        this.enemyHealth=enemyHealth;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(float enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

}
