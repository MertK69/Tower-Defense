package game.sattack;

import util.Vector2;

public class SpecialAttack {
    private SpecialAttackType type;
    private Vector2 position;
    private boolean LockAnimation = false;
    private double Timer = 0d;
    private double AnimationLockTimer;
    private int currAttackAnimation = 0;
    private boolean reachedLastFrame = false;

    public SpecialAttack(SpecialAttackType type, Vector2 position)
    {
        this.type = type;
        this.position = position;
        this.AnimationLockTimer = type.get_AnimationInterval();
    }

    public boolean reachedLastFrame()
    {
        return this.reachedLastFrame;
    }

    public SpecialAttackType get_type()
    {
        return this.type;
    }

    public Vector2 get_position()
    {
        return this.position;
    }

    public boolean get_AnimationLock()
    {
        return this.LockAnimation;
    }

    public void lockAnimationLock()
    {
            LockAnimation = true;
    }

    public void delockAnimationLock()
    {
            LockAnimation = false;
    }

    public int currAttackAnimation(double dt)
    {
            if (nextFrame(dt) == true){
                    if (currAttackAnimation == this.type.get_FrameCount() - 2)
                    {
                            this.currAttackAnimation = 0;
                            delockAnimationLock();
                    } else{
                    currAttackAnimation++;
                    if (currAttackAnimation == this.type.get_FrameCount() - 2)
                        {
                            this.reachedLastFrame = true;
                        }
                    }
                    return currAttackAnimation;
            } else {
                    return currAttackAnimation;
            }
    }

    public boolean nextFrame(double dt)
    {
            Timer += dt;
            if (Timer >= AnimationLockTimer)
            {
                    Timer -= dt;
                    return true;
            } else {
                    return false;
                    }
    }
}
