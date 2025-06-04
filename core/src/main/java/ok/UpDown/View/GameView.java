package ok.UpDown.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import java.util.Random;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private Player player = GameData.getLoggedInPlayer();
    private Skin skin;
    Stage pauseStage = new Stage(new ScreenViewport());

    private Label ammoLabel;
    private Label healthLabel;
    private Label timeLabel;
    private Label killLabel;
    private Label levelLabel;
    private Label xpLabel;
    private Label ability1;
    private Label ability2;
    private Label ability3;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
    }

    private void createPauseMenu() {

        Table pauseTable = new Table();
        pauseTable.setFillParent(true);
        pauseTable.center();

        Label pauseLabel = new Label("Game Paused", skin);
        TextButton resumeButton = new TextButton("Resume", skin);
        TextButton quitButton = new TextButton("Quit", skin);

        ability1 = new Label("Vitality: " + player.getAbilities().get("vitality"), skin);
        ability2 = new Label("Procrese: " + player.getAbilities().get("procrease"), skin);
        ability3 = new Label("Ammocrease: " + player.getAbilities().get("ammocrease"), skin);

        pauseTable.add(ability1).pad(10).row();
        pauseTable.add(ability2).pad(10).row();
        pauseTable.add(ability3).pad(10).row();
        pauseTable.add(pauseLabel).pad(10);
        pauseTable.row();
        pauseTable.add(resumeButton).pad(10).width(200);
        pauseTable.row();
        pauseTable.add(quitButton).pad(10).width(200);

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
                Main.getMain().dispose();
                Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

            }
        });
    }


    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
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

        table.add(ammoLabel).pad(10);
        table.add(healthLabel).pad(10);
        table.add(timeLabel).pad(10).row();
        table.add(killLabel).pad(10);
        table.add(levelLabel).pad(10);
        table.add(xpLabel).pad(10);

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

        if (GameData.isIsPaused()) {
            ability1.setText("Vitality: " + player.getAbilities().get("vitality"));
            ability2.setText("Procrese: " + player.getAbilities().get("procrease"));
            ability3.setText("Ammocrease: " + player.getAbilities().get("ammocrease"));

            ScreenUtils.clear(0, 0, 0, 1);
            pauseStage.act(delta);
            pauseStage.draw();
            return;
        }
        ammoLabel.setText("Ammo: " + player.getWeapon().getAmmo());
        healthLabel.setText("Health: " + player.getPlayerHealth());
        timeLabel.setText("Time: " + (int) (GameData.getTime() - GameData.getPassedTime()));
        killLabel.setText("Kill: " + player.getKill());
        levelLabel.setText("Level: " + player.getLevel());
        xpLabel.setText("XP: " + player.getXp());

        if (GameData.isIsFinished()) return;

        GameData.setLastSpawn(GameData.getLastSpawn() + delta);
        GameData.setLastSpawn2(GameData.getLastSpawn2() + delta);

        GameData.setPassedTime(GameData.getPassedTime() + delta);
        if (GameData.getPassedTime() >= GameData.getTime()) {
            GameData.setIsFinished(true);
            Main.getMain().setScreen(new PreGame(
                new PreGameController(),
                GameAssetManager.getGameAssetManager().getSkin()
            ));
        }
        Random random = new Random();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            GameData.setReloadWeaponTime(GameData.getReloadWeaponTime() + delta);
        }
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
