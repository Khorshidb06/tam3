package ok.UpDown.Controller;

import ok.UpDown.Model.GameData;
import ok.UpDown.Model.Player;
import ok.UpDown.Model.Weapon;
import ok.UpDown.Model.WeaponTypes;
import ok.UpDown.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private EnemyController enemyController;


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(enemyController);
        worldController = new WorldController(playerController);
        enemyController=new EnemyController(GameData.getAllEnemies(),playerController, GameData.getAllTentacles(), GameData.getAllEyeBat());
        weaponController = new WeaponController(playerController.getPlayer().getWeapon(), playerController, enemyController);
    }

    public void updateGame(float delta) {
        if (view != null) {
            worldController.update();
            playerController.update();
            playerController.setView(this.view);
            weaponController.update();
            enemyController.updateEnemies(delta);
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
