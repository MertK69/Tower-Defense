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
    private SpecialAttackAnimation electricAnimation = new SpecialAttackAnimation(SpecialAttackType.ElectricWave);

    public void renderSAttack(GraphicsContext gc, List<SpecialAttack>SAttackList, double dt)
    {
        
        for (SpecialAttack SAttack : SAttackList)
        {
            Vector2 position = SAttack.get_position();
            if(SAttack.get_type() == SpecialAttackType.ElectricWave) SAttack.change_position();
            Image curr_Pic = get_Image(SAttack, dt);
            gc.save();
            gc.translate(position.getX(), position.getY());
            gc.drawImage(
                    curr_Pic,
                    - SAttack.get_type().get_renderDistance(),
                    - SAttack.get_type().get_renderDistance(),
                    SAttack.get_type().get_renderDistance() * 2, SAttack.get_type().get_renderDistance() * 2
            );
            gc.restore();
        }
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
        if(specialAttack.get_type() == SpecialAttackType.ElectricWave)
        {
            if(specialAttack.get_AnimationLock())
            {
                    image = electricAnimation.get_Image();
            } else {
                 int frame = specialAttack.currAttackAnimation(dt);
                 image = electricAnimation.get_NextImage(frame);	
            }
        }
        return image;
    }
}
