package game.render.animationStorage;

import java.util.ArrayList;
import java.util.List;

import game.animation.enemyAnimationen.LoadSystems;
import game.sattack.SpecialAttackType;
import javafx.scene.image.Image;

public class SpecialAttackAnimation{    
    private Image image;
    private List<Image>SAttackAnimation = new ArrayList<>();

    public SpecialAttackAnimation(SpecialAttackType type)
    {   
        this.SAttackAnimation = LoadSystems.loadAnimation(type.get_AttackAnimationPath(), type.get_FrameCount());
    }

    public Image get_Image()
    {
        return this.image;
    }

    public Image get_NextImage(int curr_frame)
    {
        return this.SAttackAnimation.get(curr_frame);
    }


}
