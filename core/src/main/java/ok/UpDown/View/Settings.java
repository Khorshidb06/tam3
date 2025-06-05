package ok.UpDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.HintController;
import ok.UpDown.Controller.SettingController;
import ok.UpDown.Main;

import javax.swing.event.ChangeEvent;

public class Settings implements Screen {
    private final SettingController controller;
    private final Label menuTitle;
    private final CheckBox reload;
    private Stage stage;
    private Slider volumeSlider;
    public Table table;
    private final TextButton button;
    private final SelectBox<String> chooseMusic;
    private Skin skin;

    public Settings(SettingController controller, Skin skin) {
        this.controller = controller;
        this.menuTitle = new Label("Setting Menu", skin,"title");
        volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(Main.getMain().getVolume());
        this.skin=skin;
        this.reload=new CheckBox("Reload", skin);
        this.chooseMusic = new SelectBox<>(skin);
        this.button = new TextButton("go back", skin);
        Array<String> menus = new Array<>();
        menus.addAll("Pirates of the Caribbean Metal", "Simulacra", "arthur-vyncke-cherry-metal", "Film");
        chooseMusic.setItems(menus);
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table=new Table();
        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(chooseMusic).width(600).padBottom(50);
        table.row().pad(10, 0, 10, 0);
        table.add(new Label("Volume", skin));
        table.row().pad(10, 0, 10, 0);
        table.add(volumeSlider).width(400);
        table.add(reload).width(300);
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                Main.getMain().updateVolume(volume);
            }
        });

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleButton();
            }
        });
        table.row().pad(10, 0, 10, 0);
        table.add(button).width(200).padBottom(50);
        chooseMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedMusic = chooseMusic.getSelected();

                if (selectedMusic != null) {
                    Main.getMain().changeMusic(selectedMusic);
                }
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

    public CheckBox getReload() {
        return reload;
    }
}
