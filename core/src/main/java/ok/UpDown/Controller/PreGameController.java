package ok.UpDown.Controller;

import ok.UpDown.Main;
import ok.UpDown.Model.*;
import ok.UpDown.View.GameView;
import ok.UpDown.View.PreGame;

public class PreGameController {
    private PreGame view;
    private PreGameModel pregame;


    public void setView(PreGame view) {
        this.view = view;
        this.pregame = new PreGameModel();
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {
            Player player = GameData.getLoggedInPlayer();
            GameData.setTime(view.getSelectTime().getSelected() * 60f);
            player.setHero(view.getSelectHero().getSelected());
            if (player.getHero().equals("Dasher")) {
                player.setSpeed(10);
                player.setPlayerHealth(2);
            }
            if (player.getHero().equals("Shana")) {
                player.setSpeed(4);
                player.setPlayerHealth(4);
            }
            if (player.getHero().equals("Diamond")) {
                player.setSpeed(1);
                player.setPlayerHealth(7);
            }
            if (player.getHero().equals("Lilith")) {
                player.setSpeed(3);
                player.setPlayerHealth(5);
            }
            if (player.getHero().equals("Scarlet")) {
                player.setSpeed(5);
                player.setPlayerHealth(3);
            }

            if (view.getSelectWeapon().getSelected().equals("Revolver"))player.setWeapon(new Weapon(WeaponTypes.revolver));
            else if (view.getSelectWeapon().getSelected().equals("ShutGun"))player.setWeapon(new Weapon(WeaponTypes.shutGun));
            else player.setWeapon(new Weapon(WeaponTypes.SMG));
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

}
