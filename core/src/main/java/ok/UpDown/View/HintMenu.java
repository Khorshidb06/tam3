package ok.UpDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.HintController;
import com.badlogic.gdx.utils.ScreenUtils;

public class HintMenu implements Screen {
    private final HintController controller;
    private final Skin skin;
    private Stage stage;

    public HintMenu(HintController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.pad(20);
        stage.addActor(rootTable);

        Label titleLabel = new Label("Game Hints", skin, "title");

        Table heroTable = new Table();
        heroTable.top();
        heroTable.add(new Label("Heroes", skin, "default")).padBottom(10).row();
        heroTable.add(new Label("- Dasher => HP=2, speed=10", skin)).left().row();
        heroTable.add(new Label("- Shana => HP=4, speed=4", skin)).left().row();
        heroTable.add(new Label("- Lilith => HP=5, speed=3", skin)).left().row();


        Table cheatTable = new Table();
        cheatTable.top();
        cheatTable.add(new Label("Cheat Codes", skin, "default")).padBottom(10).row();
        cheatTable.add(new Label("- key I: adds player level", skin)).left().row();
        cheatTable.add(new Label("- key L: adds player HP", skin)).left().row();
        cheatTable.add(new Label("- key T: reduces time of the game", skin)).left().row();


        Table controlTable = new Table();
        controlTable.top();
        controlTable.add(new Label("Controls", skin, "default")).padBottom(10).row();
        controlTable.add(new Label("- W/A/S/D: Move", skin)).left().row();
        controlTable.add(new Label("- Mouse: Aim", skin)).left().row();
        controlTable.add(new Label("- Left click: Shoot", skin)).left().row();
        controlTable.add(new Label("- P: Pause", skin)).left().row();
        controlTable.add(new Label("- R: reload gun", skin)).left().row();

        Table abilityTable = new Table();
        abilityTable.top();
        abilityTable.add(new Label("Abilities", skin, "default")).padBottom(10).row();
        abilityTable.add(new Label("- VITALITY: Increases max HP by 1", skin)).left().row();
        abilityTable.add(new Label("- DAMAGER: +25% weapon damage for 10 seconds", skin)).left().row();
        abilityTable.add(new Label("- PROCREASE: Adds 1 extra projectile", skin)).left().row();
        abilityTable.add(new Label("- AMOCREASE: +5 to max ammo capacity", skin)).left().row();
        abilityTable.add(new Label("- SPEEDY: Doubles movement speed for 10 seconds", skin)).left().row();


        rootTable.add(titleLabel).colspan(3).padBottom(30).center().row();
        rootTable.add(heroTable).expand().top().padRight(30);
        rootTable.add(cheatTable).expand().top().padRight(30);
        rootTable.add(controlTable).expand().top().padRight(30);
        rootTable.add(abilityTable).expand().top();

        TextButton backButton = new TextButton("Back", skin);
        rootTable.row();
        rootTable.add(backButton).colspan(4).padTop(30).center();

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.backToPrevious();
            }
        });
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
}
