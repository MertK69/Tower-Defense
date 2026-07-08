package game.placement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import game.path.Path;
import game.path.PathFactory;
import game.path.Pathtype;
import game.tower.Tower;
import game.tower.TowerType;
import util.Vector2;

class PlacementSystemsTest {

    private final PathFactory pathFactory = new PathFactory();

    @Test
    public void isValidPlacement_returnsFalse_whenTooCloseToExistingTower()
    {
        // arrange
        PlacementSystems placementSystems = new PlacementSystems();
        Path path = pathFactory.createPath(Pathtype.EASY);
        Tower existingTower = new Tower(TowerType.BASIC, new Vector2(900, 700));
        List<Tower> towers = new ArrayList<>(List.of(existingTower));
        Vector2 candidate = new Vector2(910, 700);

        // act
        boolean valid = placementSystems.isValidPlacement(towers, path, candidate);

        // assert
        assertFalse(valid, "Position zu nah an einem bestehenden Turm sollte ungültig sein.");
    }

    @Test
    public void isValidPlacement_returnsFalse_whenNearPath()
    {
        // arrange
        PlacementSystems placementSystems = new PlacementSystems();
        Path path = pathFactory.createPath(Pathtype.EASY);
        List<Tower> towers = new ArrayList<>();
        // EASY path's first segment runs from (0,55) to (75,55)
        Vector2 candidate = new Vector2(30, 60);

        // act
        boolean valid = placementSystems.isValidPlacement(towers, path, candidate);

        // assert
        assertFalse(valid, "Position zu nah am Gegnerpfad sollte ungültig sein.");
    }

    @Test
    public void isValidPlacement_returnsTrue_whenFarFromTowersAndPath()
    {
        // arrange
        PlacementSystems placementSystems = new PlacementSystems();
        Path path = pathFactory.createPath(Pathtype.EASY);
        Tower existingTower = new Tower(TowerType.BASIC, new Vector2(900, 700));
        List<Tower> towers = new ArrayList<>(List.of(existingTower));
        // Far from the existing tower and below every EASY path segment (max y = 515)
        Vector2 candidate = new Vector2(200, 700);

        // act
        boolean valid = placementSystems.isValidPlacement(towers, path, candidate);

        // assert
        assertTrue(valid, "Position fernab von Türmen und Pfad sollte gültig sein.");
    }

    @Test
    public void isValidPlacement_returnsFalse_whenBothOverlapAndPathViolated()
    {
        // arrange
        PlacementSystems placementSystems = new PlacementSystems();
        Path path = pathFactory.createPath(Pathtype.EASY);
        Tower existingTower = new Tower(TowerType.BASIC, new Vector2(20, 55));
        List<Tower> towers = new ArrayList<>(List.of(existingTower));
        // On top of the existing tower AND on the path itself
        Vector2 candidate = new Vector2(25, 55);

        // act
        boolean valid = placementSystems.isValidPlacement(towers, path, candidate);

        // assert
        assertFalse(valid, "Position, die sowohl Turm-Überlappung als auch Pfadnähe verletzt, sollte ungültig sein.");
    }
}
