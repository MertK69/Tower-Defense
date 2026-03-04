package game.combat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import game.enemy.*;
import game.enemy.EnemyType;
import game.tower.Tower;
import game.tower.TowerType;
import game.path.*;
import game.animation.towerAnimationen.*;
import util.Vector2; 

class CombatSystemTest {

    @Test
    public void update_CombatSystem_Test()
    {
        // arrange
        PathFabric pathFabric = new PathFabric();
        CombatSystem combatSystem = new CombatSystem();
        Tower tower = new Tower(TowerType.BASIC, new Vector2(165,395));
        List<Tower>towerlist = new ArrayList<>(List.of(tower));
        Enemy enemy = new Enemy(EnemyType.Leutnant, pathFabric.createPath(Pathtype.EASY));
        double initialHealth = enemy.getCurrent_hp();
        enemy.__set_Position__(160,390);
        List<Enemy>enemylist = new ArrayList<>(List.of(enemy));
        List<Fire>Bullets = new ArrayList<>();
        double dt = 1.0 / 60.0;
        
        // act
        combatSystem.update(dt, towerlist, enemylist, Bullets);
        
        // assert
        assertFalse(Bullets.isEmpty(), "Es sollte ein Projektil erzeugt worden sein.");
        assertTrue(enemy.getCurrent_hp() < initialHealth, "Der Gegner sollte Schaden erlitten haben.");
        assertEquals(1, Bullets.size());

    }




}
