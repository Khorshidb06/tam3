package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private final Texture smgTexture = new Texture(GameAssetManager.getGameAssetManager().getSmg());
    private Sprite smgSprite = new Sprite(smgTexture);
    private int ammo=30;
    private final WeaponTypes weaponTypes;

    public Weapon( WeaponTypes weaponTypes){
        //this.smgTexture = //TODO;
        this.weaponTypes = weaponTypes;
        smgSprite.setX((float) Gdx.graphics.getWidth() / 2 );
        smgSprite.setY((float) Gdx.graphics.getHeight() / 2);
        smgSprite.setSize(50,50);
    }

    public Sprite getSmgSprite() {
        return smgSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public WeaponTypes getWeaponTypes() {
        return weaponTypes;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
}
