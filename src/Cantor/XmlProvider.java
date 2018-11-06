package Cantor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlProvider
{
    public int len;
    private File inputFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    private NodeList nList;

    XmlProvider()
    {
        try
        {
        inputFile = new File("E:\\nbpp.xml");
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        nList = doc.getElementsByTagName("pozycja");
        len = nList.getLength();
        } catch (Exception e) { e.printStackTrace(); }
    }

    Currency get_data(int i)
    {
        Node nNode = nList.item(i);
        Element eElement = (Element) nNode;
        String name = eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
        String multiplier = eElement.getElementsByTagName("przelicznik").item(0).getTextContent();
        String code = eElement.getElementsByTagName("kod_waluty").item(0).getTextContent();
        String rate = eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent();
        double drate = Double.parseDouble(rate.replace(",", "."));
        double dmultiplier = Double.parseDouble(multiplier);
        Currency curr = new Currency(name, code, drate, dmultiplier);
        return curr;
    }
}
