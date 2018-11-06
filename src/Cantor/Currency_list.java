package Cantor;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Currency_list
{
    public List<Currency> waluty = new ArrayList<Currency>();

    public void add(Currency waluta) { this.waluty.add(waluta); }

    public void show_informations()
    {
        String message= new String();
        for(int i=0;i<waluty.size();i++)
        {
            message+= i+1 + " ";
            message+= " " + waluty.get(i).get_code() + " | ";
            message+= " " + waluty.get(i).get_name() + "\n";
            //message+= " " + waluty.get(i).get_rate() + " | ";
            //message+= " " + waluty.get(i).get_multiplier() + "\n";
        }
        JOptionPane.showMessageDialog(new JFrame(), message, "DOSTĘPNE WALUTY", JOptionPane.INFORMATION_MESSAGE);
    }
}