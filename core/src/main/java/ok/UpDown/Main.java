package ok.UpDown;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ok.UpDown.Controller.SignupController;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.Model.GameData;
import ok.UpDown.Model.PlayerStorage;
import ok.UpDown.View.SignUpMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private float volume=0.5f;
    private Music backgroundMusic;

    @Override
    public void create() {
        main = this;
        GameData.allPlayers=PlayerStorage.loadPlayers();
        batch = new SpriteBatch();
        Pixmap pixmap = new Pixmap(Gdx.files.internal("Images_grouped_1/Sprite/T/T_Cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap, 0, 0));
        pixmap.dispose();
        backgroundMusic=Gdx.audio.newMusic(Gdx.files.internal("music/Pirates of the Caribbean Metal.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(volume);
        backgroundMusic.play();
        getMain().setScreen(new SignUpMenu( GameAssetManager.getGameAssetManager().getSkin(), new SignupController()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public void changeMusic(String filePath) {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
        }

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/"+filePath+".mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(this.volume);
        backgroundMusic.play();
    }


    public void updateVolume(float volume) {
        this.volume = volume;
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }

    public float getVolume() {
        return volume;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
