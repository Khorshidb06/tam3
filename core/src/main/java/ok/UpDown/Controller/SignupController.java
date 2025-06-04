package ok.UpDown.Controller;

import com.badlogic.gdx.graphics.Texture;
import ok.UpDown.Main;
import ok.UpDown.Model.*;
import ok.UpDown.View.MainMenu;
import ok.UpDown.View.SignUpMenu;

import java.util.Random;

public class SignupController {
    private SignUpMenu view;

    public void setView(SignUpMenu view) {
        this.view = view;
    }

    public Texture chooseAvatar(){
        Random random = new Random();
        int rand = random.nextInt(8);
        if (rand==0)return GameAssetManager.getGameAssetManager().getAbbyPortrait();
        if (rand==1)return GameAssetManager.getGameAssetManager().getDasherPortrait();
        if (rand==2)return GameAssetManager.getGameAssetManager().getDiamondPortrait();
        if (rand==3)return GameAssetManager.getGameAssetManager().getLilithPortrait();
        if (rand==4)return GameAssetManager.getGameAssetManager().getLunaPortrait();
        if (rand==5)return GameAssetManager.getGameAssetManager().getRavenPortrait();
        if (rand==6)return GameAssetManager.getGameAssetManager().getScarlettPortrait();
        return GameAssetManager.getGameAssetManager().getShanaPortrait();


    }
    public void handleRegister() {
        String username = view.getUserName().getText().trim();
        String password = view.getPassword().getText();
        String securityAns=view.getSecurityAns().getText();
        String securityQuest=view.getSelectQuest().getSelected();
        Texture texture=chooseAvatar();

        for (Player player : GameData.allPlayers) {
            if (player.getUserName().equals(username)) {
                view.showDialog("", "Oops! Username already taken.", () -> {});
                return;
            }
        }

        if (username.equals("Guest")) {
            view.showDialog("Welcome", "You are playing as a guest", () -> {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenu(
                    new MainMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()
               ));
            });

            Player newPlayer = new Player(username, password,0,0,securityQuest,securityAns, texture);
            GameData.allPlayers.add(newPlayer);
            GameData.setLoggedInPlayer(newPlayer);
            PlayerStorage.savePlayers(GameData.allPlayers);
            return;
        }

        else if (!isValidPassword(password)) {
            view.showDialog("", "Oops! You want hackers to find this?", () -> {});
            return;
        }

        else {
            Player newPlayer = new Player(username, password,0,0, securityQuest, securityAns,texture);
            GameData.allPlayers.add(newPlayer);
            GameData.setLoggedInPlayer(newPlayer);
            PlayerStorage.savePlayers(GameData.allPlayers);
        }

        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void handleLogin() {
        for (Player p : GameData.allPlayers) {
            System.out.println("Username: " + p.getUserName());
        }
        String username = view.getUserName().getText().trim();
        String password = view.getPassword().getText();
        Player person=null;
        for (Player player : GameData.allPlayers) {
            if (player.getUserName().equals(username)) {
                person=player;
            }
        }
        if (person==null){
            view.showDialog("", "Username doesn't exist.", () -> {});
            return;
        }

        else if(!person.getPassword().equals(password)){
            view.showDialog("", "Wrong password. ", ()->{});
            //TODO: add setNewPassword page and answer secQuest
            return;
        }

        GameData.setLoggedInPlayer(person);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

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

