package game.economy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.tower.Tower;
import game.tower.TowerType;
import util.Vector2;

class EconomySystemsTest {

    @Test
    void withdrawMoneyFromUpgrade_returnsTowerUpgradeCost() {
        // arrange
        EconomySystems economySystems = new EconomySystems();
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));

        // act
        int cost = economySystems.withdrawMoneyFromUpgrade(tower);

        // assert
        assertEquals(tower.getUpgradeCost(), cost);
    }

    @Test
    void withdrawMoneyFromUpgrade_reflectsCurrentLevelNotJustBaseCost() {
        // arrange
        EconomySystems economySystems = new EconomySystems();
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        tower.upgrade(); // now level 2, higher upgrade cost

        // act
        int cost = economySystems.withdrawMoneyFromUpgrade(tower);

        // assert
        assertEquals(tower.getUpgradeCost(), cost);
    }
}
