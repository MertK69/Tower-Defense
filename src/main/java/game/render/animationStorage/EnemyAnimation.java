package game.render.animationStorage;
import java.util.*;
import game.enemy.*;
import javafx.scene.image.Image;
import game.animation.enemyAnimationen.LoadSystems;
public class EnemyAnimation{
    private List<Image>movementRight;
    private List<Image>movementLeft;
    private List<Image>movementUpwards;
    private List<Image>movementDownwards;

    public EnemyAnimation(EnemyType type)
    {
        this.init(type);
    } 

    private void init(EnemyType type)
    {
        this.movementRight = LoadSystems.loadAnimation(type.get_rightPath(), type.get_frameCount()); 
        this.movementLeft = LoadSystems.loadAnimation(type.get_leftPath(), type.get_frameCount()); 
        this.movementUpwards = LoadSystems.loadAnimation(type.get_upwardsPath(), type.get_frameCount()); 
        this.movementDownwards = LoadSystems.loadAnimation(type.get_downwardsPath(), type.get_frameCount()); 
    }

    public Image getNextMovement(int direction, int currMovement)
    {
        Image image = null;
        if (direction == 1) image = this.movementLeft.get(currMovement);
        if (direction == 2) image = this.movementRight.get(currMovement);
		if (direction == 3) image = this.movementUpwards.get(currMovement);
		if (direction == 4) image = this.movementDownwards.get(currMovement);
        return image;
    }


}
