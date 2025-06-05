package ok.UpDown.Controller;

import ok.UpDown.Main;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.View.MainMenu;

public class HintController {
    public void backToPrevious() {

        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

    }
}
