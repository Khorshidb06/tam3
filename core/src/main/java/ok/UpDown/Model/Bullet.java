package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 5;
    private CollisionRect rect;
    private float x;
    private float y;
    private float posX;
    private float posY;

    public Bullet(float x, float y, float posY, float posX){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        this.posY=posY;
        this.posX=posX;
        this.rect=new CollisionRect(posX,posY,texture.getWidth(),texture.getHeight());
        sprite.setX((float) Gdx.graphics.getWidth() / 2);
        sprite.setY((float) Gdx.graphics.getHeight() / 2);
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
