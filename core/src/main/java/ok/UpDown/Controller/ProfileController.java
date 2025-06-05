package ok.UpDown.Controller;

import com.badlogic.gdx.utils.Array;
import ok.UpDown.Main;
import ok.UpDown.Model.GameData;
import ok.UpDown.Model.GameAssetManager;
import ok.UpDown.Model.Player;
import ok.UpDown.Model.PlayerStorage;
import ok.UpDown.View.MainMenu;
import ok.UpDown.View.Profile;

public class ProfileController {
    private Profile view;
    Player player1 = GameData.getLoggedInPlayer();

    public void setView(Profile view) {
        this.view = view;
    }

    public void handlePassButton(String newPass) {
        if (!isValidPassword(newPass)) {
            view.showDialog("", "Oops! You want hackers to find this?", () -> {
            });
            return;
        }
        player1.setPassword(newPass);
        PlayerStorage.savePlayers(GameData.allPlayers);
        view.showDialog("", "Password changed successfully", () -> {
        });

    }

    public void handleUserButton(String newName) {
        for (Player player : GameData.allPlayers) {
            if (player.getUserName().equals(newName)) {
                view.showDialog("", "Oops! Username already taken.", () -> {
                });
                return;
            }
        }
        player1.setUserName(newName);
        PlayerStorage.savePlayers(GameData.allPlayers);
        view.showDialog("", "UserName changed successfully", () -> {
        });
    }

    public void handleGoodbyeButton() {
        Array<Player> newPlayers = new Array<>();

        for (Player p : GameData.allPlayers) {
            if (!p.getUserName().equals(player1.getUserName())) {
                newPlayers.add(p);
            }
        }
        GameData.allPlayers = newPlayers;
        GameData.setLoggedInPlayer(null);
        PlayerStorage.savePlayers(GameData.allPlayers);
        handleButton();

    }

    public void handleButton() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void changeAvatar(String avatar) {
        if (avatar.equals("Abby")) player1.setAvatar(GameAssetManager.getGameAssetManager().getAbbyPortrait());
        if (avatar.equals("Dasher")) player1.setAvatar(GameAssetManager.getGameAssetManager().getDasherPortrait());
        if (avatar.equals("Diamond")) player1.setAvatar(GameAssetManager.getGameAssetManager().getDiamondPortrait());
        if (avatar.equals("Lilith")) player1.setAvatar(GameAssetManager.getGameAssetManager().getLilithPortrait());
        if (avatar.equals("Luna")) player1.setAvatar(GameAssetManager.getGameAssetManager().getLunaPortrait());
        if (avatar.equals("Raven")) player1.setAvatar(GameAssetManager.getGameAssetManager().getRavenPortrait());
        if (avatar.equals("Scarlett")) player1.setAvatar(GameAssetManager.getGameAssetManager().getScarlettPortrait());
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
