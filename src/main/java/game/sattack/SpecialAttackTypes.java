package game.sattack;

public enum SpecialAttackTypes {

    // const
    FireAttack(15, 200),
    BombAttack(30, 150),
    EarthQuake(10000, 1000);


    // constructor

    private int Reichweite;
    private int Damage;

    SpecialAttackTypes(int Reichweite, int Damage)
    {
        this.Reichweite = Reichweite; 
        this.Damage = Damage;
    }

    public double get_Reichweite()
    {
        return this.Reichweite;
    }

    public double get_Damage()
    {
        return this.Damage;
    }


}
