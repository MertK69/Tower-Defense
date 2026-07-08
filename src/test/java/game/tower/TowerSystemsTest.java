package game.tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import game.economy.Economy;
import game.economy.EconomySystems;
import game.path.Pathtype;
import util.Vector2;

class TowerSystemsTest {

    private Economy newEconomy(Pathtype pathtype) {
        return new Economy(new EconomySystems(), pathtype);
    }

    // --- findTowerAt (pure hit-testing seam, no GameEngine construction) ---

    @Test
    void findTowerAt_returnsTowerWithinRadius() {
        Tower tower = new Tower(TowerType.BASIC, new Vector2(100, 100));
        List<Tower> towers = List.of(tower);

        Tower found = TowerSystems.findTowerAt(towers, 105, 100, 18);

        assertSame(tower, found);
    }

    @Test
    void findTowerAt_returnsNullOutsideRadius() {
        Tower tower = new Tower(TowerType.BASIC, new Vector2(100, 100));
        List<Tower> towers = List.of(tower);

        Tower found = TowerSystems.findTowerAt(towers, 130, 100, 18);

        assertNull(found);
    }

    @Test
    void findTowerAt_exactlyOnRadiusBoundaryCountsAsHit() {
        Tower tower = new Tower(TowerType.BASIC, new Vector2(100, 100));
        List<Tower> towers = List.of(tower);

        Tower found = TowerSystems.findTowerAt(towers, 118, 100, 18); // distance == 18 exactly

        assertSame(tower, found);
    }

    @Test
    void findTowerAt_emptyListReturnsNull() {
        Tower found = TowerSystems.findTowerAt(List.of(), 0, 0, 18);

        assertNull(found);
    }

    // --- handleSellRequest ---

    @Test
    void handleSellRequest_addsTowerToPendingSoldOnce() {
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = newEconomy(Pathtype.EASY);
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        List<Tower> towers = new ArrayList<>(List.of(tower));
        List<Tower> pendingSoldTowers = new ArrayList<>();

        towerSystems.handleSellRequest(economy, towers, tower, pendingSoldTowers);

        assertEquals(1, pendingSoldTowers.size());
        assertTrue(pendingSoldTowers.contains(tower));
    }

    @Test
    void handleSellRequest_doesNotDoubleQueueSameFrame() {
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = newEconomy(Pathtype.EASY);
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        List<Tower> towers = new ArrayList<>(List.of(tower));
        List<Tower> pendingSoldTowers = new ArrayList<>();

        towerSystems.handleSellRequest(economy, towers, tower, pendingSoldTowers);
        towerSystems.handleSellRequest(economy, towers, tower, pendingSoldTowers); // rapid re-click

        assertEquals(1, pendingSoldTowers.size(), "Rapid re-sell must not double-queue the same tower");
    }

    @Test
    void handleSellRequest_ignoresTowerNotInActiveList() {
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = newEconomy(Pathtype.EASY);
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        List<Tower> towers = new ArrayList<>(); // tower already removed / stale selection
        List<Tower> pendingSoldTowers = new ArrayList<>();

        towerSystems.handleSellRequest(economy, towers, tower, pendingSoldTowers);

        assertTrue(pendingSoldTowers.isEmpty());
    }

    // --- handleUpgradeRequest ---

    @Test
    void handleUpgradeRequest_deductsGoldAndIncreasesLevelWhenAffordable() {
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = newEconomy(Pathtype.EASY); // plentiful starting gold
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        int moneyBefore = economy.getMoney();
        int upgradeCost = tower.getUpgradeCost();

        towerSystems.handleUpgradeRequest(economy, tower);

        assertEquals(2, tower.getLevel());
        assertEquals(moneyBefore - upgradeCost, economy.getMoney());
    }

    @Test
    void handleUpgradeRequest_noOpWhenGoldInsufficient() {
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = newEconomy(Pathtype.MEDIUM); // low starting gold (1000)
        Tower tower = new Tower(TowerType.ROCKETLAUNCHEREXPERT, new Vector2(0, 0)); // price 6500 -> upgradeCost 3250
        int moneyBefore = economy.getMoney();

        towerSystems.handleUpgradeRequest(economy, tower);

        assertEquals(1, tower.getLevel());
        assertEquals(moneyBefore, economy.getMoney());
    }

    @Test
    void handleUpgradeRequest_noOpAtMaxLevel() {
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = newEconomy(Pathtype.EASY);
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        tower.upgrade();
        tower.upgrade();
        assertTrue(tower.isMaxLevel());
        int moneyBeforeThirdAttempt = economy.getMoney();

        towerSystems.handleUpgradeRequest(economy, tower);

        assertEquals(Tower.MAX_LEVEL, tower.getLevel());
        assertEquals(moneyBeforeThirdAttempt, economy.getMoney());
    }
}
