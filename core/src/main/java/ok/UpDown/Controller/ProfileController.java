package ok.UpDown.Controller;

import ok.UpDown.Main;
import ok.UpDown.Model.GameData;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.Model.Player;
import ok.UpDown.Model.PlayerStorage;
import ok.UpDown.View.MainMenu;
import ok.UpDown.View.Profile;

public class ProfileController {
    private Profile view;
    Player player1= GameData.getLoggedInPlayer();

    public void setView(Profile view) {
        this.view = view;
    }

    public void handlePassButton(String newPass){
        if (!isValidPassword(newPass)) {
            view.showDialog("", "Oops! You want hackers to find this?", () -> {});
            return;
        }
        player1.setPassword(newPass);
        PlayerStorage.savePlayers(GameData.allPlayers);
        view.showDialog("", "Password changed successfully", () -> {});

    }

    public void handleUserButton(String newName){
        for (Player player : GameData.allPlayers) {
            if (player.getUserName().equals(newName)) {
                view.showDialog("", "Oops! Username already taken.", () -> {});
                return;
            }
        }
        player1.setUserName(newName);
        PlayerStorage.savePlayers(GameData.allPlayers);
        view.showDialog("", "UserName changed successfully", () -> {});
    }

    public void handleButton(){
        if (view!=null){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }


    public static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasDigit = false, hasUpper = false, hasSpecial = false;
        String specialChars = "@%$#&*()_";

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (specialChars.indexOf(c) >= 0) hasSpecial = true;
        }

        return hasDigit && hasUpper && hasSpecial;
    }
}
