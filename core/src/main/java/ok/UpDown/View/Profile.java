package ok.UpDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.ProfileController;
import ok.UpDown.Main;
import ok.UpDown.Model.FileUtils;
import ok.UpDown.Model.GameData;

import java.io.File;

public class Profile implements Screen {
    private Stage stage;
    private final ProfileController controller;
    private Skin skin;
    private final TextButton button;
    private final TextField userName;
    private final TextField password;
    private final TextButton chaneUser;
    private final TextButton chanePassword;
    private final Table table=new Table();
    private final SelectBox<String> avatar;
    private final TextButton file1;
    private final TextButton goodBye;




    public Profile(ProfileController controller, Skin skin) {
        this.controller = controller;
        this.chaneUser=new TextButton("Change UserName", skin);
        this.userName = new TextField("", skin);
        this.button = new TextButton("go back", skin);
        this.password = new TextField("", skin);
        this.chanePassword=new TextButton("Change PassWord", skin);
        this.avatar=new SelectBox<>(skin);
        this.skin=skin;
        this.file1 =new TextButton("choose File", skin);
        this.goodBye=new TextButton("Delete Account", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        Array<String> avatars=new Array<>();
        avatars.add("Lilith");
        avatars.add("Dasher");
        avatars.add("Diamond");
        avatars.add("Luna");
        avatars.add("Raven");
        avatars.add("Scarlett");
        avatars.add("Abby");
        avatar.setItems(avatars);
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        table.center();
        table.add(new Label("Profile Menu", skin, "title")).row();
        table.add(password).width(500).padBottom(20).row();
        table.add(chanePassword).width(400).row();
        table.add(userName).width(500).padBottom(20).row();
        table.add(chaneUser).width(400).row();
        table.add(avatar).row();
        table.add(file1).width(400).row();
        table.add(goodBye).width(400).row();

        chanePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handlePassButton(password.getText());
            }
        });

        chaneUser.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleUserButton(userName.getText());
            }
        });

        goodBye.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleGoodbyeButton();
            }
        });

        file1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                File file = FileUtils.chooseImageFile();
                if (file != null) {
                    Pixmap pixmap = new Pixmap(Gdx.files.absolute(file.getAbsolutePath()));
                    GameData.getLoggedInPlayer().setAvatar(new Texture(pixmap));
                    pixmap.dispose();
                }
            }
        });


        avatar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedPic = avatar.getSelected();

                if (selectedPic != null) {
                    controller.changeAvatar(selectedPic);
                }
            }
        });

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleButton();
            }
        });
        table.row().pad(10, 0, 10, 0);
        table.add(button).width(600).padBottom(50);

        controller.setView(this);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
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

    public void showDialog(String title, String message, Runnable onOk) {
        com.badlogic.gdx.scenes.scene2d.ui.Dialog dialog = new Dialog(title, skin) {
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
