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
        private BooleanProperty showTowerRanges = new SimpleBooleanProperty(false);
		private int waveNumber = 1;
        private IntegerProperty waveEnemys = new SimpleIntegerProperty();
        private final RoundLifecycle roundLifecycle = new RoundLifecycle();
        private final Pathtype pathtype;
		private Path path = null;
		private TowerType placementPreviewType = null;
		private Vector2 placementPreviewPosition = null;
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
            this.pathtype = pathtype;
            this.soundSystem = new SoundSystems((double) volume / 100);
            this.economy = new Economy(this.economySystems, pathtype);
            this.path = this.pathFactory.createPath(pathtype);
            this.waveProperty = new SimpleIntegerProperty(waveNumber);
        }

		public void update(double stepTime)
		{
                if (roundLifecycle.checkAndHandleGameLost()) return;

                if (!roundLifecycle.isOnBreak())
                {
                    advanceWave(stepTime);
                }
                else
                {
                    roundLifecycle.advanceBreak(stepTime);
                }

				updateEnemies(stepTime);
				updateTowers(stepTime);
				updateBullets(stepTime);
				updateSpecialAttacks(stepTime);

				combatSystem.update(stepTime, towers, enemies, Bullets, sattackList);
				List<Tower>TowersToRemove = new ArrayList<>();
				economy.update(EnemiesToRemove, TowersToRemove);
		}

		// Spawns enemies for the current wave and starts the post-wave break once it is finished.
		private void advanceWave(double stepTime)
		{
			if (activeWave == null) activeWave = new ActiveWave(createWave());

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
					roundLifecycle.startBreak();
			}
		}

		private void updateEnemies(double stepTime)
		{
				EnemiesToRemove.clear();
				for (Enemy enemy : enemies)
				{
						enemy.update(stepTime);
                        if (enemy.isFinished())
                        {
                            roundLifecycle.loseLife();
                        }

                        if (!enemy.isAlive())
                        {
                            soundSystem.playEnemyDyingSound(enemy.getType());
                        }

						if (!enemy.isAlive() || enemy.isFinished())
						{
								EnemiesToRemove.add(enemy);
						}
				}
				enemies.removeAll(EnemiesToRemove);
		}

		private void updateTowers(double stepTime)
		{
				for (Tower tower : towers)
				{
                        if (tower.get_fireSound())
                        {
                            soundSystem.playTowerSound(tower.getType());
                            tower.set_fireSound();
                        }
						tower.update(stepTime);
				}
		}

		private void updateBullets(double stepTime)
		{
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
		}

		private void updateSpecialAttacks(double stepTime)
		{
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
		}

		public void render(double STEP)
		{
				renderSystems.renderBackground(gc, canvas);

				renderSystems.renderPath(gc, path);

				renderSystems.renderTower(gc, towers, STEP);

				if (isPlacingTower())
				{
						renderSystems.renderPlacementPreview(gc, placementPreviewType, placementPreviewPosition, isPlacementPreviewValid());
				}

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

		public boolean handleBuyRequest(TowerType type, Vector2 position)
		{
				return towerSystems.handleBuyRequest(economy, towers, path, type, position);
		}

		public void startPlacementPreview(TowerType type)
		{
				this.placementPreviewType = type;
				this.placementPreviewPosition = null;
		}

		public void updatePlacementPreviewPosition(Vector2 position)
		{
				this.placementPreviewPosition = position;
		}

		public void cancelPlacementPreview()
		{
				this.placementPreviewType = null;
				this.placementPreviewPosition = null;
		}

		public boolean isPlacingTower()
		{
				return this.placementPreviewType != null && this.placementPreviewPosition != null;
		}

		public TowerType getPlacementPreviewType()
		{
				return this.placementPreviewType;
		}

		public Vector2 getPlacementPreviewPosition()
		{
				return this.placementPreviewPosition;
		}

		public boolean isPlacementPreviewValid()
		{
				return towerSystems.isValidPlacement(towers, path, placementPreviewPosition);
		}

		public boolean confirmPlacementPreview()
		{
				boolean placed = handleBuyRequest(placementPreviewType, placementPreviewPosition);
				if (placed)
				{
						cancelPlacementPreview();
				}
				return placed;
		}

        public void skipBreak()
        {
            roundLifecycle.skipBreak();
        }

        public void resetGame()
        {
            this.waveNumber = 1;
            this.waveProperty.set(1);
            this.towers.clear();
            this.enemies.clear();
            this.EnemiesToRemove.clear();
            this.Bullets.clear();
            this.sattackList.clear();
            this.enemyProperty.set(0);
            this.waveEnemys.set(0);
            this.activeWave = null;
            this.roundLifecycle.reset();
            this.economy.reset(this.pathtype);
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
            return this.roundLifecycle.gameLostProperty();
        }

        public IntegerProperty get_livesLeftProperty()
        {
            return this.roundLifecycle.livesLeftProperty();
        }

        public IntegerProperty get_waveEnemyProperty()
        {
            return this.waveEnemys;
        }

        public BooleanProperty get_onBreakProperty()
        {
            return this.roundLifecycle.onBreakProperty();
        }

        public IntegerProperty get_breakSecondsLeftProperty()
        {
            return this.roundLifecycle.breakSecondsLeftProperty();
        }

        public BooleanProperty get_showTowerRangesProperty()
        {
            return this.showTowerRanges;
        }
}
