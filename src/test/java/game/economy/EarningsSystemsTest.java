package game.economy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import game.tower.Tower;
import game.tower.TowerType;
import util.Vector2;

class EarningsSystemsTest {

    @Test
    void addMoneyFromSoldTowers_refundsSellValueNotFullPrice() {
        // arrange
        EarningsSystems earningsSystems = new EarningsSystems();
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        List<Tower> sold = new ArrayList<>(List.of(tower));

        // act
        int money = earningsSystems.addMoneyFromSoldTowers(sold);

        // assert
        assertEquals((int) (TowerType.BASIC.price() * 0.7), money);
        assertNotEquals(TowerType.BASIC.price(), money, "Full price must not be refunded (regression guard)");
    }

    @Test
    void addMoneyFromSoldTowers_includesUpgradeSpendInRefund() {
        // arrange
        EarningsSystems earningsSystems = new EarningsSystems();
        Tower tower = new Tower(TowerType.BASIC, new Vector2(0, 0));
        tower.upgrade();
        List<Tower> sold = new ArrayList<>(List.of(tower));

        // act
        int money = earningsSystems.addMoneyFromSoldTowers(sold);

        // assert
        assertEquals(tower.getSellValue(), money);
        assertTrue(money > (int) (TowerType.BASIC.price() * 0.7));
    }

    @Test
    void addMoneyFromSoldTowers_sumsRefundsAcrossMultipleTowers() {
        // arrange
        EarningsSystems earningsSystems = new EarningsSystems();
        Tower towerA = new Tower(TowerType.BASIC, new Vector2(0, 0));
        Tower towerB = new Tower(TowerType.ADVANCED, new Vector2(0, 0));
        List<Tower> sold = new ArrayList<>(List.of(towerA, towerB));

        // act
        int money = earningsSystems.addMoneyFromSoldTowers(sold);

        // assert
        assertEquals(towerA.getSellValue() + towerB.getSellValue(), money);
    }
}
