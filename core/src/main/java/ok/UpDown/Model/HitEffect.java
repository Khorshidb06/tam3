package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HitEffect {
    float x, y;
    float stateTime = 0f;
    Animation<Texture> animation;
    Player player=GameData.getLoggedInPlayer();

    public HitEffect(float x, float y, Animation<Texture> animation) {
        this.x = x;
        this.y = y;
        this.animation = animation;
    }

    public boolean isFinished() {
        return animation.isAnimationFinished(stateTime);
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void draw(Batch batch) {
        Texture currentFrame = animation.getKeyFrame(stateTime, false);

        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        float drawX = screenCenterX - (x - player.getPosX());
        float drawY = screenCenterY - (y - player.getPosY());
        batch.draw(currentFrame, drawX, drawY);
    }
}

