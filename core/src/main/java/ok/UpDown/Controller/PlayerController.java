package ok.UpDown.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import ok.UpDown.Main;
import ok.UpDown.Model.*;
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

        Main.getBatch().setColor(1f, 1f, 1f, 0.3f);
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

        handlePlayerLevel(delta);

        player.setLastDamageTime(player.getLastDamageTime() + delta);

        if (player.getLastDamageTime()<1f){
            Main.getBatch().draw(GameAssetManager.getGameAssetManager().getShield0(), player.getPlayerSprite().getX()-50,
                player.getPlayerSprite().getY()-50, player.getPlayerSprite().getWidth()*3,
                player.getPlayerSprite().getHeight()*2);
        }

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



    public void handlePlayerLevel(float delta){
        if(player.getSpeedyTime()>0f){
            player.setSpeedyTime(player.getSpeedyTime()+delta);
        }if (player.getSpeedyTime()>=10f){
            player.setSpeedyTime(0f);
        }
        if(player.getDamagerTime()>0f){
            player.setDamagerTime(player.getDamagerTime()+delta);
        }if (player.getDamagerTime()>=10f){
            player.setDamagerTime(0f);
        }
        Random random=new Random();
        int rand= random.nextInt(5);
        if (player.getXp()>=player.getLevel()*20){
            player.setLevel(player.getLevel()+1);
            if (GameData.isSfx())Main.getMain().playN();
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
            else if (rand==3){
                player.getAbilities().put("damager", player.getAbilities().get("damager")+1);
                player.setDamagerTime(delta);
                view.showDialog("", "damager ability", ()->{});
            }
            else {
                player.getAbilities().put("speedy", player.getAbilities().get("speedy")+1);
                player.setSpeedyTime(delta);
                view.showDialog("", "speedy ability", ()->{});
            };
        }

    }
    public void handlePlayerInput(){
        float speed=player.getSpeed();
        if(player.getSpeedyTime()>0f)speed*=2;
        if (GameData.isMoveWSAD()){
            if (Gdx.input.isKeyPressed(Input.Keys.W)){
                player.setPosY(player.getPosY() - speed);
                RunAnimation();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)){
                player.setPosX(player.getPosX() - speed);
                RunAnimation();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)){
                player.setPosY(player.getPosY() + speed);
                RunAnimation();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)){
                player.setPosX(player.getPosX() + speed);
                RunAnimation();
                player.getPlayerSprite().flip(true, false);
            }
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.Y)){
                player.setPosY(player.getPosY() - speed);
                RunAnimation();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)){
                player.setPosX(player.getPosX() - speed);
                RunAnimation();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.H)){
                player.setPosY(player.getPosY() + speed);
                RunAnimation();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.G)){
                player.setPosX(player.getPosX() + speed);
                RunAnimation();
                player.getPlayerSprite().flip(true, false);
            }
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

    public void RunAnimation(){
        Animation<Texture> animation = getTextureAnimation();
        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private Animation<Texture> getTextureAnimation() {
        Animation<Texture> animation=GameAssetManager.getGameAssetManager().getChar1Run();

        Player player1= GameData.getLoggedInPlayer();

        if (player1.getHero().equals("Dasher")) {
            animation = GameAssetManager.getGameAssetManager().getChar2Run();
        }
        if (player1.getHero().equals("Diamond")) {
            animation = GameAssetManager.getGameAssetManager().getChar3Run();
        }
        if (player1.getHero().equals("Lilith")) {
            animation = GameAssetManager.getGameAssetManager().getChar4Run();
        }
        if (player1.getHero().equals("Scarlet")) {
            animation = GameAssetManager.getGameAssetManager().getChar5Run();
        }
        return animation;
    }

    public Player getPlayer() {
        return player;
    }

}
