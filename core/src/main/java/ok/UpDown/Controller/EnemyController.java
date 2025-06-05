package ok.UpDown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import ok.UpDown.Main;
import ok.UpDown.Model.*;
import ok.UpDown.View.GameView;

import java.util.Iterator;


import java.util.ArrayList;

public class EnemyController {
    private PlayerController playerController;
    private GameView view;
    private ArrayList<Tree> trees;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> eyeBats;

    public EnemyController(ArrayList<Tree> trees, PlayerController playerController, ArrayList<Enemy> enemies, ArrayList<Enemy> eyeBats, GameView view) {
        this.eyeBats = eyeBats;
        this.enemies = enemies;
        this.trees = trees;
        this.playerController = playerController;
        this.view=view;
    }

    public void updateEnemies(float delta) {
        Player player = playerController.getPlayer();
        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        for (Tree enemy : trees) {
            idleAnimationTree(enemy);
            float drawX = screenCenterX - (enemy.getPosX() - player.getPosX());
            float drawY = screenCenterY - (enemy.getPosY() - player.getPosY());
            enemy.getSprite().setX(drawX);
            enemy.getSprite().setY(drawY);
            enemy.getSprite().draw(Main.getBatch());

        }
        Iterator<Enemy> iterator1 = enemies.iterator();
        while (iterator1.hasNext()) {
            Enemy enemy = iterator1.next();
            idleAnimationEnemy(enemy, GameAssetManager.getGameAssetManager().getTentacleEnemy());

            float dx = player.getPosX() - enemy.getPosX();
            float dy = player.getPosY() - enemy.getPosY();
            Vector2 direction = new Vector2(dx, dy).nor();

            enemy.setPosX(enemy.getPosX() + direction.x * 100 * delta);
            enemy.setPosY(enemy.getPosY() + direction.y * 100 * delta);

            float drawX = screenCenterX - (enemy.getPosX() - player.getPosX());
            float drawY = screenCenterY - (enemy.getPosY() - player.getPosY());
            enemy.getSprite().setX(drawX);
            enemy.getSprite().setY(drawY);

            enemy.getRect().setX(enemy.getPosX());
            enemy.getRect().setY(enemy.getPosY());

            if (enemy.getEnemyHealth() <= 0) {
                GameData.getAllSeeds().add(new Seed(enemy.getPosX(), enemy.getPosY()));
                view.hitEffects.add(new HitEffect(enemy.getPosX(), enemy.getPosY(), GameAssetManager.getGameAssetManager().getExplosion()));

                iterator1.remove();
                continue;
            }

            enemy.getSprite().draw(Main.getBatch());
        }

        Iterator<Enemy> iterator = eyeBats.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();

            idleAnimationEnemy(enemy, GameAssetManager.getGameAssetManager().getEyeBatEnemy());

            float dx = player.getPosX() - enemy.getPosX();
            float dy = player.getPosY() - enemy.getPosY();
            Vector2 direction = new Vector2(dx, dy).nor();

            enemy.setPosX(enemy.getPosX() + direction.x * 100 * delta);
            enemy.setPosY(enemy.getPosY() + direction.y * 100 * delta);

            float drawX = screenCenterX - (enemy.getPosX() - player.getPosX());
            float drawY = screenCenterY - (enemy.getPosY() - player.getPosY());
            enemy.getSprite().setX(drawX);
            enemy.getSprite().setY(drawY);

            enemy.getRect().setX(enemy.getPosX());
            enemy.getRect().setY(enemy.getPosY());

            if (enemy.getEnemyHealth() <= 0) {
                GameData.getAllSeeds().add(new Seed(enemy.getPosX(), enemy.getPosY()));
                iterator.remove();
                view.hitEffects.add(new HitEffect(enemy.getPosX(), enemy.getPosY(), GameAssetManager.getGameAssetManager().getExplosion()));
                continue;
            }
            enemy.getSprite().draw(Main.getBatch());
        }
        updateSeeds();


    }

    public void updateSeeds() {
        Player player = playerController.getPlayer();
        Iterator<Seed> iterator = GameData.getAllSeeds().iterator();

        while (iterator.hasNext()) {
            Seed seed = iterator.next();

            float screenCenterX = Gdx.graphics.getWidth() / 2f;
            float screenCenterY = Gdx.graphics.getHeight() / 2f;
            float drawX = screenCenterX - (seed.getPosX() - player.getPosX());
            float drawY = screenCenterY - (seed.getPosY() - player.getPosY());

            seed.getSprite().setX(drawX);
            seed.getSprite().setY(drawY);
            seed.getSprite().draw(Main.getBatch());

            if (player.getRect().collidesWith(seed.getRect())) {
                player.setXp(player.getXp() + 3);
                iterator.remove();
            }
        }
    }


    public void idleAnimationTree(Tree tree) {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getTree_Monster();

        tree.getSprite().setRegion(animation.getKeyFrame(tree.getTime()));
        if (!animation.isAnimationFinished(tree.getTime())) {
            tree.setTime(tree.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            tree.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void idleAnimationEnemy(Enemy enemy, Animation<Texture> animation) {
        //Animation<Texture> animation = GameAssetManager.getGameAssetManager().getTree_Monster();

        enemy.getSprite().setRegion(animation.getKeyFrame(enemy.getTime()));
        if (!animation.isAnimationFinished(enemy.getTime())) {
            enemy.setTime(enemy.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            enemy.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Enemy> getEyeBats() {
        return eyeBats;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }
}
