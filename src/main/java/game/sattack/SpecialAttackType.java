package game.sattack;

public enum SpecialAttackType {

    // const
    FireAttack(15, 200, "/images/Fire/Fire", 6, 0.15d, 18),
    BombAttack(30, 150, "/images/Explosion/Explosion", 9, 0.15d, 36),
    EarthQuake(10000, 1000, "/images/Explosion/Explosion", 5, 0.15d, 600);


    // constructor

    private int Reichweite;
    private int Damage;
    private String AttackAnimation;
    private int FrameCount;
    private double SAttackInterval;
    private double renderDistance;


    SpecialAttackType(int Reichweite, int Damage, String AttackAnimation, int FrameCount, double SAttackInterval, double renderDistance)
    {
        this.Reichweite = Reichweite; 
        this.Damage = Damage;
        this.AttackAnimation = AttackAnimation;
        this.FrameCount = FrameCount;
        this.SAttackInterval = SAttackInterval;
        this.renderDistance = renderDistance;
    }
    
    public double get_renderDistance()
    {
        return this.renderDistance;
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
