package ok.UpDown.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final String character1_idle0 = "1/Idle_0.png";
    private final String character1_idle1 = "1/Idle_1.png";
    private final String character1_idle2 = "1/Idle_2.png";
    private final String character1_idle3 = "1/Idle_3.png";
    private final String character1_idle4 = "1/Idle_4.png";
    private final String character1_idle5 = "1/Idle_5.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Texture character1_idle3_tex = new Texture(character1_idle3);
    private final Texture character1_idle4_tex = new Texture(character1_idle4);
    private final Texture character1_idle5_tex = new Texture(character1_idle5);
    private final Texture character2_idle0_tex = new Texture("ssss/Dasher/idle/Idle_0 #8325.png");
    private final Texture character2_idle1_tex = new Texture("ssss/Dasher/idle/Idle_1 #8355.png");
    private final Texture character2_idle2_tex = new Texture("ssss/Dasher/idle/Idle_2 #8814.png");
    private final Texture character2_idle3_tex = new Texture("ssss/Dasher/idle/Idle_3 #8452.png");
    private final Texture character2_idle4_tex = new Texture("ssss/Dasher/idle/Idle_4 #8313.png");
    private final Texture character2_idle5_tex = new Texture("ssss/Dasher/idle/Idle_5 #8302.png");
    private final Texture character3_idle0_tex = new Texture("ssss/Diamond/idle/Idle_0 #8328.png");
    private final Texture character3_idle1_tex = new Texture("ssss/Diamond/idle/Idle_1 #8358.png");
    private final Texture character3_idle2_tex = new Texture("ssss/Diamond/idle/Idle_2 #8817.png");
    private final Texture character3_idle3_tex = new Texture("ssss/Diamond/idle/Idle_3 #8455.png");
    private final Texture character3_idle4_tex = new Texture("ssss/Diamond/idle/Idle_4 #8316.png");
    private final Texture character3_idle5_tex = new Texture("ssss/Diamond/idle/Idle_5 #8305.png");
    private final Texture character4_idle0_tex = new Texture("ssss/Lilith/idle/Idle_0 #8333.png");
    private final Texture character4_idle1_tex = new Texture("ssss/Lilith/idle/Idle_1 #8363.png");
    private final Texture character4_idle2_tex = new Texture("ssss/Lilith/idle/Idle_2 #8822.png");
    private final Texture character4_idle3_tex = new Texture("ssss/Lilith/idle/Idle_3 #8460.png");
    private final Texture character4_idle4_tex = new Texture("ssss/Lilith/idle/Idle_4 #8321.png");
    private final Texture character4_idle5_tex = new Texture("ssss/Lilith/idle/Idle_5 #8310.png");
    private final Texture character5_idle0_tex = new Texture("ssss/Scarlet/idle/Idle_0 #8327.png");
    private final Texture character5_idle1_tex = new Texture("ssss/Scarlet/idle/Idle_1 #8357.png");
    private final Texture character5_idle2_tex = new Texture("ssss/Scarlet/idle/Idle_2 #8816.png");
    private final Texture character5_idle3_tex = new Texture("ssss/Scarlet/idle/Idle_3 #8454.png");
    private final Texture character5_idle4_tex = new Texture("ssss/Scarlet/idle/Idle_4 #8315.png");
    private final Texture character5_idle5_tex = new Texture("ssss/Scarlet/idle/Idle_5 #8304.png");
    private final Animation<Texture> character1_idle_frames = new Animation<>(0.1f, character1_idle0_tex, character1_idle1_tex, character1_idle2_tex, character1_idle3_tex, character1_idle4_tex, character1_idle5_tex);
    private final Animation<Texture> character2_idle_frames = new Animation<>(0.1f, character2_idle0_tex, character2_idle1_tex, character2_idle2_tex, character2_idle3_tex, character2_idle4_tex, character2_idle5_tex);
    private final Animation<Texture> character3_idle_frames = new Animation<>(0.1f, character3_idle0_tex, character3_idle1_tex, character3_idle2_tex, character3_idle3_tex, character3_idle4_tex, character3_idle5_tex);
    private final Animation<Texture> character4_idle_frames = new Animation<>(0.1f, character4_idle0_tex, character4_idle1_tex, character4_idle2_tex, character4_idle3_tex, character4_idle4_tex, character4_idle5_tex);
    private final Animation<Texture> character5_idle_frames = new Animation<>(0.1f, character5_idle0_tex, character5_idle1_tex, character5_idle2_tex, character5_idle3_tex, character5_idle4_tex, character5_idle5_tex);


    private final Texture Tree_Monster0=new Texture("Images_grouped_1/Sprite/T/T_TreeMonster_0.png");
    private final Texture Tree_Monster1=new Texture("Images_grouped_1/Sprite/T/T_TreeMonster_1.png");
    private final Texture Tree_Monster2=new Texture("Images_grouped_1/Sprite/T/T_TreeMonster_2.png");
    private final Animation<Texture> Tree_Monster=new Animation<>(0.3f, Tree_Monster0,Tree_Monster1,Tree_Monster2);

    private final Texture Tentacle_Monster0=new Texture("Images_grouped_1/Sprite/T/T_TentacleEnemy_0.png");
    private final Texture Tentacle_Monster1=new Texture("Images_grouped_1/Sprite/T/T_TentacleEnemy_1.png");
    private final Texture Tentacle_Monster2=new Texture("Images_grouped_1/Sprite/T/T_TentacleEnemy_2.png");
    private final Texture Tentacle_Monster3=new Texture("Images_grouped_1/Sprite/T/T_TentacleEnemy_3.png");
    private final Animation<Texture> TentacleEnemy=new Animation<>(0.2f,Tentacle_Monster0,Tentacle_Monster1,Tentacle_Monster2,Tentacle_Monster3);

    private final Texture eyeBat_Monster0= new Texture("Images_grouped_1/Sprite/T/T_EyeBat_0.png");
    private final Texture eyeBat_Monster1= new Texture("Images_grouped_1/Sprite/T/T_EyeBat_1.png");
    private final Texture eyeBat_Monster2= new Texture("Images_grouped_1/Sprite/T/T_EyeBat_2.png");
    private final Texture eyeBat_Monster3= new Texture("Images_grouped_1/Sprite/T/T_EyeBat_3.png");
    private final Animation<Texture> EyeBatEnemy= new Animation<>(0.2f, eyeBat_Monster0,eyeBat_Monster1,eyeBat_Monster2,eyeBat_Monster3);

    private final Texture abbyPortrait=new Texture("Images_grouped_1/Sprite/T/T_Abby_Portrait.png");
    private final Texture dasherPortrait=new Texture("Images_grouped_1/Sprite/T/T_Dasher_Portrait.png");
    private final Texture lilithPortrait=new Texture("Images_grouped_1/Sprite/T/T_Lilith_Portrait.png");
    private final Texture diamondPortrait=new Texture("Images_grouped_1/Sprite/T/T_Diamond_Portrait.png");
    private final Texture lunaPortrait=new Texture("Images_grouped_1/Sprite/T/T_Luna_Portrait.png");
    private final Texture ravenPortrait=new Texture("Images_grouped_1/Sprite/T/T_Raven_Portrait.png");
    private final Texture scarlettPortrait=new Texture("Images_grouped_1/Sprite/T/T_Scarlett_Portrait.png");
    private final Texture shanaPortrait=new Texture("Images_grouped_1/Sprite/T/T_Shana_Portrait.png");

    private final Texture explosion0=new Texture("Images_grouped_1/Sprite/T/T_FireExplosionSmall_0.png");
    private final Texture explosion1=new Texture("Images_grouped_1/Sprite/T/T_FireExplosionSmall_1.png");
    private final Texture explosion2=new Texture("Images_grouped_1/Sprite/T/T_FireExplosionSmall_2.png");
    private final Texture explosion3=new Texture("Images_grouped_1/Sprite/T/T_FireExplosionSmall_3.png");
    private final Texture explosion4=new Texture("Images_grouped_1/Sprite/T/T_FireExplosionSmall_4.png");
    private final Animation<Texture> explosion=new Animation<>(0.2f, explosion0, explosion1,explosion2,explosion3,explosion4);

    private final Texture hit0=new Texture("Images_grouped_1/Sprite/T/T_HitMarkerFX_0.png");
    private final Texture hit1=new Texture("Images_grouped_1/Sprite/T/T_HitMarkerFX_1.png");
    private final Texture hit2=new Texture("Images_grouped_1/Sprite/T/T_HitMarkerFX_2.png");
    private final Animation<Texture> hit=new Animation<>(0.2f, hit0,hit1, hit2);

    private final Texture seed=new Texture("Images_grouped_1/Sprite/T/T_DevilDealPickup.png");

    Texture glowTexture=new Texture("Images_grouped_1/Sprite/kindpng_7434455.png");

    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

    private final String bullet = "bullet.png";


    private GameAssetManager(){

    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Animation<Texture> getCharacter1_idle_animation() {
        return character1_idle_frames;
    }

    public String getCharacter1_idle0(){
        return character1_idle0;
    }

    public Texture getSmgTexture(){
        return smgTexture;
    }

    public String getSmg(){
        return smg;
    }

    public String getBullet(){
        return bullet;
    }

    public Animation<Texture> getCharacter2_idle_frames() {
        return character2_idle_frames;
    }

    public Animation<Texture> getTree_Monster() {
        return Tree_Monster;
    }

    public Texture getTree_Monster0() {
        return Tree_Monster0;
    }

    public Texture getTentacle_Monster0() {
        return Tentacle_Monster0;
    }

    public Animation<Texture> getEyeBatEnemy() {
        return EyeBatEnemy;
    }

    public Animation<Texture> getTentacleEnemy() {
        return TentacleEnemy;
    }

    public Texture getEyeBat_Monster0() {
        return eyeBat_Monster0;
    }

    public Texture getSeed() {
        return seed;
    }

    public Texture getAbbyPortrait() {
        return abbyPortrait;
    }

    public Texture getDasherPortrait() {
        return dasherPortrait;
    }

    public Texture getDiamondPortrait() {
        return diamondPortrait;
    }

    public Texture getLilithPortrait() {
        return lilithPortrait;
    }

    public Texture getLunaPortrait() {
        return lunaPortrait;
    }

    public Texture getRavenPortrait() {
        return ravenPortrait;
    }

    public Texture getScarlettPortrait() {
        return scarlettPortrait;
    }

    public Texture getShanaPortrait() {
        return shanaPortrait;
    }

    public Animation<Texture> getCharacter3_idle_frames() {
        return character3_idle_frames;
    }

    public Animation<Texture> getCharacter4_idle_frames() {
        return character4_idle_frames;
    }

    public Animation<Texture> getExplosion() {
        return explosion;
    }

    public Animation<Texture> getHit() {
        return hit;
    }

    public Animation<Texture> getCharacter5_idle_frames() {
        return character5_idle_frames;
    }

    public Texture getGlowTexture() {
        return glowTexture;
    }
}
