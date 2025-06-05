package ok.UpDown.Controller;

import ok.UpDown.Main;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.Model.GameData;
import ok.UpDown.Model.Player;
import ok.UpDown.View.MainMenu;
import ok.UpDown.View.Profile;
import ok.UpDown.View.ScoreBoard;

public class ScoreBoardController {
    private ScoreBoard view;
    Player player1= GameData.getLoggedInPlayer();

    public void setView(ScoreBoard view) {
        this.view = view;
    }
    public void handleButton(){
        if (view!=null){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
