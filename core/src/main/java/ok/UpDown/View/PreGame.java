package ok.UpDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.PreGameController;
import ok.UpDown.Main;

import java.awt.*;

public class PreGame implements Screen {

    private Stage stage;
    private final Label gameTitle;
    private final TextButton playButton;
    private final SelectBox<String> selectHero;
    private final SelectBox<String> selectWeapon;
    private final SelectBox<Integer> selectTime;
    public Table table;
    private PreGameController controller;

    public PreGame(PreGameController controller, Skin skin) {
        this.gameTitle = new Label("Pregame Menu", skin);
        this.selectHero = new SelectBox<>(skin);
        this.selectWeapon=new SelectBox<>(skin);
        this.playButton = new TextButton("Start", skin);
        this.selectTime = new SelectBox<>(skin);
        this.table = new Table();
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Array<String> hero = new Array<>();

        hero.add("Dasher");
        hero.add("Diamond");
        hero.add("Lilith");
        hero.add("Scarlet");
        hero.add("Shana");

        Array<String> weapon = new Array<>();

        weapon.add("Revolver");
        weapon.add("ShutGun");
        weapon.add("SMG");

        Array<Integer> time=new Array<>();
        time.add(10);
        time.add(2);
        time.add(5);
        time.add(20);
        selectHero.setItems(hero);
        selectWeapon.setItems(weapon);
        selectTime.setItems(time);
        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(gameTitle);
        table.row().pad(10, 0 , 10 , 0);
        table.add(selectHero);
        table.row().pad(10, 0 , 10 , 0);
        table.add(selectWeapon);
        table.row().pad(10, 0 , 10 , 0);
        table.add(selectTime);
        table.row().pad(10, 0 , 10 , 0);
        table.add(playButton);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handlePreGameMenuButtons();
            }
        });
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
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

    public SelectBox<Integer> getSelectTime() {
        return selectTime;
    }

    public SelectBox<String> getSelectHero() {
        return selectHero;
    }

    public SelectBox<String> getSelectWeapon() {
        return selectWeapon;
    }
}
