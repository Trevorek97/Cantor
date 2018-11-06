package Cantor;

public class Currency
{
    private String name;
    private String code;
    private double rate;
    private double multiplier;


    Currency(String name, String code, double rate, double multiplier)
    {
        this.name=name;
        this.code=code;
        this.rate=rate;
        this.multiplier=multiplier;
    }

    public String get_code()       { return this.code; }
    public String get_name() 	   { return this.name; }
    public double get_rate()       { return this.rate; }
    public double get_multiplier() { return this.multiplier; }

    public void set_rate(double r) { this.rate = r; }
    public void set_multiplier (double multi) { this.multiplier = multi; }
}
