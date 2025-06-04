package ok.UpDown.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tree {
    private Texture enemyTex;
    private Sprite sprite;
    private CollisionRect rect;
    private float posX ;
    private float posY ;
    private float time = 0;
    private float enemyHealth;

    public Tree(float posX, float posY) {
        this.enemyTex = GameAssetManager.getGameAssetManager().getTree_Monster0();
        this.sprite=new Sprite(enemyTex);
        this.rect = new CollisionRect(posX,posY,enemyTex.getWidth()*3, enemyTex.getHeight()*3);
        this.enemyHealth = 5;
        this.posX=posX;
        this.posY=posY;
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

    public CollisionRect getRect() {
        return rect;
    }
}
