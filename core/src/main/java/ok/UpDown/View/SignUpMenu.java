package ok.UpDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.SignupController;
import ok.UpDown.Main;
import ok.UpDown.Model.SecurityQuest;

import java.util.ArrayList;
import java.util.Set;

public class SignUpMenu implements Screen {
    private Stage stage;
    private final TextButton register;
    private final TextButton login;
    private final Label title;
    private final TextField userName;
    private final TextField password;
    private final TextField securityAns;
    private final Skin skin;
    private final SelectBox<String> selectQuest;
    private final SignupController controller;
    private final Table table;

    public SignUpMenu(Skin skin, SignupController controller) {
        this.skin = skin;
        this.selectQuest = new SelectBox<>(skin);
        this.controller = controller;
        this.title = new Label("20 Minutes Until Down!", skin,"title");
        this.userName = new TextField("", skin);
        this.securityAns = new TextField("", skin);
        this.password = new TextField("", skin);
        this.password.setPasswordCharacter('*');
        this.password.setPasswordMode(true);
        this.register = new TextButton("Register", skin);
        this.login = new TextButton("Login", skin);
        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Array<String> securityQuests=new Array<>();
        securityQuests.add(SecurityQuest.FIRST_VIDEO_GAME.getQuest());
        securityQuests.add(SecurityQuest.CHILDHOOD_CITY.getQuest());
        securityQuests.add(SecurityQuest.CHILDHOOD_DREAM_JOB.getQuest());
        securityQuests.add(SecurityQuest.FAVORITE_CARTOON_CHARACTER.getQuest());
        securityQuests.add(SecurityQuest.FIRST_BEST_FRIEND.getQuest());
        securityQuests.add(SecurityQuest.FIRST_BOOK_READ.getQuest());
        securityQuests.add(SecurityQuest.MOTHER_BIRTH_CITY.getQuest());

        table.setFillParent(true);
        table.center();

        table.add(title).padBottom(40).colspan(2).row();
        table.add(userName).width(500).padBottom(20).colspan(2).row();
        table.add(password).width(500).padBottom(20).colspan(2).row();
        table.add(securityAns).width(500).padBottom(20).colspan(2).row();
        table.add(selectQuest).width(500).padBottom(20).colspan(2).row();
        table.add(register).width(240).padRight(20);
        table.add(login).width(240);


        selectQuest.setItems(securityQuests);
        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleRegister();
            }
        });

        login.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleLogin();
            }
        });

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    public TextField getUserName() { return userName; }
    public TextField getPassword() { return password; }

    public TextField getSecurityAns() {
        return securityAns;
    }

    public SelectBox<String> getSelectQuest() {
        return selectQuest;
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

