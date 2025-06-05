package ok.UpDown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import ok.UpDown.Main;
import ok.UpDown.Model.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.View.GameView;

import java.util.Random;


public class PlayerController {
    private final Player player=GameData.getLoggedInPlayer();
    private EnemyController enemyController;
    private GameView view;

    public void setView(GameView view) {
        this.view = view;
    }

    public PlayerController( EnemyController enemyController){
        this.enemyController=enemyController;

    }

    public void update() {

        float delta = Gdx.graphics.getDeltaTime();

        Main.getBatch().setColor(1f, 1f, 1f, 0.5f);
        Main.getBatch().draw(GameAssetManager.getGameAssetManager().getGlowTexture(), player.getPlayerSprite().getX()-100,
            player.getPlayerSprite().getY()-70, player.getPlayerSprite().getWidth()*5,
            player.getPlayerSprite().getHeight()*3);
        Main.getBatch().setColor(1f, 1f, 1f, 1f);
        player.getPlayerSprite().draw(Main.getBatch());

        if (player.isPlayerIdle()) {
            idleAnimation();
        }

        handlePlayerInput();

        player.getRect().setX(player.getPosX());
        player.getRect().setY(player.getPosY());

        handlePlayerLevel();

        player.setLastDamageTime(player.getLastDamageTime() + delta);

        if (player.getLastDamageTime() >= 1f) {
            boolean damaged = false;

            for (Tree tree : GameData.getAllEnemies()) {
                if (player.getRect().collidesWith(tree.getRect())) {
                    damaged = true;
                    break;
                }
            }

            if (!damaged) {
                for (Enemy enemy : GameData.getAllTentacles()) {
                    if (player.getRect().collidesWith(enemy.getRect())) {
                        damaged = true;
                        break;
                    }
                }
            }

            if (!damaged) {
                for (Enemy enemy : GameData.getAllEyeBat()) {
                    if (player.getRect().collidesWith(enemy.getRect())) {
                        damaged = true;
                        break;
                    }
                }
            }

            if (damaged) {
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                player.setLastDamageTime(0f);
            }
        }
    }



    public void handlePlayerLevel(){
        Random random=new Random();
        int rand= random.nextInt(3);
        if (player.getXp()>=player.getLevel()*20){
            player.setLevel(player.getLevel()+1);
            player.setXp(0);
            if (rand==0){
                player.getAbilities().put("vitality", player.getAbilities().get("vitality")+1);
                player.setPlayerHealth(player.getPlayerHealth()+1);//vitality
                view.showDialog("", "vitality ability", ()->{});
            }
            else if (rand==1){
                player.getAbilities().put("procrease", player.getAbilities().get("procrease")+1);
                player.getWeapon().getWeaponTypes().setProjectile(player.getWeapon().getWeaponTypes().getProjectile()+1);//PROⅭREASE
                view.showDialog("","procrease ability", ()->{});
            }
            else if (rand==2){

                player.getAbilities().put("ammocrease", player.getAbilities().get("ammocrease")+1);
                player.getWeapon().getWeaponTypes().setAmmoMax(player.getWeapon().getWeaponTypes().getAmmoMax()+5);//AⅯOⅭREASE
                view.showDialog("", "ammocrease ability", ()->{});
            }
            else if (rand==3);
            else ;
        }

    }
    public void handlePlayerInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            player.setPosY(player.getPosY() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            player.setPosX(player.getPosX() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            player.setPosY(player.getPosY() + player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            player.setPosX(player.getPosX() + player.getSpeed());
            player.getPlayerSprite().flip(true, false);
        }
    }



    public void idleAnimation(){
        Player player1= GameData.getLoggedInPlayer();
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getCharacter1_idle_animation();

        if (player1.getHero().equals("Dasher")) {
             animation = GameAssetManager.getGameAssetManager().getCharacter2_idle_frames();
        }if (player1.getHero().equals("Diamond")) {
            animation = GameAssetManager.getGameAssetManager().getCharacter3_idle_frames();
        }
        if (player1.getHero().equals("Lilith")) {
            animation = GameAssetManager.getGameAssetManager().getCharacter4_idle_frames();
        }
        if (player1.getHero().equals("Scarlet")) {
            animation = GameAssetManager.getGameAssetManager().getCharacter5_idle_frames();
        }


        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

}
