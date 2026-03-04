package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Vector2Test {

    @Test
    void direction_to_standardPositiveCoordinates() {
        // 1. Arrange (Vorbereiten)
        Vector2 start = new Vector2(2.0, 3.0);
        Vector2 waypoint = new Vector2(5.0, 7.0);

        // 2. Act (Ausführen)
        Vector2 result = start.direction_to(waypoint);

        // 3. Assert (Überprüfen)
        assertEquals(3.0, result.getX(), 0.0001, "X-Richtung ist falsch berechnet");
        assertEquals(4.0, result.getY(), 0.0001, "Y-Richtung ist falsch berechnet");
    }

    @Test
    void direction_to_standardNegativeCoordinates()
    {
        // 1. Arrange (Vorbereiten)
        Vector2 start = new Vector2(5.0, 7.0);
        Vector2 waypoint = new Vector2(2.0, 3.0);

        // 2. Act (Ausführen)
        Vector2 result = start.direction_to(waypoint);

        // 3. Assert (Überprüfen)
        assertEquals(-3.0, result.getX(), 0.0001, "X-Richtung ist falsch berechnet");
        assertEquals(-4.0, result.getY(), 0.0001, "Y-Richtung ist falsch berechnet");
    }
}
