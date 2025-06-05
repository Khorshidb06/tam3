package ok.UpDown.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.GameController;
import ok.UpDown.Controller.MainMenuController;
import ok.UpDown.Controller.PreGameController;
import ok.UpDown.Main;
import ok.UpDown.Model.*;
import ok.UpDown.Model.Tree;

import java.util.ArrayList;
import java.util.Random;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private Player player = GameData.getLoggedInPlayer();
    private Skin skin;
    private Stage pauseStage = new Stage(new ScreenViewport());
    private Stage endStage = new Stage(new ScreenViewport());
    private Label endMode;

    private Label ammoLabel;
    private Label healthLabel;
    private Label timeLabel;
    private Label killLabel;
    private Label timeLabelG;
    private Label killLabelG;
    private Label levelLabel;
    private Label xpLabel;
    private Label nameLabel;
    private Label scoreLabel;
    private Label scoreLabelG;
    private Label ability1;
    private Label ability2;
    private Label ability3;
    private Label ability4;
    private Label ability5;
    public ArrayList<HitEffect> hitEffects = new ArrayList<>();
    ProgressBar progressBar;



    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
    }

    private void createPauseMenu() {
        Table pauseTable = new Table();
        pauseTable.setFillParent(true);
        pauseTable.center();

        Label pauseLabel = new Label("Game Paused", skin, "title");
        TextButton resumeButton = new TextButton("Resume", skin);
        TextButton quitButton = new TextButton("Quit", skin);

        Table playerInfoTable = new Table();
        nameLabel = new Label(player.getUserName(), skin);
        ability1 = new Label("Vitality: " + player.getAbilities().get("vitality"), skin);
        ability2 = new Label("Procrese: " + player.getAbilities().get("procrease"), skin);
        ability3 = new Label("Ammocrease: " + player.getAbilities().get("ammocrease"), skin);
        ability4=new Label("Damager: 0", skin);
        ability5=new Label("Speedy: 0", skin);

        playerInfoTable.add(nameLabel).left().row();
        playerInfoTable.add(ability1).left().row();
        playerInfoTable.add(ability2).left().row();
        playerInfoTable.add(ability3).left().row();
        playerInfoTable.add(ability4).left().row();
        playerInfoTable.add(ability5).left().row();


        Table cheatTable = new Table();
        cheatTable.top();
        cheatTable.add(new Label("Cheat Codes", skin, "default")).padBottom(10).row();
        cheatTable.add(new Label("- key I: adds player level", skin)).left().row();
        cheatTable.add(new Label("- key L: adds player HP", skin)).left().row();
        cheatTable.add(new Label("- key T: reduces 1 minute of the time", skin)).left().row();
        cheatTable.add(new Label("- key B: go to boss fight", skin)).left().row();
        cheatTable.add(new Label("- key X: increase XP by 5", skin)).left().row();


        Table infoRowTable = new Table();
        infoRowTable.add(playerInfoTable).pad(20).top();
        infoRowTable.add(cheatTable).pad(20).top();

        pauseTable.add(pauseLabel).colspan(2).pad(10).row();
        pauseTable.add(infoRowTable).colspan(2).row();
        pauseTable.add(resumeButton).colspan(2).pad(10).width(200).row();
        pauseTable.add(quitButton).colspan(2).pad(10).width(200);

        pauseStage.addActor(pauseTable);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameData.setIsPaused(false);
                Gdx.input.setInputProcessor(new InputMultiplexer(stage, GameView.this));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayerStorage.savePlayers(GameData.allPlayers);
                GameData.setIsFinished(true);
                endMode = new Label("Dead", skin);
            }
        });
    }


    private void createEndMenu() {

        Table endTable = new Table();
        endTable.setFillParent(true);
        endTable.center();

        Label endLabel = new Label("Game Ended", skin, "title");
        TextButton backButton = new TextButton("Go to main", skin);
        endTable.add(endLabel).pad(10).row();
        endTable.add(nameLabel).pad(10).row();
        endTable.add(killLabel).pad(10).row();
        endTable.add(timeLabel).pad(10).row();
        endTable.add(scoreLabel).pad(10).row();
        endTable.add(endMode).pad(10).row();

        endTable.row();
        endTable.add(backButton).pad(10).width(300);

        endStage.addActor(endTable);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameData.setIsFinished(false);
                PlayerStorage.savePlayers(GameData.allPlayers);
                Main.getMain().dispose();
                Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

            }
        });

    }


    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        progressBar = new ProgressBar(0f, 1f, 0.01f, false, skin);
        progressBar.setValue((float) player.getXp() / player.getLevel()*20);
        progressBar.setSize(200, 20);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        createPauseMenu();

        ammoLabel = new Label("Ammo: 0", skin);
        healthLabel = new Label("Health: 100", skin);
        timeLabel = new Label("Time: 0", skin);
        killLabel = new Label("Kill: 0", skin);
        levelLabel = new Label("", skin);
        xpLabel = new Label("", skin);
        scoreLabel = new Label("", skin);
        endMode=new Label("",skin);
        timeLabelG=new Label("", skin);
        killLabelG=new Label("",skin);
        scoreLabelG=new Label("", skin);

        table.add(timeLabelG).pad(10);
        table.add(killLabelG).pad(10);
        table.add(scoreLabelG).pad(10);
        table.add(progressBar).pad(10).row();

        table.add(timeLabel).pad(10);
        table.add(killLabel).pad(10);
        table.add(scoreLabel).pad(10).row();
        table.add(ammoLabel).pad(10);
        table.add(healthLabel).pad(10);
        table.add(levelLabel).pad(10);
        table.add(xpLabel).pad(10);
        createEndMenu();


        stage.addActor(table);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randY = random.nextInt(2300);
            int randX = random.nextInt(3400);
            GameData.getAllEnemies().add(new Tree(-randX + 800, -randY + 200));
        }
    }

    @Override
    public void render(float delta) {

        progressBar.setValue((float) (player.getXp()*100) / (player.getLevel()*20)/100);
        if (GameData.isIsPaused()) {
            ability1.setText("Vitality: " + player.getAbilities().get("vitality"));
            ability2.setText("Procrese: " + player.getAbilities().get("procrease"));
            ability3.setText("Ammocrease: " + player.getAbilities().get("ammocrease"));
            ability4.setText("Damager: " + player.getAbilities().get("damager"));
            ability5.setText("Speedy: " + player.getAbilities().get("speedy"));

            ScreenUtils.clear(0, 0, 0, 1);
            pauseStage.act(delta);
            pauseStage.draw();
            return;
        }
        ammoLabel.setText("Ammo: " + player.getWeapon().getAmmo());
        healthLabel.setText("Health: " + player.getPlayerHealth());
        player.setTimeAlive(GameData.getPassedTime());
        timeLabel.setText("Time: " + (int) (GameData.getTime() - player.getTimeAlive()));
        killLabel.setText("Kill: " + player.getKill());
        timeLabelG.setText("Time: " + (int) (GameData.getTime() - player.getTimeAlive()));
        killLabelG.setText("Kill: " + player.getKill());
        levelLabel.setText("Level: " + player.getLevel());
        xpLabel.setText("XP: " + player.getXp());
        scoreLabel.setText("Score: " + GameData.getPassedTime() * player.getKill() / 60);
        scoreLabelG.setText("Score: " + String.format("%.1f", GameData.getPassedTime() * player.getKill() / 60f));


        if (GameData.isIsFinished()) {
            nameLabel.setText(player.getUserName());
            killLabel.setText("Kill: " + player.getKill());
            timeLabel.setText("Time: " + (int)player.getTimeAlive());
            scoreLabel.setText("Score: " + String.format("%.1f", GameData.getPassedTime() * player.getKill() / 60f));

            Gdx.input.setInputProcessor(endStage);
            ScreenUtils.clear(0, 0, 0, 1);
            endStage.act(delta);
            endStage.draw();
            return;
        }


        GameData.setLastSpawn(GameData.getLastSpawn() + delta);
        GameData.setLastSpawn2(GameData.getLastSpawn2() + delta);

        GameData.setPassedTime(GameData.getPassedTime() + delta);
        if (GameData.getPassedTime() >= GameData.getTime()) {
            GameData.setIsFinished(true);
            endMode.setText("You won the Game!");
        }
        if (player.getPlayerHealth() <= 0) {
            GameData.setIsFinished(true);
            endMode.setText("Dead");
        }

        Random random = new Random();

        if (player.getWeapon().getAmmo() <= 0 && GameData.isAutoReload()) {
            GameData.setReloadWeaponTime(GameData.getReloadWeaponTime() + delta);
        }
        if (GameData.getReloadWeaponTime() >= player.getWeapon().getWeaponTypes().getTimeReload()) {
            player.getWeapon().setAmmo(player.getWeapon().getWeaponTypes().getAmmoMax());
            GameData.setReloadWeaponTime(0);
        }
        if (GameData.getLastSpawn() >= 3f) {
            GameData.setLastSpawn(0);
            for (int i = 0; i < (GameData.getPassedTime() / 30f); i++) {
                int randY = random.nextInt(2000);
                int randX = random.nextInt(3000);
                GameData.getAllTentacles().add(new Enemy(-randX + 800, -randY + 200, GameAssetManager.getGameAssetManager().getTentacle_Monster0(), 25));
            }
        }
        if (GameData.getPassedTime() > GameData.getTime() / 2 && !GameData.isBoss()){
            GameData.setBoss(true);
            int randY = random.nextInt(2000);
            int randX = random.nextInt(3000);
            GameData.getAllEyeBat().add(new Enemy(-randX + 800, -randY + 200, GameAssetManager.getGameAssetManager().getBoss0(), 400));
        }

        if (GameData.getPassedTime() > GameData.getTime() / 4) {
            if (GameData.getLastSpawn2() >= 10f) {
                GameData.setLastSpawn2(0);
                for (int i = 0; i < (-GameData.getTime() + GameData.getPassedTime() * 4 + 30) / 30; i++) {
                    int randY = random.nextInt(2000);
                    int randX = random.nextInt(3000);
                    GameData.getAllEyeBat().add(new Enemy(-randX + 800, -randY + 200, GameAssetManager.getGameAssetManager().getEyeBat_Monster0(), 50));
                }
            }
        }


        ScreenUtils.clear(0, 0, 0, 1);

        Main.getBatch().begin();

        controller.updateGame(delta);
        for (HitEffect effect : hitEffects) {
            effect.update(delta);
            effect.draw(Main.getBatch());
        }
        hitEffects.removeIf(HitEffect::isFinished);

        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.R) {
            GameData.setAutoReload(true);
        }
        if (keycode==Input.Keys.B){
            GameData.setPassedTime(GameData.getTime()/2);
        }
        if (keycode == Input.Keys.T){
            GameData.setPassedTime(GameData.getPassedTime()+60);
        }
        if (keycode == Input.Keys.L){
            player.setPlayerHealth(player.getPlayerHealth()+1);
        }
        if (keycode ==Input.Keys.I){
            player.setXp(player.getLevel()*20+1);
        }
        if (keycode==Input.Keys.X){
            player.setXp(player.getXp()+5);
        }
        if (keycode == Input.Keys.P) {
            GameData.setIsPaused(!GameData.isIsPaused());

            if (GameData.isIsPaused()) {
                Gdx.input.setInputProcessor(pauseStage);
            } else {
                Gdx.input.setInputProcessor(new InputMultiplexer(stage, this));
            }
            return true;
        }
        return false;
    }

    public void showDialog(String title, String message, Runnable onOk) {
        Dialog dialog = new Dialog(title, skin) {
            @Override
            protected void result(Object object) {
                if (Boolean.TRUE.equals(object)) {
                    onOk.run();
                }
            }
        };
        dialog.text(message);
        dialog.button("OK", true);
        dialog.show(stage);
    }

}
