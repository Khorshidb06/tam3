package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Seed {
    private float posX;
    private float posY;
    private CollisionRect rect;
    private Texture texture=GameAssetManager.getGameAssetManager().getSeed();
    private Sprite sprite = new Sprite(texture);


    public Seed(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.rect=new CollisionRect(posX,posY,texture.getWidth(),texture.getHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getPosY() {
        return posY;
    }

    public float getPosX() {
        return posX;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
