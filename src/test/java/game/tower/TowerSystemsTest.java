package game.tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import game.economy.Economy;
import game.economy.EconomySystems;
import game.path.Path;
import game.path.PathFactory;
import game.path.Pathtype;
import util.Vector2;

class TowerSystemsTest {

    private final PathFactory pathFactory = new PathFactory();

    @Test
    public void handleBuyRequest_returnsFalse_whenInsufficientFunds()
    {
        // arrange
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = new Economy(new EconomySystems(), Pathtype.HARD); // HARD: kein Startgeld gesetzt -> 0
        Path path = pathFactory.createPath(Pathtype.HARD);
        List<Tower> towers = new ArrayList<>();
        Vector2 position = new Vector2(600, 700);

        // act
        boolean result = towerSystems.handleBuyRequest(economy, towers, path, TowerType.BASIC, position);

        // assert
        assertFalse(result, "Kauf sollte bei unzureichendem Guthaben fehlschlagen.");
        assertTrue(towers.isEmpty(), "Bei fehlgeschlagenem Kauf darf kein Turm hinzugefügt werden.");
    }

    @Test
    public void handleBuyRequest_returnsFalse_whenPositionInvalid()
    {
        // arrange
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = new Economy(new EconomySystems(), Pathtype.EASY); // ausreichend Guthaben
        Path path = pathFactory.createPath(Pathtype.EASY);
        List<Tower> towers = new ArrayList<>();
        int moneyBefore = economy.getMoney();
        // Direkt auf dem Pfad (erstes Segment (0,55)-(75,55))
        Vector2 position = new Vector2(30, 55);

        // act
        boolean result = towerSystems.handleBuyRequest(economy, towers, path, TowerType.BASIC, position);

        // assert
        assertFalse(result, "Kauf an ungültiger Position sollte fehlschlagen.");
        assertTrue(towers.isEmpty(), "Bei ungültiger Position darf kein Turm hinzugefügt werden.");
        assertEquals(moneyBefore, economy.getMoney(), "Bei fehlgeschlagenem Kauf darf kein Geld abgezogen werden.");
    }

    @Test
    public void handleBuyRequest_returnsTrue_placesTowerAndDeductsMoney_whenValid()
    {
        // arrange
        TowerSystems towerSystems = new TowerSystems();
        Economy economy = new Economy(new EconomySystems(), Pathtype.EASY);
        Path path = pathFactory.createPath(Pathtype.EASY);
        List<Tower> towers = new ArrayList<>();
        int moneyBefore = economy.getMoney();
        // Weit entfernt von Pfad (max y = 515) und Türmen (Liste ist leer)
        Vector2 position = new Vector2(200, 700);

        // act
        boolean result = towerSystems.handleBuyRequest(economy, towers, path, TowerType.BASIC, position);

        // assert
        assertTrue(result, "Kauf an gültiger Position mit ausreichendem Guthaben sollte gelingen.");
        assertEquals(1, towers.size(), "Bei erfolgreichem Kauf sollte ein Turm hinzugefügt werden.");
        assertEquals(moneyBefore - TowerType.BASIC.price(), economy.getMoney(), "Der Turmpreis sollte vom Guthaben abgezogen werden.");
    }
}
