package ok.UpDown.Model;

public enum WeaponTypes {
    revolver("Revolver", 1, 20, 6, 1),
    shutGun("ShutGun", 4, 10, 2,1),
    SMG("SMG", 1,8,24,2)
    ;


    private final String name;
    private int projectile;
    private final int damage;
    private int AmmoMax;
    private final int timeReload;

    WeaponTypes(String name, int projectile, int damage, int ammoMax, int timeReload) {
        this.name = name;
        this.projectile = projectile;
        this.damage = damage;
        AmmoMax = ammoMax;
        this.timeReload = timeReload;
    }

    public String getName() {
        return name;
    }

    public int getAmmoMax() {
        return AmmoMax;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getDamage() {
        return damage;
    }

    public int getTimeReload() {
        return timeReload;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }

    public void setAmmoMax(int ammoMax) {
        AmmoMax = ammoMax;
    }
}
