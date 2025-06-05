package ok.UpDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ok.UpDown.Controller.HintController;
import ok.UpDown.Controller.ScoreBoardController;
import ok.UpDown.Main;
import ok.UpDown.Model.GameData;
import ok.UpDown.Model.Player;

public class ScoreBoard implements Screen {
    private final ScoreBoardController controller;
    private Skin skin;
    private Stage stage;
    private final TextButton button;
    private final SelectBox<String> selectMode;
    private Table table;


    public ScoreBoard(ScoreBoardController controller,Skin skin) {
        this.controller = controller;
        this.skin=skin;
        this.selectMode=new SelectBox<>(skin);
        this.table=new Table(skin);
        this.button = new TextButton("go back", skin);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Array<String> modes = new Array<>();
        modes.add("sort by kills");
        modes.add("sort by username");
        modes.add("sort by score");
        modes.add("sort by timeAlive");

        selectMode.setItems(modes);
        controller.setView(this);

        selectMode.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                updateScoreTable(selectMode.getSelected());
            }
        });

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleButton();
            }
        });

        table.setFillParent(true);
        table.top().pad(10);
        table.add(selectMode).colspan(3).padBottom(10).row();
        table.add(button).colspan(3).padBottom(20).row();

        stage.addActor(table);

        updateScoreTable(selectMode.getSelected());
    }

    private void updateScoreTable(String mode) {
        table.clearChildren();
        table.add(selectMode).colspan(3).padBottom(10).row();
        table.add(button).colspan(3).padBottom(20).row();

        table.add("Username", "default").pad(5);
        table.add("Kills", "default").pad(5);
        table.add("TimeAlive", "default").pad(5);
        table.add("Score", "default").pad(5).row();

        Array<Player> players = GameData.allPlayers;

        switch (mode) {
            case "sort by kills":
                players.sort((a, b) -> Integer.compare(b.getKill(), a.getKill()));
                break;
            case "sort by username":
                players.sort((a, b) -> a.getUserName().compareToIgnoreCase(b.getUserName()));
                break;
            case "sort by timeAlive":
                players.sort((a, b) -> Float.compare(b.getTimeAlive(), a.getTimeAlive()));
                break;
            case "sort by score":
                players.sort((a, b) -> {
                    float scoreA = a.getKill() * a.getTimeAlive()/60;
                    float scoreB = b.getKill() * b.getTimeAlive()/60;
                    return Float.compare(scoreB, scoreA);
                });
                break;
        }

        for (int i = 0; i < players.size; i++) {
            Player p = players.get(i);

            Color rowColor;
            if (i == 0) rowColor = Color.GOLD;
            else if (i == 1) rowColor = Color.RED;
            else rowColor = Color.WHITE;

            Label.LabelStyle style = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
            style.fontColor = rowColor;

            Label nameLabel = new Label(p.getUserName(), style);
            Label killLabel = new Label(String.valueOf(p.getKill()), style);
            Label timeLabel = new Label(String.format("%.1f", p.getTimeAlive()), style);
            Label scoreLabel = new Label(String.format("%.1f", p.getTimeAlive() * p.getKill() / 60), style);

            if (p.getUserName().equals(GameData.getLoggedInPlayer().getUserName())) {
                Action blink = Actions.forever(
                    Actions.sequence(
                        Actions.fadeOut(0.5f),
                        Actions.fadeIn(0.5f)
                    )
                );
                nameLabel.addAction(blink);
            }

            table.add(nameLabel).pad(5);
            table.add(killLabel).pad(5);
            table.add(timeLabel).pad(5);
            table.add(scoreLabel).pad(5).row();
        }

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
}
