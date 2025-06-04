package ok.UpDown.Controller;

import ok.UpDown.Main;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.View.*;

public class MainMenuController {
    private MainMenu view;

    public void setView(MainMenu view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {

        if (view != null) {
            switch (view.getChooseMenu().getSelected()){
                case "Settings":{
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new Settings(new SettingController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                case "Profile Menu":{
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new Profile(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                case  "Hint Menu":{
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new HintMenu(new HintController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                case "ScoreBoard":{
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new ScoreBoard(new ScoreBoardController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                case "PreGame Menu":{
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new PreGame(new PreGameController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            }

        }
    }

    public void handleLogout(){
        if (view!=null){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SignUpMenu(GameAssetManager.getGameAssetManager().getSkin(),new SignupController()));
        }
    }
}
