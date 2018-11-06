package Cantor;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame implements ActionListener
{

    JButton close, przelicz, informations;
    JLabel result, ilosc, first, second;
    JTextField currency_f, currency_s, money;
    static public XmlProvider xmlprov;
    static public Validator valid;
    static public Currency_list currlist;
    static public Calculate calc;


   View()
   {
       xmlprov = new XmlProvider();
       valid = new Validator();
       currlist = new Currency_list();
       calc = new Calculate();

       setSize(300, 300);
       setLocation(530, 250);
       setTitle("Przelicznik walut");
       setLayout(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       ImageIcon img = new ImageIcon("E:\\dolar.png");
       setIconImage(img.getImage());


       /*NAPISY NA GÓRZE*/
       first = new JLabel("Przelicz z:");
       first.setBounds(10, 10, 100, 30);
       add(first);

       second = new JLabel("Przelicz na:");
       second.setBounds(170, 10, 100, 30);
       add(second);


       /*WPROWADZANIE RODZAJU WALUTY*/
       currency_f = new JTextField();
       currency_f.setBounds(10, 40, 100, 30);
       add(currency_f);

       currency_s = new JTextField();
       currency_s.setBounds(170, 40, 100, 30);
       add(currency_s);


       /*PRZYCISKI*/
       przelicz = new JButton("Przelicz");
       przelicz.setBounds(10, 200, 100, 30);
       add(przelicz);
       przelicz.addActionListener(this);

       close = new JButton("Wyjście");
       close.setBounds(170, 200, 100, 30);
       add(close);
       close.addActionListener(this);

       informations = new JButton("Waluty:");
       informations.setBounds(170, 130, 100, 30);
       add(informations);
       informations.addActionListener(this);


       /*ŚRODKOWA CZĘŚĆ OKNA*/
       ilosc = new JLabel("Kwota:");
       ilosc.setBounds(70, 100, 100, 30);
       add(ilosc);

       money = new JTextField();
       money.setBounds(10, 130, 100, 30);
       add(money);

       result = new JLabel();
       result.setBounds(170, 100, 100, 30);
       add(result);
   }


    public static void main (String[]args)
    {
        View widok = new View();
        widok.setVisible (true);

        for (int i = 0; i < xmlprov.len; i++)
        {
            Currency curr = xmlprov.get_data(i);
            valid.check_xml_str(curr);
            valid.check_xml_num(curr);
            currlist.add(curr);
        }
        currlist.add(new Currency("złoty","PLN",1,1)); //dodajemy złotówki do bazy
    }


    @Override
    public void actionPerformed(ActionEvent akcja)
    {
        Object source = akcja.getSource();
        if(source == przelicz)
        {
            String cur_f  = currency_f.getText();
            String cur_s  = currency_s.getText();
            String cash_s = money.getText();

            valid.check_data_str(cur_f,currlist);
            valid.check_data_str(cur_s,currlist);

            int num = valid.check_data_num(cash_s);
            if(!(num == -1)) //użytkownik może wprowadzić znak , zamiast obsługiwanego .
            {
                char[] tmp = cash_s.toCharArray();
                tmp[num] = '.';
                cash_s = new String(tmp);
                valid.check_data_num(cash_s);
            }

            double cash = Double.parseDouble(cash_s);
            int f=-1,s=-1;
            for(int i=0;i<currlist.waluty.size();i++)
            {
                if((currlist.waluty.get(i).get_code().equals(cur_f)) || (currlist.waluty.get(i).get_name().equals(cur_f))) f=i;
                if((currlist.waluty.get(i).get_code().equals(cur_s)) || (currlist.waluty.get(i).get_name().equals(cur_s))) s=i;
            }

            double wynik=calc.calculate(currlist.waluty.get(f), currlist.waluty.get(s),cash);
            result.setText(Double.toString(wynik));
        }

        else if(source==informations) currlist.show_informations(); //Pokazuje okienko ze wszystkimi dostępnymi walutami
        else if(source==close) dispose(); //Zamyka główne okno programu
    }
}