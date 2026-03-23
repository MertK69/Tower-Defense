package game.engine;

import game.enemy.*;
import game.wave.*;
import game.path.*;
import game.render.RenderSystems;
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackFactory;
import game.sattack.SpecialAttackType;
import game.sound.SoundSystems;
import game.tower.*;
import game.animation.towerAnimationen.Fire;
import game.combat.*;
import game.economy.*;
import util.Vector2;
import java.util.*;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameEngine {
		private final List<Enemy>enemies = new ArrayList<>();
		private final List<Tower>towers = new ArrayList<>();
		private final List<Enemy>EnemiesToRemove = new ArrayList<>();
		private final List<Fire>Bullets = new ArrayList<>();
        private final List<SpecialAttack> sattackList = new ArrayList<>();
		private TowerFactory towerFactory = new TowerFactory();
		private EnemyFactory enemyFactory = new EnemyFactory();
        private SpecialAttackFactory sattackFactory = new SpecialAttackFactory();
		private WaveFactory waveFactory = new WaveFactory();
		private PathFactory pathFactory = new PathFactory();
        private SoundSystems soundSystem;
		private ActiveWave activeWave = null;
        private IntegerProperty waveProperty = new SimpleIntegerProperty(1);
        private IntegerProperty enemyProperty = new SimpleIntegerProperty();
        private BooleanProperty gameLost = new SimpleBooleanProperty();
        private BooleanProperty showTowerRanges = new SimpleBooleanProperty(false);
		private int waveNumber = 1;
        private IntegerProperty livesLeft = new SimpleIntegerProperty(10);
        private IntegerProperty waveEnemys = new SimpleIntegerProperty();
		private Path path = null;
	    private	Canvas canvas = new Canvas(1200, 800);
        private GraphicsContext gc = canvas.getGraphicsContext2D();
		private TowerSystems towerSystems = new TowerSystems();
        private CombatSystem combatSystem = new CombatSystem();
		private RenderSystems renderSystems = new RenderSystems(showTowerRanges);
		private EconomySystems economySystems = new EconomySystems();
		private Economy economy;

        public GameEngine(Pathtype pathtype, int waveNumber, int volume)
        {
            this.waveNumber = waveNumber;
            this.soundSystem = new SoundSystems((double) volume / 100);
            this.economy = new Economy(this.economySystems, pathtype);
            this.path = this.pathFactory.createPath(pathtype);
            this.waveProperty = new SimpleIntegerProperty(waveNumber);
        }

		public void update(double stepTime) 
		{
                if (livesLeft.getValue() == 0) this.gameLost.setValue(!gameLost.getValue());

				if ( activeWave == null ) activeWave = new ActiveWave(createWave());

                this.waveEnemys.setValue(this.activeWave.getEnemyCount());
		
				EnemyType spawnType = activeWave.update(stepTime);

				if (spawnType != null)
				{
						createEnemy(spawnType, path);
				}
                this.enemyProperty.set(enemies.size());

				if (activeWave.isFinished() && enemies.isEmpty())
				{
						activeWave = null;
						waveNumber++;
                        this.waveProperty.set(this.waveNumber);
				}
				EnemiesToRemove.clear();
				for (Enemy enemy : enemies)
				{
						enemy.update(stepTime);
                        if (enemy.isFinished())
                        {
                            livesLeft.setValue(livesLeft.getValue() - 1);
                        }

                        if (!enemy.isAlive())
                        {
                            soundSystem.playEnemyDyingSound(enemy.getType());
                        }

						if (!enemy.isAlive() || enemy.isFinished())
						{
								EnemiesToRemove.add(enemy);
								continue;
						}
				}
				enemies.removeAll(EnemiesToRemove);

				List<Tower>TowersToRemove = new ArrayList<>();
				for (Tower tower : towers)
				{
                        if (tower.get_fireSound() == true)
                        {
                            soundSystem.playTowerSound(tower.getType());
                            tower.set_fireSound();
                        }
						tower.update(stepTime);
				}
				List<Fire>bulletsToRemove = new ArrayList<>();
				for (Fire bullet : Bullets)
				{
						if (bullet.reachedTarget())
						{
							bulletsToRemove.add(bullet);
						}
                        bullet.updatePosition(stepTime);
				}
				Bullets.removeAll(bulletsToRemove);
                List<SpecialAttack>SAttacksToRemove = new ArrayList<>();
                for(SpecialAttack specialAttack : sattackList)
                {   
                    if (!specialAttack.playedSound()) soundSystem.playSpecialAttackSound(specialAttack.get_type());

                    if(specialAttack.reachedLastFrame())
                    {
                        SAttacksToRemove.add(specialAttack);
                    }
                    specialAttack.didPlayedSound();
                }
                sattackList.removeAll(SAttacksToRemove);

				combatSystem.update(stepTime, towers, enemies, Bullets, sattackList);
				economy.update(EnemiesToRemove, TowersToRemove);
		}

		public void render(double STEP)
		{
				renderSystems.renderBackground(gc, canvas);

				renderSystems.renderPath(gc, path);

				renderSystems.renderTower(gc, towers, STEP);	

				renderSystems.renderEnemies(gc, enemies, STEP, path);

				renderSystems.renderRemovedEnemies(gc, EnemiesToRemove);

				renderSystems.renderBullets(gc, Bullets);

                renderSystems.renderSpecialAttacks(gc, sattackList, STEP);
		}

		public void buyTower(TowerType type, Vector2 position)
		{
				towers.add(towerFactory.create_tower(type, position));	
		}

        public void createSAttack(SpecialAttackType type, Vector2 position)
        {
                sattackList.add(sattackFactory.create_sattack(type, position));
        }

		public void createEnemy(EnemyType type, Path path)
		{
				enemies.add(enemyFactory.create_enemy(type, path));
		}

		public Wave createWave()
		{
				return waveFactory.create_wave(waveNumber);		
		}

		public Canvas getCanvas()
		{
				return canvas;
		}

		public void handleBuyRequest(TowerType type, Vector2 position)
		{
				towerSystems.handleBuyRequest(economy, towers, type, position);	
		}

        public void handleSpecialAttack(Vector2 Position ,SpecialAttackType attackType)
        {
            if (economy.getMoney() >= attackType.get_Price())
            {
                createSAttack(attackType, Position);
                combatSystem.handleSpecialAttack(this.enemies, Position, attackType); 
                economy.specialEffectsBought(attackType);
            }
        }

        public IntegerProperty get_MoneyProperty()
        {
            return this.economy.Money_Property();
        }

        public IntegerProperty get_waveProperty()
        {
            return this.waveProperty;
        }

        public IntegerProperty get_enemyProperty()
        {
            return this.enemyProperty;
        }

        public BooleanProperty get_gameLostProperty()
        {
            return this.gameLost;
        }

        public IntegerProperty get_livesLeftProperty()
        {
            return this.livesLeft;
        }

        public IntegerProperty get_waveEnemyProperty()
        {
            return this.waveEnemys;
        }

        public BooleanProperty get_showTowerRangesProperty()
        {
            return this.showTowerRanges;
        }
}
