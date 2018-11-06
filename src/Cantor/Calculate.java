package Cantor;

public class Calculate
{
    public double calculate(Currency first, Currency second, double money)
    {
        double result;

       if(first.get_code().equals("PLN") || first.get_name().equals("złoty") ) result = (money/second.get_rate())*second.get_multiplier();
       else if (second.get_code().equals("PLN") || second.get_name().equals("złoty")) result=(money*first.get_rate())/first.get_multiplier();
       else if (first.get_code() == second.get_code()) result = money;
       else result = ((money*first.get_rate())/second.get_rate())/first.get_multiplier()*second.get_multiplier();
      return result;
    }
}