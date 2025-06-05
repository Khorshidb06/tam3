package ok.UpDown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ok.UpDown.Main;
import ok.UpDown.Model.*;
import ok.UpDown.View.GameView;

import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    PlayerController playerController;
    EnemyController enemyController;
    private Weapon weapon;
    Player player=GameData.getLoggedInPlayer();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private final GameView view;

    public WeaponController(Weapon weapon, PlayerController playerController, EnemyController enemyController, GameView view){
        this.weapon = weapon;
        this.playerController=playerController;
        this.enemyController=enemyController;
        this.view = view;
    }

    public void update(){
        weapon.getSmgSprite().draw(Main.getBatch());
        updateBullets();
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getSmgSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y){
        if (weapon.getAmmo()>0){
            if (GameData.isSfx())Main.getMain().playShot();
            for (int i=0; i<weapon.getWeaponTypes().getProjectile(); i++){
                bullets.add(new Bullet(x, y, playerController.getPlayer().getPosY(),playerController.getPlayer().getPosX()));
            }
            weapon.setAmmo(weapon.getAmmo() - 1);
        }

    }

    public boolean handleReload(){
        return Gdx.input.isKeyPressed(Input.Keys.R);
    }

    public void updateBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet b = iterator.next();

            b.getSprite().draw(Main.getBatch());

            Vector2 direction = new Vector2(
                Gdx.graphics.getWidth() / 2f - b.getX(),
                Gdx.graphics.getHeight() / 2f - b.getY()
            ).nor();

            b.getSprite().setX(b.getSprite().getX() - direction.x * 5);
            b.getSprite().setY(b.getSprite().getY() + direction.y * 5);

            b.setPosX(b.getPosX() + direction.x * 5);
            b.setPosY(b.getPosY() + direction.y * 5);

            b.getRect().setX(b.getPosX());
            b.getRect().setY(b.getPosY());

            if (checkCollisions(b)) {
                iterator.remove();
            }


        }
    }


    //TODO: check and fix
    public boolean checkCollisions(Bullet b){
        boolean a=false;
        boolean c=false;
        for (Enemy enemy :enemyController.getEnemies()){
            if (enemy.getRect().collidesWith(b.getRect())) {
                enemy.setEnemyHealth(enemy.getEnemyHealth()-weapon.getWeaponTypes().getDamage());
                view.hitEffects.add(new HitEffect(enemy.getPosX(), enemy.getPosY(), GameAssetManager.getGameAssetManager().getHit()));
                a=true;
            }
        }
        if (!a){
            for (Enemy enemy :enemyController.getEyeBats()){
                if (enemy.getRect().collidesWith(b.getRect())) {
                    enemy.setEnemyHealth(enemy.getEnemyHealth()-weapon.getWeaponTypes().getDamage());
                    view.hitEffects.add(new HitEffect(enemy.getPosX(), enemy.getPosY(), GameAssetManager.getGameAssetManager().getHit()));
                    c=true;
                }
            }
        }
        if (c|a) {
            if (GameData.isSfx())Main.getMain().playHit();
            player.setKill(player.getKill()+1);
        }

        return c|a;
    }

}
