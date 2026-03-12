package game.render;

import java.util.List;

import game.render.animationStorage.SpecialAttackAnimation;
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.Vector2;

public class SAttackRenderer{
    private SpecialAttackAnimation fireAnimation = new SpecialAttackAnimation(SpecialAttackType.FireAttack);
    private SpecialAttackAnimation explosionAnimation = new SpecialAttackAnimation(SpecialAttackType.BombAttack);
    private SpecialAttackAnimation earthQuakeAnimation = new SpecialAttackAnimation(SpecialAttackType.EarthQuake);

    public void renderSAttack(GraphicsContext gc, List<SpecialAttack>SAttackList, double dt)
    {
        gc.save();
        
        for (SpecialAttack SAttack : SAttackList)
        {
            Vector2 position = SAttack.get_position();
            Image curr_Pic = get_Image(SAttack, dt);
            gc.translate(position.getX(), position.getY());
            gc.drawImage(
                    curr_Pic,
                    - 18,
                    - 18,
                    36, 36
            );
        }
        gc.restore();
    }

    public Image get_Image(SpecialAttack specialAttack, double dt)
    {
        Image image = null;
        if(specialAttack.get_type() == SpecialAttackType.FireAttack)
        {
            if(specialAttack.get_AnimationLock())
            {
                    image = fireAnimation.get_Image();
            } else {
                 int frame = specialAttack.currAttackAnimation(dt);
                 image = fireAnimation.get_NextImage(frame);	
            }
        }
        if(specialAttack.get_type() == SpecialAttackType.BombAttack)
        {
            if(specialAttack.get_AnimationLock())
            {
                    image = explosionAnimation.get_Image();
            } else {
                 int frame = specialAttack.currAttackAnimation(dt);
                 image = explosionAnimation.get_NextImage(frame);	
            }
        }
        if(specialAttack.get_type() == SpecialAttackType.EarthQuake)
        {
            if(specialAttack.get_AnimationLock())
            {
                    image = earthQuakeAnimation.get_Image();
            } else {
                 int frame = specialAttack.currAttackAnimation(dt);
                 image = earthQuakeAnimation.get_NextImage(frame);	
            }
        }
        return image;
    }
}
