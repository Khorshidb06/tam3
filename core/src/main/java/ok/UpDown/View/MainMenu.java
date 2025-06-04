package ok.UpDown.View;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.MainMenuController;
import ok.UpDown.Model.GameData;
import ok.UpDown.Model.Player;

public class MainMenu implements Screen {
    private Stage stage;
    private final TextButton playButton;
    private final TextButton logout;
    private final Label gameTitle;
    public Table table;
    private final MainMenuController controller;
    private Skin skin;
    private final SelectBox<String> chooseMenu;
    Player player= GameData.getLoggedInPlayer();

    public MainMenu(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin=skin;
        this.logout=new TextButton("Logout" ,skin);
        this.playButton = new TextButton("play", skin);
        this.gameTitle = new Label("Choose next menu", skin);
        this.table = new Table();
        this.chooseMenu = new SelectBox<>(skin);
        Array<String> menus = new Array<>();
        menus.addAll("Settings", "Profile Menu", "Hint Menu", "ScoreBoard", "PreGame Menu");
        chooseMenu.setItems(menus);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table=new Table();
        table.setFillParent(true);
        table.center();

        Table avatarTable = new Table();
        avatarTable.left().padBottom(30);

        Texture avatarTexture = player.getAvatar();
        Image avatarImage = new Image(avatarTexture);
        avatarImage.setSize(64, 64);

        Label nameLabel = new Label("Name: " + player.getUserName(), skin);
        Label scoreLabel = new Label("Score: " + player.getXp(), skin);

        Table infoTable = new Table();
        infoTable.add(nameLabel).left().row();
        infoTable.add(scoreLabel).left();

        avatarTable.add(avatarImage).padRight(10);
        avatarTable.add(infoTable);

        table.add(avatarTable).left().colspan(2).row();

        table.row().pad(10, 0, 10, 0);
        table.add(gameTitle).padBottom(40).colspan(2).row();

        table.row().pad(10, 0, 10, 0);
        table.add(chooseMenu).width(600).padBottom(50).colspan(2).row();

        table.row().pad(10, 0, 10, 0);
        table.add(playButton).width(150).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(logout).width(150).colspan(2);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleMainMenuButtons();
            }
        });
        logout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleLogout();
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

    public TextButton getPlayButton() {
        return playButton;
    }

    public SelectBox<String> getChooseMenu() {
        return chooseMenu;
    }
}
