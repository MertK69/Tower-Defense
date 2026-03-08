package game.render.animationStorage;
import java.util.*;
import game.tower.*;
import javafx.scene.image.Image;
import game.animation.enemyAnimationen.LoadSystems;
public class TowerAnimation{
    private Image image;
    private List<Image>shootingAnimation;

    public TowerAnimation(TowerType type)
    {
        this.init(type);
    } 

    private void init(TowerType type)
    {
        this.shootingAnimation = LoadSystems.loadAnimation(type.get_shootingAnimationPath(), type.get_frameCount());
        this.image = LoadSystems.loadImage(type.get_imagePath());
    }

    public Image getNextMovement(int currMovement)
    {
        Image image = this.shootingAnimation.get(currMovement);
        return image;
    }

    public Image getImage()
    {
        return this.image;
    }

}
