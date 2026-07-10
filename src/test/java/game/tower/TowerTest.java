package game.tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import util.Vector2;

class TowerTest {

    @Test
    void newTower_startsAtLevel1WithBaseCostAsInvestedGold() {
        // arrange + act
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));

        // assert
        assertEquals(1, tower.getLevel());
        assertEquals(TowerType.BASIC.price(), tower.getBaseCost());
        assertEquals(TowerType.BASIC.price(), tower.getTotalInvestedGold());
        assertFalse(tower.isMaxLevel());
    }

    @Test
    void getUpgradeCost_isHalfBaseCostTimesCurrentLevel() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));

        // act + assert (level 1: baseCost * 0.5 * 1)
        assertEquals((int) (TowerType.BASIC.price() * 0.5), tower.getUpgradeCost());
    }

    @Test
    void upgrade_incrementsLevelAndAccumulatesInvestedGold() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        int baseCost = TowerType.BASIC.price();
        int firstUpgradeCost = tower.getUpgradeCost();

        // act
        tower.upgrade();

        // assert
        assertEquals(2, tower.getLevel());
        assertEquals(baseCost + firstUpgradeCost, tower.getTotalInvestedGold());
    }

    @Test
    void upgrade_costGrowsWithLevel() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        int level1Cost = tower.getUpgradeCost();

        // act
        tower.upgrade(); // now level 2

        // assert
        int level2Cost = tower.getUpgradeCost();
        assertTrue(level2Cost > level1Cost, "Upgrade cost must grow with level");
    }

    @Test
    void upgrade_isNoOpAtMaxLevel() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        tower.upgrade();
        tower.upgrade();
        assertTrue(tower.isMaxLevel());
        int investedAtMax = tower.getTotalInvestedGold();

        // act
        tower.upgrade();

        // assert
        assertEquals(Tower.MAX_LEVEL, tower.getLevel());
        assertEquals(investedAtMax, tower.getTotalInvestedGold());
    }

    @Test
    void upgrade_boostsDamageAndRangeByStatMultiplier() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        double baseDamage = TowerType.BASIC.damage();
        double baseRange = TowerType.BASIC.reichweite();

        // act
        tower.upgrade(); // level 2 -> multiplier 1.25

        // assert
        assertEquals(baseDamage * 1.25, tower.getDamage(), 0.0001);
        assertEquals(baseRange * 1.25, tower.getReichweite(), 0.0001);
    }

    @Test
    void upgrade_reducesEffectiveCooldownSoUpgradedTowerFiresFaster() {
        // arrange
        Tower levelOne = new Tower(TowerType.BASIC, new Vector2(0, 0));
        Tower levelTwo = new Tower(TowerType.BASIC, new Vector2(0, 0));
        levelTwo.upgrade();

        levelOne.fire();
        levelTwo.fire();

        // act: advance time just past the upgraded (faster) cooldown, but still
        // within the un-upgraded cooldown window.
        double justPastFasterCooldown = (TowerType.BASIC.firerate() / 1.25) + 0.001;
        levelOne.update(justPastFasterCooldown);
        levelTwo.update(justPastFasterCooldown);

        // assert
        assertFalse(levelOne.canFire(), "Level 1 tower should still be on cooldown");
        assertTrue(levelTwo.canFire(), "Level 2 tower should have finished its faster cooldown");
    }

    @Test
    void getSellValue_atLevel1IsSeventyPercentOfBaseCost() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));

        // act + assert
        assertEquals((int) (TowerType.BASIC.price() * 0.7), tower.getSellValue());
    }

    @Test
    void getSellValue_includesUpgradeSpendAfterUpgrading() {
        // arrange
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));

        // act
        tower.upgrade();

        // assert
        int expected = (int) (tower.getTotalInvestedGold() * 0.7);
        assertEquals(expected, tower.getSellValue());
        assertTrue(tower.getSellValue() > (int) (TowerType.BASIC.price() * 0.7));
    }
}
