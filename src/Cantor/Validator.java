package Cantor;
import javax.swing.*;

public class Validator extends JFrame
{
    //KONTROLA POPRAWNOŚCI NAZWY LUB KODU WALUTY -  DANE POBIERANE Z PLIKU XML
    public void check_xml_str(Currency curr)
    {
        String message;
        if(curr.get_code().length()==0)
        {
            message ="BRAK KODU WALUTY " + curr.get_name();
            JOptionPane.showMessageDialog(new JFrame(), message, "BŁĄD", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        String test=curr.get_code();
        if(test.length()==3)
        {
            char[] test_code = test.toCharArray();
            for(int i=0;i<3;i++)
            {
                if (test_code[i] >= 65 && test_code[i] <= 90) continue;
                else
                {
                    message ="NIEPOPRAWNY KOD WALUTY " + curr.get_code();
                    JOptionPane.showMessageDialog(new JFrame(), message, "BŁĄD", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }
            }
        }
        else
        {
            message ="NIEPOPRAWNY KOD WALUTY " + curr.get_code();
            JOptionPane.showMessageDialog(new JFrame(), message, "BŁĄD", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        //Sprawdzenie poprawności nazwy waluty
        test=curr.get_name();
        if(!(test.length()>0))
        {
            message ="BRAK NAZWY WALUTY " + curr.get_code();
            JOptionPane.showMessageDialog(new JFrame(), message, "BŁĄD", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

    }


    //KONTROLA POPRAWNOŚCI PRZELICZNIKA LUB KURSU WALUTY  - DANE POBIERANE Z PLIKU XML
    public void check_xml_num(Currency curr)
    {
        String message;
        if(curr.get_rate()<=0)
        {
            message ="KURS WALUTY NIE MOŻE BYĆ UJEMNY " + curr.get_code();
            JOptionPane.showMessageDialog(new JFrame(), message, "BŁĄD", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        if(curr.get_multiplier()<=0)
        {
            message ="PRZELICZNIK WALUTY NIE MOŻE BYĆ UJEMNY " + curr.get_code();
            JOptionPane.showMessageDialog(new JFrame(), message, "BŁĄD", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }


    //KONTROLA POPRAWNOŚCI NAZWY LUB KODU WALUTY - DANE WPROWADZA UŻYTKOWNIK W OKNIE APLIKACJI
    public int check_data_str(String tocheck, Currency_list cl)
    {
        int ctrlsum=0;
        if(tocheck.length()==0)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Podaj walutę!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
            return -1;
        }


        for(int i=0;i<cl.waluty.size();i++)
        {
            if(tocheck.equals(cl.waluty.get(i).get_code())) ctrlsum++;
            if(tocheck.equals(cl.waluty.get(i).get_name())) ctrlsum++;
        }
        if(ctrlsum==1) return 0;
        else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Błąd wprowadzanej waluty!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    //KONTROLA POPRAWNOŚCI PRZELICZNIKA LUB KURSU WALUTY - DANE WPROWADZA UŻYTKOWNIK W OKNIE APLIKACJI
    public int check_data_num(String tocheck)
    {
        if(tocheck.length()==0)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Wpisz kwotę!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        char[] ch = tocheck.toCharArray();
        for(int i = 0; i<tocheck.length(); i++)
        {
            if(ch[i]==',')
                return i;
        }
        isDouble(tocheck);
        double money=Double.parseDouble(tocheck);

        if(money<0)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Podaj nieujemną kwotę!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        return -1;
    }


   //FUNKCJA SPRAWDZAJĄCA, CZY WPROWADZANY STRING MOŻE BYĆ PRZEKONWERTOWANY NA TYP DOUBLE
    public double isDouble(String input)
    {
        try
        {
            return Double.parseDouble(input);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Kwota ma być liczbą!", "BŁĄD", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
}
