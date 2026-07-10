package game.economy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.path.Pathtype;

class EconomyTest {

    @Test
    void resetEasySetsStartingMoneyTo80000() {
        Economy economy = new Economy(new EconomySystems(), Pathtype.EASY);

        economy.reset(Pathtype.EASY);

        assertEquals(80000, economy.getMoney());
    }

    @Test
    void resetMediumSetsStartingMoneyTo1000() {
        // Pre-existing constructor logic checks MEDIUM twice (750, then 1000) - the second
        // write wins, net effective starting money is 1000. reset() reproduces this exact
        // net effect via a shared helper, it does not introduce a new value.
        Economy economy = new Economy(new EconomySystems(), Pathtype.MEDIUM);

        economy.reset(Pathtype.MEDIUM);

        assertEquals(1000, economy.getMoney());
    }

    @Test
    void resetHardLeavesStartingMoneyAtZero() {
        // HARD is unhandled by the pre-existing constructor logic (no branch matches it),
        // so money falls through to the IntegerProperty default of 0. This is a documented
        // pre-existing gap (see plan.md Testing Strategy / Edge Cases), intentionally
        // replicated by reset() rather than fixed here.
        Economy economy = new Economy(new EconomySystems(), Pathtype.HARD);

        economy.reset(Pathtype.HARD);

        assertEquals(0, economy.getMoney());
    }

    @Test
    void resetImpossibleSetsStartingMoneyTo1500() {
        Economy economy = new Economy(new EconomySystems(), Pathtype.IMPOSSIBLE);

        economy.reset(Pathtype.IMPOSSIBLE);

        assertEquals(1500, economy.getMoney());
    }
}
