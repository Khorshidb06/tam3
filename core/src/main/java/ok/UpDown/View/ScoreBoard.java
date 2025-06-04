package ok.UpDown.View;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ok.UpDown.Controller.HintController;
import ok.UpDown.Controller.ScoreBoardController;

public class ScoreBoard implements Screen {
    private final ScoreBoardController controller;
    private Skin skin;


    public ScoreBoard(ScoreBoardController controller,Skin skin) {
        this.controller = controller;
        this.skin=skin;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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
