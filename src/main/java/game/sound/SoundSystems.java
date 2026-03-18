package game.sound;

import java.util.HashMap;
import java.util.Map;

import game.enemy.EnemyType;
import game.tower.TowerType;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;

public class SoundSystems {
    private Map<EnemyType, AudioClip>EnemySounds = new HashMap<>();
    private Map<TowerType, AudioClip>TowerSounds = new HashMap<>();
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
}
