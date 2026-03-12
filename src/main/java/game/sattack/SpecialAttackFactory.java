package game.sattack;

import util.Vector2;

public class SpecialAttackFactory{

    public SpecialAttack create_sattack(SpecialAttackType type, Vector2 position)
    {
        return new SpecialAttack(type, position);
    }
}
