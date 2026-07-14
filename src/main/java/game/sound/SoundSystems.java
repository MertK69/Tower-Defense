package game.sound;

import java.util.HashMap;
import java.util.Map;

import game.enemy.EnemyType;
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackType;
import game.tower.TowerType;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;

public class SoundSystems {
    private Map<EnemyType, AudioClip>EnemySounds = new HashMap<>();
    private Map<TowerType, AudioClip>TowerSounds = new HashMap<>();
    private Map<SpecialAttackType, AudioClip>SpecialAttackSounds = new HashMap<>();
    private double volume;

    public SoundSystems(double volume)
    {
        this.volume = volume;
        init();
    }

    public void init()
    {
        this.TowerSounds.put(TowerType.BASIC, new AudioClip(getClass().getResource("/sounds/cannon.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.ADVANCED, new AudioClip(getClass().getResource("/sounds/cannon.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.EXPERT, new AudioClip(getClass().getResource("/sounds/cannon.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.RAYBASIC, new AudioClip(getClass().getResource("/sounds/raySound.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.RAYADVANCED, new AudioClip(getClass().getResource("/sounds/raySound.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.RAYEXPERT, new AudioClip(getClass().getResource("/sounds/raySound.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.ROCKETLAUNCHERBASIC, new AudioClip(getClass().getResource("/sounds/rocket-launch.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.ROCKETLAUNCHERADVANCED, new AudioClip(getClass().getResource("/sounds/rocket-launch.wav").toExternalForm()));
        this.TowerSounds.put(TowerType.ROCKETLAUNCHEREXPERT, new AudioClip(getClass().getResource("/sounds/rocket-launch.wav").toExternalForm()));

        for (Map.Entry<TowerType, AudioClip> sound : this.TowerSounds.entrySet())
        {
            sound.getValue().setVolume(this.volume);
        }
        this.EnemySounds.put(EnemyType.Matrose, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Kommodore, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Gefreiter, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Leutnant, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Kapitan, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Vizeadmiral, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Admiral, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        this.EnemySounds.put(EnemyType.Großadmiral, new AudioClip(getClass().getResource("/sounds/dying-enemy.wav").toExternalForm()));
        for (Map.Entry<EnemyType, AudioClip> sound : this.EnemySounds.entrySet())
        {
            sound.getValue().setVolume(this.volume);
        }
        this.SpecialAttackSounds.put(SpecialAttackType.BombAttack, new AudioClip(getClass().getResource("/sounds/rocket-explosion.wav").toExternalForm()));
        this.SpecialAttackSounds.put(SpecialAttackType.ElectricWave, new AudioClip(getClass().getResource("/sounds/electric-sound.wav").toExternalForm()));
    this.SpecialAttackSounds.put(SpecialAttackType.FireAttack, new AudioClip(getClass().getResource("/sounds/fire-breath.wav").toExternalForm()));

        for (Map.Entry<SpecialAttackType, AudioClip> sound : this.SpecialAttackSounds.entrySet())
        {
            sound.getValue().setVolume(this.volume);
        }
    }

    /**
     * Updates the SFX volume live (TT-5 AC-2). Mutates every already-loaded
     * {@link AudioClip} in all three maps so subsequent {@code play()} calls use the
     * new value, and stores it as the current volume for {@link #getVolume()}.
     */
    public void setVolume(double newVolume)
    {
        this.volume = newVolume;
        for (AudioClip clip : this.TowerSounds.values())         clip.setVolume(newVolume);
        for (AudioClip clip : this.EnemySounds.values())         clip.setVolume(newVolume);
        for (AudioClip clip : this.SpecialAttackSounds.values()) clip.setVolume(newVolume);
    }

    public double getVolume()
    {
        return this.volume;
    }

    public void playTowerSound(TowerType type)
    {
       try {
           if (this.TowerSounds.containsKey(type)){
            Platform.runLater(() -> {
            this.TowerSounds.get(type).play(); 
            });
        }
       } catch (Exception mediaException) {
            mediaException.printStackTrace();
        }
       
    }

    public void playEnemyDyingSound(EnemyType type)
    {
        try {
            if (this.EnemySounds.containsKey(type)){
                Platform.runLater(() -> {
                this.EnemySounds.get(type).play();
                });
            }
        } catch (Exception mediaException) {
            mediaException.printStackTrace();
            }
    }

    public void playSpecialAttackSound(SpecialAttackType type)
    {
        try {
            if (this.SpecialAttackSounds.containsKey(type)){
                Platform.runLater(() -> {
                    this.SpecialAttackSounds.get(type).play();
                });
            }
        }catch (Exception mediaException) {
                mediaException.printStackTrace();
        }
    }
}
