package game.sattack;

public enum SpecialAttackType {

    // const
    FireAttack(15, 200, 250,"/images/Fire/Fire", 6, 0.15d, 18),
    BombAttack(30, 150, 500,"/images/Explosion/Explosion", 9, 0.15d, 36),
    ElectricWave(10000, 150, 1250,"/images/lightning/lightning", 8, 0.3d, 48);


    // constructor

    private int Reichweite;
    private int Damage;
    private int price;
    private String AttackAnimation;
    private int FrameCount;
    private double SAttackInterval;
    private double renderDistance;


    SpecialAttackType(int Reichweite, int Damage, int price,String AttackAnimation, int FrameCount, double SAttackInterval, double renderDistance)
    {
        this.Reichweite = Reichweite; 
        this.Damage = Damage;
        this.price = price;
        this.AttackAnimation = AttackAnimation;
        this.FrameCount = FrameCount;
        this.SAttackInterval = SAttackInterval;
        this.renderDistance = renderDistance;
    }

    public int get_Price()
    {
        return this.price;
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
