package rocket.gameeco.structure;

import rocket.objcontrol.LiveGameObj;

public class FactoryHealth implements LiveGameObj {
    private int armor;
    private int structure;
    private boolean destroyed;

    private final int armorMax;
    private final int structureMax;

    private final double armorDamageToStructureRatio = 0.1;
    private final double structureDamageToArmorRatio = 0.01;

    private int structureFixRate;

    public FactoryHealth(){
        this(100,1000);
    }

    public FactoryHealth(int armor, int structure) {
        this.armor = armor;
        this.structure = structure;
        this.armorMax = armor;
        this.structureMax = structure;
        structureFixRate = 1;
        destroyed = false;
    }

    public int getArmor() {
        return armor;
    }

    public int getStructure() {
        return structure;
    }

    // structure can only fix itself


    public int getStructureFixRate() {
        return structureFixRate;
    }

    public void setStructureFixRate(int structureFixRate) {
        this.structureFixRate = structureFixRate;
    }

    /**
     *
     * @param armor
     * @return redundant armor
     */
    public int fixArmor(int armor) {
        if (this.armor + armor > armorMax) {
            int result =  this.armor + armor - armorMax;
            this.armor = armorMax;
            return result;
        }
        this.armor += armor;
        return 0;
    }

    public void hit(int armorHit, int structureHit) {
        if (armor - armorHit > 0) {
            if (armor - (int)(structureHit * structureDamageToArmorRatio) > 0) {
                armor -= (int)(structureHit * structureDamageToArmorRatio);
            } else {
                armor = 0;
            }
        } else if (armor == 0){
            structure -= structureHit + (int)(armorHit*armorDamageToStructureRatio);
        } else {
            armor = 0;
            structure -= structureHit;
        }
        if (structure < 0) {
            this.structure = 0;
            this.destroyed = true;
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void update() {
        if (structure + structureFixRate > structureMax) {
            return;
        }
        structure+=structureFixRate;
    }
}
