package ok.UpDown.Controller;

import ok.UpDown.Main;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.Model.GameData;
import ok.UpDown.View.GameView;
import ok.UpDown.View.MainMenu;
import ok.UpDown.View.Settings;

public class SettingController {
    private Settings view;

    public void setView(Settings view) {
        this.view = view;
    }

    public void handleButton(){
        if (view!=null){
            if (view.getReload().isChecked()) GameData.setAutoReload(true);
            else GameData.setAutoReload(false);
            if (view.getSfx().isChecked())GameData.setSfx(true);
            else GameData.setSfx(false);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
