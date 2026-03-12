package game.sattack;

public enum SpecialAttackType {

    // const
    FireAttack(15, 200, "/images/Explosion/Explosion", 4, 0.2d),
    BombAttack(30, 150, "/images/Explosion/Explosion", 9, 0.2d),
    EarthQuake(10000, 1000, "/images/Explosion/Explosion", 5, 0.2d);


    // constructor

    private int Reichweite;
    private int Damage;
    private String AttackAnimation;
    private int FrameCount;
    private double SAttackInterval;


    SpecialAttackType(int Reichweite, int Damage, String AttackAnimation, int FrameCount, double SAttackInterval)
    {
        this.Reichweite = Reichweite; 
        this.Damage = Damage;
        this.AttackAnimation = AttackAnimation;
        this.FrameCount = FrameCount;
        this.SAttackInterval = SAttackInterval;
    }

    public double get_Reichweite()
    {
        return this.Reichweite;
    }

    public double get_Damage()
    {
        return this.Damage;
    }

    public int get_FrameCount()
    {
        return this.FrameCount;
    }

    public String get_AttackAnimationPath()
    {
        return this.AttackAnimation;
    }

    public double get_AnimationInterval()
    {
        return this.SAttackInterval;
    }


}
